package fr.escalade_metier.forms;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Article;
import fr.escalade.beans.Secteur;
import fr.escalade.beans.Site;
import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.SecteurDao;

public class AjoutSecteurForm {

	private static final String CHAMP_NOM_SECTEUR = "nomsect";
	private static final String CHAMP_SITE = "site";

	public static final String SESSION_SITES = "sessionSite";
	public static final String SESSION_SECTEURS = "sessionSecteur";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	SecteurDao secteurDao;

	public AjoutSecteurForm(SecteurDao secteurDao) {
		this.secteurDao = secteurDao;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	@SuppressWarnings("unchecked")
	public Secteur creerSecteur(HttpServletRequest request) {

		Secteur secteur = new Secteur();

		String nomsect = getValeurChamp(request, CHAMP_NOM_SECTEUR);

		HttpSession session = request.getSession();

		traiterNomSecteur(nomsect, secteur);

		Site site = (Site) session.getAttribute(SESSION_SITES);
		site.addSecteur(secteur);

		LinkedHashMap<String, Secteur> secteurs = (LinkedHashMap<String, Secteur>) session
				.getAttribute(SESSION_SECTEURS);
		if (secteurs != null) {
			Secteur secteurSession = (Secteur) secteurs.get(nomsect);

			if (secteurSession != null ) {
				setErreur(CHAMP_NOM_SECTEUR, "Ce secteur existe déjà.");
			}
		}

		try {
			if (erreurs.isEmpty()) {
				resultat = "Succès de la création du secteur.";
			} else {
				resultat = "Échec de la création du secteur.";
			}
		} catch (DaoException e) {
			setErreur("imprévu", "Erreur imprévue lors de la création.");
			resultat = "Échec de la création du secteur : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}

		return secteur;
	}

	private void traiterNomSecteur(String nomsect, Secteur secteur) {
		try {
			validationNomSecteur(nomsect);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM_SECTEUR, e.getMessage());
		}
		secteur.setNomsect(nomsect);
	}

	private void validationNomSecteur(String nomsect) throws FormValidationException {
		if (secteurDao.trouver(nomsect) != null){
			throw new FormValidationException("Ce secteur existe déjà");
		}

		if (nomsect != null && nomsect.length() < 3) {

			throw new FormValidationException("Le nom du secteur doit contenir au moins 3 caractères.");
		} else if (nomsect == null) {
			throw new FormValidationException("Il faut un nom au secteur.");

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
