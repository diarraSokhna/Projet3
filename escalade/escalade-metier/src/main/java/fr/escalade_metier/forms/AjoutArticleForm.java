package fr.escalade_metier.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;



import eu.medsea.mimeutil.MimeUtil;
import fr.escalade.beans.Article;
import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.ArticleDao;
import fr.escalade.persistance.DaoException;

public class AjoutArticleForm {

	
	private static final String CHAMP_TITRE        = "titre";
	private static final String CHAMP_CONTENU       = "contenu";
	private static final String CHAMP_IMAGE       = "photo";
	private static final String CHAMP_UTILISATEUR      = "utilisateur";
	
	private static final String SESSION_UTILISATEURS        = "sessionUtilisateur";
	
    private static final int TAILLE_TAMPON = 10140;
    private String  resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    
    private ArticleDao articleDao;

	   public AjoutArticleForm(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public Map<String, String> getErreurs() {
	        return erreurs;
	    }

	    public String getResultat() {
	        return resultat;
	    }
	    
	 
		public Article creerArticle( HttpServletRequest request, String chemin ) {
			
			
		    HttpSession session = request.getSession();
		    Utilisateur utilisateur =  (Utilisateur) session.getAttribute( SESSION_UTILISATEURS);

			
			String titre = getValeurChamp( request, CHAMP_TITRE );
	        String contenu = getValeurChamp( request, CHAMP_CONTENU );
	        
	        Article article = new Article();

	        traiterTitre( titre, article );
	        traiterContenu( contenu, article );
	        traiterImage( article, request, chemin );
	        traiterUtilisateur( utilisateur, article );
	        
	        
	        try {
	            if ( erreurs.isEmpty() ) {
	                articleDao.creer( article );
	                resultat = "Succès de la création de l'article.";
	            } else {
	                resultat = "Échec de la création de l'article.";
	            }
	        } catch ( DaoException e ) {
	            setErreur( "imprévu", "Erreur imprévue lors de la création." );
	            resultat = "Échec de la création de l'article : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	            e.printStackTrace();
	        }

	        return article;
	    }   
	    
	    private void traiterTitre( String titre, Article article ) {
	        try {
	            validationTitre( titre );
	        } catch ( FormValidationException e ) {
	            setErreur( CHAMP_TITRE, e.getMessage() );
	        }
	        article.setTitre(titre);
	    }

	    private void traiterContenu( String contenu, Article article ) {
	        try {
	            validationContenu( contenu );
	        } catch ( FormValidationException e ) {
	            setErreur( CHAMP_CONTENU, e.getMessage() );
	        }
	        article.setContenu(contenu);
	    }
	    
	    
	    
	    private void traiterImage( Article article, HttpServletRequest request, String chemin ) {
	        String image = null;
	        try {
	            image = validationImage( request, chemin );
	        } catch ( FormValidationException e ) {
	            setErreur( CHAMP_IMAGE, e.getMessage() );
	        }
	        article.setPhoto(image);
	    }
	    
	    private void traiterUtilisateur( Utilisateur utilisateur, Article article ) {
	        if ( utilisateur == null ) {
	            setErreur( CHAMP_UTILISATEUR, "Utilisateur inconnu, merci d'utiliser le formulaire prévu à cet effet." );
        }
	        article.setUtilisateur(utilisateur);
	    }
	    
	    private void validationTitre( String titre ) throws FormValidationException {
	        if ( titre != null && titre.length() < 3  ) {
	          
	                throw new FormValidationException( "Le titre de l'article doit contenir au moins 3 caractères." );
	        }else if ( titre == null  ){
	        	throw new FormValidationException( "Il faut un titre à l'article." );
	 	       
	        }
	    }
	    
	    private void validationContenu( String contenu ) throws FormValidationException {
	        if ( contenu != null && contenu.length() < 3  ) {
	            throw new FormValidationException( "Le contenu de l'article doit contenir au moins 50 caractères." );
	        }
	        else if ( contenu == null  ){
	        	throw new FormValidationException( "Il faut un contenu pour l'article." );
	 	       
	        }
	    }
  
	    private String validationImage( HttpServletRequest request, String chemin ) throws FormValidationException {
	       String nomFichier = null;
	        InputStream contenuFichier = null;
	        try {
	            Part part = request.getPart( CHAMP_IMAGE );
	            nomFichier = getNomFichier( part );

	            if ( nomFichier != null && !nomFichier.isEmpty() ) {
	               nomFichier = nomFichier.substring( nomFichier.lastIndexOf( '/' ) + 1 )
	                        .substring( nomFichier.lastIndexOf( '\\' ) + 1 );

	                /* Récupération du contenu du fichier */
	                contenuFichier = part.getInputStream();
	                MimeUtil.registerMimeDetector( "eu.medsea.mimeutil.detector.MagicMimeMimeDetector" );
	                Collection<?> mimeTypes = MimeUtil.getMimeTypes( contenuFichier );
	                
	                if ( mimeTypes.toString().startsWith( "image" ) ) {
	                     ecrireFichier( contenuFichier, nomFichier, chemin );
	                } else {
	                    throw new FormValidationException( "Le fichier envoyé doit être une image." );
	                }
	            }
	        } catch ( IllegalStateException e ) {
	           e.printStackTrace();
	            throw new FormValidationException( "Le fichier envoyé ne doit pas dépasser 1Mo." );
	        } catch ( IOException e ) {
	           e.printStackTrace();
	            throw new FormValidationException( "Erreur de configuration du serveur." );
	        } catch ( ServletException e ) {
	            e.printStackTrace();
	            throw new FormValidationException(
	                    "Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer votre fichier." );
	        }

	        return nomFichier;
	    }
	    
	   
	    private static String getNomFichier( Part part ) {
	        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
	            if ( contentDisposition.trim().startsWith( "filename" ) ) {
	               
	            	return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
	            }
	        }
	        return null;
	    }

	    private void ecrireFichier( InputStream contenuFichier, String nomFichier, String chemin ) throws FormValidationException {
	        BufferedInputStream entree = null;
	        BufferedOutputStream sortie = null;
	        try {
	            entree = new BufferedInputStream( contenuFichier, TAILLE_TAMPON );
	            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ), TAILLE_TAMPON );

	            byte[] tampon = new byte[TAILLE_TAMPON];
	            int longueur = 0;
	            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
	                sortie.write( tampon, 0, longueur );
	            }
	        } catch ( Exception e ) {
	            throw new FormValidationException( "Erreur lors de l'écriture du fichier sur le disque." );
	        } finally {
	            try {
	                sortie.close();
	            } catch ( IOException ignore ) {
	            }
	            try {
	                entree.close();
	            } catch ( IOException ignore ) {
	            }
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
