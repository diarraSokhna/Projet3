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

import eu.medsea.mimeutil.*;

import fr.escalade.beans.Topo;
import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.TopoDao;

public final class CreationTopoForm {

	    private static final String CHAMP_NOM       = "nom";
	    private static final String CHAMP_DESCRIPTION    = "description";
	    private static final String CHAMP_NOMBRE_PAGE   = "nbrpage";
	    private static final String CHAMP_UTILISATEUR = "utilisateur";
	    private static final String CHAMP_IMAGE     = "image";
	    
	    private static final String SESSION_UTILISATEURS        = "sessionUtilisateur";
	    
	    private static final int    TAILLE_TAMPON   = 10240;  // 10ko
	    
	    private String   resultat;
	    private Map<String, String> erreurs = new HashMap<String, String>();
	    
	    private TopoDao topoDao;
	   
	    
	    public CreationTopoForm(TopoDao topoDao) {
			super();
			this.topoDao = topoDao;
		}

		public Map<String, String> getErreurs() {
	        return erreurs;
	    }

	    public String getResultat() {
	        return resultat;
	    }
	    
	    
	    public Topo creerTopo( HttpServletRequest request, String chemin ) {
	    	 HttpSession session = request.getSession();
	         Utilisateur utilisateur =  (Utilisateur) session.getAttribute( SESSION_UTILISATEURS);

	        String nom = getValeurChamp( request, CHAMP_NOM );
	        String description = getValeurChamp( request, CHAMP_DESCRIPTION );
	        String nbrpage = getValeurChamp( request, CHAMP_NOMBRE_PAGE );
	        

	        Topo topo = new Topo();

	        traiterNom( nom, topo );
	        traiterDescription( description, topo );
	        traiterNbrPage( nbrpage, topo );
	        traiterUtilisateur( utilisateur, topo );
	        traiterImage( topo, request, chemin );
          
	        try {
	            if ( erreurs.isEmpty() ) {
	                topoDao.creer( topo );
	                resultat = "Succès de la création du topo.";
	            } else {
	                resultat = "Échec de la création du topo.";
	            }
	        } catch ( DaoException e ) {
	            setErreur( "imprévu", "Erreur imprévue lors de la création." );
	            resultat = "Échec de la création du topo : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	            e.printStackTrace();
	        }

	        return topo;
	    }

	    
		private void traiterNbrPage(String nbrpage, Topo topo) {
			
			int nbpage=0;
			   try {
				   nbpage= validationNbrPage( nbrpage );
		        } catch ( FormValidationException e ) {
		            setErreur( CHAMP_NOMBRE_PAGE, e.getMessage() );
		        }
		        topo.setNbpage(nbpage);
			
		}

		private int validationNbrPage(String nbrpage) throws FormValidationException {
			
		    int temp;
	        if ( nbrpage != null ) {
	            try {
	                temp = Integer.parseInt( nbrpage );
	                if ( temp < 0 ) {
	                    throw new FormValidationException( "Le nombre de page doit être un nombre positif." );
	                }
	            } catch ( NumberFormatException e ) {
	                temp = 0;
	                throw new FormValidationException( "Le nombre de page doit être un nombre." );
	            }
	        } else {
	            temp = 0;
	            throw new FormValidationException( "Merci d'entrer le nombre de page." );
	        }
	        return temp;
	    }
			
		private void traiterUtilisateur(Utilisateur utilisateur, Topo topo) {
		    if ( utilisateur == null ) {
	            setErreur( CHAMP_UTILISATEUR, "Utilisateur inconnu, merci d'utiliser le formulaire prévu à cet effet." );
        }
	        topo.setUtilisateur(utilisateur);
		
		}

		private void traiterNom(String nom, Topo topo) {
			
			   try {
		            validationNom( nom );
		        } catch ( FormValidationException e ) {
		            setErreur( CHAMP_NOM, e.getMessage() );
		        }
		        topo.setNom( nom );
			
		}
		
		private void traiterDescription(String description, Topo topo) {
			   try {
		            validationDescription( description );
		        } catch ( FormValidationException e ) {
		            setErreur( CHAMP_DESCRIPTION, e.getMessage() );
		        }
		        topo.setDescription( description );
			
		}

		private void traiterImage(Topo topo, HttpServletRequest request, String chemin) {
			 String image = null;
		        try {
		            image = validationImage( request, chemin );
		        } catch ( FormValidationException e ) {
		            setErreur( CHAMP_IMAGE, e.getMessage() );
		        }
		        topo.setImage( image );
			
		}

		private String validationImage(HttpServletRequest request, String chemin) throws FormValidationException {
			String nomFichier = null;
	        InputStream contenuFichier = null;
	        try {
	            Part part = request.getPart( CHAMP_IMAGE );
	            nomFichier = getNomFichier( part );

	            if ( nomFichier != null && !nomFichier.isEmpty() ) {
	                nomFichier = nomFichier.substring( nomFichier.lastIndexOf( '/' ) + 1 )
	                        .substring( nomFichier.lastIndexOf( '\\' ) + 1 );

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
		
		private void validationDescription(String description) throws FormValidationException {
			 if ( description != null && description.length() < 10 ) {
		            throw new FormValidationException( "La description  du topo doit contenir au moins 10 caractères." );
		        }else {
		        	throw new FormValidationException( "Il faut une description du topo." );
			 	       
		        }
			
		}

	

		private void validationNom(String nom) throws FormValidationException {
			 if ( nom != null && nom.length() < 2 ) {
		            throw new FormValidationException( "Le nom du topo doit contenir au moins 2 caractères." );
		        }else {
		        	throw new FormValidationException( "Le nom du topo est obligatoire." );
			 	       
		        }
			
		}

		 private static String getNomFichier( Part part ) {
		        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
		             if ( contentDisposition.trim().startsWith( "filename" ) ) {
		                 return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
		            }
		        }
		        return null;
		    }
		 
		 private void ecrireFichier( InputStream contenuFichier, String nomFichier, String chemin )
		            throws FormValidationException {
		        BufferedInputStream entree = null;
		        BufferedOutputStream sortie = null;
		        try {
		            entree = new BufferedInputStream( contenuFichier, TAILLE_TAMPON );
		            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ),
		                    TAILLE_TAMPON );

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
		 
		private String getValeurChamp(HttpServletRequest request, String nomChamp) {
			 String valeur = request.getParameter( nomChamp );
		        if ( valeur == null || valeur.trim().length() == 0 ) {
		            return null;
		        } else {
		            return valeur;
		        }
		
		}
		
		private void setErreur( String champ, String message ) {
	        erreurs.put( champ, message );
	    }
		
	 
}
