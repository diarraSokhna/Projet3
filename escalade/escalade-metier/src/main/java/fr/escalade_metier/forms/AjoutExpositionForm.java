package fr.escalade_metier.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.escalade.beans.Exposition;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.ExpositionDao;

public class AjoutExpositionForm {

	private static final String CHAMP_LIBELLE_EXPO = "libelleExpo";
	private String resultat;

	private Map<String, String> erreurs = new HashMap<String, String>();

	private ExpositionDao expositionDao;

	public AjoutExpositionForm(ExpositionDao expositionDao) {
		this.expositionDao = expositionDao;
	}

	public AjoutExpositionForm() {
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Exposition creerExposition(HttpServletRequest request) {
		String libelleexpo = getValeurChamp(request, CHAMP_LIBELLE_EXPO);
		Exposition exposition = new Exposition();
		traiterLibelleExposition(libelleexpo, exposition);

		try {
			if (erreurs.isEmpty()) {
				expositionDao.creer(exposition);
				resultat = "Succès de la création de l'exposition.";
			} else {
				resultat = "Échec de la création de l'exposition.";
			}
		} catch (DaoException e) {
			setErreur("imprévu", "Erreur imprévue lors de la création.");
			resultat = "Échec de la création de l'exposition : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}

		return exposition;

	}

	private void traiterLibelleExposition(String libelleexpo, Exposition exposition) {
		try {
			validationLibelleExposition(libelleexpo);
		} catch (FormValidationException e) {
			setErreur(CHAMP_LIBELLE_EXPO, e.getMessage());
		}
		exposition.setLibelle_expo(libelleexpo);
	}

	private void validationLibelleExposition(String libelleexpo) throws FormValidationException {

		if (libelleexpo != null && libelleexpo.length() < 4) {
			throw new FormValidationException("Le libellé de l'exposition doit contenir au moins 4 caractères.");
		} else if (libelleexpo == null) {
			throw new FormValidationException("Le libellé de l'exposition est obligatoire.");

		}

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
