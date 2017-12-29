package fr.escalade_metier.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.UtilisateurDao;

public final class ConnexionForm {
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS = "motdepasse";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

	private UtilisateurDao utilisateurDao;

	public ConnexionForm() {
	}

	public ConnexionForm(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Utilisateur connecterUtilisateur(HttpServletRequest request) {

		String email = getValeurChamp(request, CHAMP_EMAIL);
		String motDePasse = getValeurChamp(request, CHAMP_PASS);
		
//		Utilisateur u = utilisateurDao.trouverParMail("toto@toto.com");
//		
//		System.out.println("u "+ u.getEmail());
		
		Utilisateur utilisateur=new Utilisateur();

		try {
			traiterEmail(email, utilisateur);
			traiterMotsDePasse(motDePasse, email, utilisateur);

			if (erreurs.isEmpty() && passwordEncryptor.checkPassword(motDePasse, utilisateurDao.trouverParMail(email).getMotpass())) {
				resultat = "Succès de la connexion.";
				
			} else {
				resultat = "Échec de la connexion.";
			}
		} catch (DaoException e) {
			resultat = "Échec de la connexion : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}

		return utilisateur;
	}

	private void traiterEmail(String email, Utilisateur utilisateur) {
		try {
			validationEmail(email);
		} catch (FormValidationException e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		utilisateur.setEmail(email);
	}

	private void traiterMotsDePasse(String motDePasse, String email, Utilisateur utilisateur) {
		try {
			validationMotsDePasse(motDePasse, email);
		} catch (FormValidationException e) {
			setErreur(CHAMP_PASS, e.getMessage());
		}
		utilisateur.setMotpass(motDePasse);
	}

	private void validationEmail(String email) throws FormValidationException {
		if (email != null) {
			if (utilisateurDao.trouverParMail(email) == null) {
				System.out.println("utilisateur : " + utilisateurDao.trouverParMail(email));
				System.out.println("email  : " + email);
				throw new FormValidationException("Merci de saisir une adresse mail valide.");
			}
		} else {
			throw new FormValidationException("Merci de saisir une adresse mail.");
		}
	}

	private void validationMotsDePasse(String motDePasse, String email) throws FormValidationException {
		if (motDePasse != null ) {
			Utilisateur utilisateur = utilisateurDao.trouverParMail(email);
			String modepassbase = utilisateur.getMotpass();
			if (!passwordEncryptor.checkPassword(motDePasse, modepassbase)) {
			
				throw new FormValidationException("Veuillez saisir un mot de passe valide");
			}

		} else {
			throw new FormValidationException("Veuillez entrer un mot de passe.");
		}

	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}