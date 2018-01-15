package fr.escalade_metier.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.escalade.beans.Pays;
import fr.escalade.beans.Ville;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.VilleDao;

public class AjoutVilleForm {

	private static final String CHAMP_NOM = "nomVille";
	private static final String CHOIX_PAYS = "idpays";
	private static final String CHAMP_CP = "codep";
	private String resultat;

	private Map<String, String> erreurs = new HashMap<String, String>();

	private VilleDao villeDao;
	private PaysDao paysDao;

	public AjoutVilleForm(VilleDao villeDao, PaysDao paysDao) {
		this.villeDao = villeDao;
		this.paysDao = paysDao;
	}

	public AjoutVilleForm() {
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Ville creerVille(HttpServletRequest request) {
		String nomville = getValeurChamp(request, CHAMP_NOM);
		String codep = getValeurChamp(request, CHAMP_CP);
		String idpays = getValeurChamp(request, CHOIX_PAYS).trim();
		long id_pays = Long.parseLong(idpays);
		Pays pays = paysDao.trouver(id_pays);

		Ville ville = new Ville();
		traiterNomVille(nomville, ville);
		traiterCodePostal(codep, ville);
		if (pays == null) {
			setErreur(CHOIX_PAYS, "Veuillez choisir un pays");
		} else {
			ville.setPays(pays);
		}

		try {
			if (erreurs.isEmpty()) {
				villeDao.creer(ville);
				resultat = "Succès de la création de la ville.";
			} else {
				resultat = "Échec de la création de la ville.";
			}
		} catch (DaoException e) {
			setErreur("imprévu", "Erreur imprévue lors de la création.");
			resultat = "Échec de la création de la ville : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}

		return ville;

	}

	private void traiterNomVille(String nomville, Ville ville) {
		try {
			validationNomVille(nomville);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		ville.setNom_ville(nomville);
	}

	private void traiterCodePostal(String codep, Ville ville) {

		int codeP = 0;
		try {
			codeP = validationCodePostal(codep);
		} catch (FormValidationException e) {
			setErreur(CHAMP_CP, e.getMessage());
		}
		ville.setCp(codeP);

	}

	private void validationNomVille(String nomville) throws FormValidationException {

		if (nomville != null && nomville.length() < 2) {
			throw new FormValidationException("Le nom de la ville doit contenir au moins 2 caractères.");
		} else if (nomville == null) {
			throw new FormValidationException("Le nom de la ville est obligatoire.");

		}

	}

	private int validationCodePostal(String codep) throws FormValidationException {

		int temp = 0;
		if (codep != null) {
			try {
				temp = Integer.parseInt(codep);
				if (temp < 5) {
					throw new FormValidationException("Le code postal doit comporter 5 caractéres.");
				}
			} catch (NumberFormatException e) {
				temp = 0;
				throw new FormValidationException("Le code postal doit être un nombre.");
			}
		} else {
			throw new FormValidationException("Il faut un code postal");
		}
		return temp;

	}

	private String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}

	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

}
