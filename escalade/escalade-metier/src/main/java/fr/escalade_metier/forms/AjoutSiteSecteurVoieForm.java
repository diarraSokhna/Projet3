package fr.escalade_metier.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Secteur;
import fr.escalade.beans.Site;
import fr.escalade.beans.Voie;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.SecteurDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.VoieDao;

public class AjoutSiteSecteurVoieForm {

	public static final String SESSION_SITES = "sessionSite";
	public static final String SESSION_SECTEURS = "sessionSecteur";
	public static final String SESSION_VOIES = "sessionVoie";

	private SiteDao siteDao;
	private SecteurDao secteurDao;
	private VoieDao voieDao;

	public AjoutSiteSecteurVoieForm(SiteDao siteDao, SecteurDao secteurDao, VoieDao voieDao) {
		super();
		this.siteDao = siteDao;
		this.secteurDao = secteurDao;
		this.voieDao = voieDao;
	}

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Site ajouterSiteSecteurVoie(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Site site = (Site) session.getAttribute(SESSION_SITES);

		try {
			if (site != null) {
				siteDao.creer(site);
				for (Secteur secteur : site.getSecteurs()) {
					secteurDao.creer(secteur, site);
					for (Voie voie : secteur.getVoies()) {
						voieDao.creerVoie(voie, secteur);
					}
				}

				resultat = "Succès d'ajout du site .";
			} else {
				resultat = "Échec d'ajout du site .";
			}
		} catch (DaoException e) {
			setErreur("imprévu", "Erreur imprévue lors de l'ajout.");
			resultat = "Échec de l'ajout du site: une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}
		return site;
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

}
