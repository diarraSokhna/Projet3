package fr.escalade_metier.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import fr.escalade.beans.Commentaire;
import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.CommentaireDao;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.UtilisateurDao;

public class AjoutCommentaireForm {

	 private static final String CHAMP_lIBELLE     = "libelle";
	 private static final String CHAMP_ID_USER        = "Utilisateur";
	 private static final String CHAMP_ID_ART       = "article";
    

     private String  resultat;
     private Map<String, String> erreurs = new HashMap<String, String>();
  
	 private CommentaireDao  commentaireDao;

     public AjoutCommentaireForm(CommentaireDao commentaireDao) {
		this.commentaireDao = commentaireDao;
	}


	public Map<String, String> getErreurs() {
        return erreurs;
    }

    
    public String getResultat() {
        return resultat;
    }
	
	public Commentaire ajouterCommentaire( HttpServletRequest request ) {
		
		String libelle = getValeurChamp( request, CHAMP_lIBELLE );
		String utilisateur = getValeurChamp( request, CHAMP_ID_USER );
		String article = getValeurChamp( request, CHAMP_ID_ART );
		
		
	    Commentaire commentaire = new Commentaire();
	    
	    try {
	    	
	        traiterLibelle( libelle, commentaire );
	        traiterUtilisateur( utilisateur, commentaire );
	        traiterArticle( article, commentaire );
	        
	        if ( erreurs.isEmpty() ) {
	            commentaireDao.laisser(commentaire);
	            resultat = "Succès de l'inscription.";
	        } else {
	            resultat = "Échec de l'inscription.";
	        }
	    } catch ( DaoException e ) {
	        resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	        e.printStackTrace();
	    }

	    return commentaire;
	}
	
	
    private void traiterLibelle( String libelle, Commentaire commentaire ) {
        try {
            validationLibelle( libelle );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_lIBELLE, e.getMessage() );
        }
        commentaire.setLibelle(libelle);
    }

    
    private void traiterUtilisateur( String utilisateur, Commentaire commentaire ) {
    	int id_user=0;
        try {
            validationUtilisateur( utilisateur );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_ID_USER, e.getMessage() );
        }
        commentaire.setId_user(id_user);
    }
    
    private void traiterArticle( String article, Commentaire commentaire ) {
    	int id_art=0;
        try {
            validationArticle( article );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_ID_USER, e.getMessage() );
        }
        commentaire.setId_art(id_art);
    }
    

    /* Validation de l'adresse email */
    private void validationLibelle( String libelle ) throws FormValidationException {
        if ( libelle.length() < 3  ) {
                throw new FormValidationException( "Le libellé doit contenir au moins 3 caractéres." );
        }
    }
    
    /* Validation des mots de passe */
    private void validationUtilisateur( String utilisateur ) throws FormValidationException {
       if ( utilisateur == null ) {
                throw new FormValidationException( "ko" );
            
        }
    }
    
    private void validationArticle( String article ) throws FormValidationException {
        if ( article == null ) {
                 throw new FormValidationException( "ko" );
             
         }
     }



    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
	
	
}
