package fr.escalade_metier.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Commentaire;
import fr.escalade.beans.Topo;
import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.CommentaireDao;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.TopoDao;

public class AjoutCommentaireForm {

	private static final String CHAMP_lIBELLE = "libelle";
	private static final String CHAMP_TOPO = "topo";

	private static final String SESSION_UTILISATEURS = "sessionUtilisateur";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	private CommentaireDao commentaireDao;
	private TopoDao topoDao;

	public AjoutCommentaireForm(CommentaireDao commentaireDao, TopoDao topoDao) {
		this.commentaireDao = commentaireDao;
		this.topoDao = topoDao;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Commentaire ajouterCommentaire(HttpServletRequest request) {
		HttpSession session = request.getSession();

		Utilisateur utilisateur = (Utilisateur) session.getAttribute(SESSION_UTILISATEURS);

		String idtopo = getValeurChamp(request, CHAMP_TOPO);
		Long id_topo = Long.parseLong(idtopo);
		Topo topo = topoDao.trouver(id_topo);

		String libelle = getValeurChamp(request, CHAMP_lIBELLE);
		Commentaire commentaire = new Commentaire();

		try {

			commentaire.setUtilisateur(utilisateur);
			commentaire.setTopo(topo);
			traiterLibelle(libelle, commentaire);

			if (erreurs.isEmpty()) {
				commentaireDao.laisser(commentaire);
				resultat = "Succès de l'inscription.";
			} else {
				resultat = "Échec de l'inscription.";
			}
		} catch (DaoException e) {
			resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}

		return commentaire;
	}

	private void traiterLibelle(String libelle, Commentaire commentaire) {
		try {
			validationLibelle(libelle);
		} catch (FormValidationException e) {
			setErreur(CHAMP_lIBELLE, e.getMessage());
		}
		commentaire.setLibelle(libelle);
	}

	private void validationLibelle(String libelle) throws FormValidationException {
		if (libelle != null && libelle.length() < 3) {
			throw new FormValidationException("Le libellé doit contenir au moins 3 caractéres.");
		} else if (libelle == null) {
			throw new FormValidationException("Merci d'entrer un commentaire.");
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
