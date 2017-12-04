package fr.escalade_metier.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import fr.escalade.beans.Article;
import fr.escalade.beans.Commentaire;
import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.CommentaireDao;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.UtilisateurDao;

public class AjoutCommentaireForm {

	 private static final String CHAMP_lIBELLE     = "libelle";
	 private static final String CHAMP_UTILISATEUR        = "utilisateur";
	 private static final String CHAMP_ARTICLE       = "article";
	 
	 private static final String SESSION_UTILISATEURS        = "sessionUtilisateur";
    
	 private static final String SESSION_ARTICLES        = "sessionArticle";

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
		   HttpSession session = request.getSession();
          
		   Utilisateur utilisateur =  (Utilisateur) session.getAttribute( SESSION_UTILISATEURS);
         
           Article article =  (Article) session.getAttribute( SESSION_ARTICLES);
           
           
		   String libelle = getValeurChamp( request, CHAMP_lIBELLE );
	       Commentaire commentaire = new Commentaire();
	    
	    try {
	    	
	        traiterLibelle( libelle, commentaire );
	        traiterUtilisateur(utilisateur, commentaire);
	        traiterArticle(article, commentaire);
	        
	        
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

    
    private void traiterUtilisateur( Utilisateur utilisateur, Commentaire commentaire ) {
   
    	  if ( utilisateur == null ) {
         setErreur( CHAMP_UTILISATEUR, "Utilisateur inconnu, merci d'utiliser le formulaire prévu à cet effet." );
         
      }
      
        commentaire.setUtilisateur(utilisateur);
    }
    
    private void traiterArticle( Article article, Commentaire commentaire ) {
       if ( article == null ) {
            setErreur( CHAMP_ARTICLE, "l'article inconnu, merci d'utiliser le formulaire prévu à cet effet." );
    }
        commentaire.setArticle(article);
    }
    

    private void validationLibelle( String libelle ) throws FormValidationException {
        if (libelle.length() < 3  ) {
                throw new FormValidationException( "Le libellé doit contenir au moins 3 caractéres." );
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
