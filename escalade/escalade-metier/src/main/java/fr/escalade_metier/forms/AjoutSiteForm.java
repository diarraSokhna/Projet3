package fr.escalade_metier.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import eu.medsea.mimeutil.MimeUtil;
import fr.escalade.beans.Site;
import fr.escalade.beans.Ville;
import fr.escalade.beans.Classement;
import fr.escalade.beans.Exposition;
import fr.escalade.beans.Pays;
import fr.escalade.beans.Secteur;
import fr.escalade.persistance.ClassementDao;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.ExpositionDao;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.SecteurDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.VilleDao;

public class AjoutSiteForm {
		
	    private static final String CHAMP_NOM       = "nom";
	    private static final String CHOIX_PAYS = "idpays";
	    private static final String CHAMP_IMAGE     = "image";
	    private static final String CHOIX_CLASSEMENT = "idclass";
	    private static final String CHOIX_VILLE = "idville";
	    
	    public static final String SESSION_SECTEURS  = "sessionSecteur";
	    
	    private static final int    TAILLE_TAMPON   = 10240;  // 10ko
	    
	    private String   resultat;
	    
	    private Map<String, String> erreurs = new HashMap<String, String>();
	    
	    private SiteDao siteDao;
	    private PaysDao paysDao;
	    private ClassementDao classementDao;
	    private VilleDao villeDao;
	    
	    
	    
	    public AjoutSiteForm() {}

		public AjoutSiteForm(SiteDao siteDao, PaysDao paysDao, ClassementDao classementDao, VilleDao villeDao){
	    	this.siteDao = siteDao;
	    	this.paysDao =  paysDao;
	    	this.classementDao = classementDao;
	    	this.villeDao = villeDao;
	    }
	    
	    public Map<String, String> getErreurs() {
	        return erreurs;
	    }

	    public String getResultat() {
	        return resultat;
	    }
	    
	
	    @SuppressWarnings( "unchecked" )
		public Site creerSite(HttpServletRequest request, String chemin){
	    	Site site = new Site();
	    	
	    	String idpays = getValeurChamp( request, CHOIX_PAYS);
	    	Long id_pays = Long.parseLong(idpays);
	    	Pays pays = paysDao.trouver(id_pays);
	    	
	    	String idclass = getValeurChamp( request, CHOIX_CLASSEMENT);
	    	Long id_class = Long.parseLong(idclass);
	    	Classement classement = classementDao.trouver(id_class);
	    	
	    	String idville = getValeurChamp( request, CHOIX_VILLE);
	    	Long id_ville = Long.parseLong(idville);
	    	Ville ville = villeDao.trouver(id_ville);
	    	
	    	HttpSession session = request.getSession();
	    	
	    	//secteur
	    	 LinkedHashMap<String, Secteur> secteurs = (LinkedHashMap<String, Secteur>) session.getAttribute( SESSION_SECTEURS );
		       
	    	
	    	 String nom = getValeurChamp( request, CHAMP_NOM );
	    	
	    	 if ( pays == null ) {
		            setErreur( CHOIX_PAYS, "Merci de choisir un pays.");
		            }
	        
	    	 if ( classement == null ) {
		            setErreur( CHOIX_CLASSEMENT, "Merci de choisir un classement.");
		            }
	    	
	    	 if ( ville == null ) {
		            setErreur( CHOIX_VILLE, "Merci de choisir un classement.");
		            }
	    	 
	    	 
	        site.setPays(pays);
	        site.setClassement(classement);
	        site.setVille(ville);
	        
	        traiterNom( nom, site );
	        traiterImage( site, request, chemin );
	        
            
	        try {
	            if ( erreurs.isEmpty() ) {
	            	
	                resultat = "Succès de la création du site.";
	            } else {
	                resultat = "Échec de la création du site.";
	            }
	        } catch ( DaoException e ) {
	            setErreur( "imprévu", "Erreur imprévue lors de la création." );
	            resultat = "Échec de la création du site : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	            e.printStackTrace();
	        }
	        return site;
	    }
	    
	
			
	    private void traiterNom(String nom, Site site) {
				
				   try {
			            validationNom( nom );
			        } catch ( FormValidationException e ) {
			            setErreur( CHAMP_NOM, e.getMessage() );
			        }
			        site.setNomsite(nom);
			
	    }
	    
		private void traiterImage(Site site, HttpServletRequest request, String chemin) {
			 String image = null;
		        try {
		            image = validationImage( request, chemin );
		        } catch ( FormValidationException e ) {
		            setErreur( CHAMP_IMAGE, e.getMessage() );
		        }
		        site.setImage( image );
			
		}
		
		private void validationNom(String nom) throws FormValidationException {
			if( siteDao.trouver(nom) != null){
				 throw new FormValidationException( "Ce site existe déja" );
			}
			 if ( nom != null && nom.length() < 2 ) {
		            throw new FormValidationException( "Le nom du site doit contenir au moins 2 caractères." );
		        }
			 else if ( nom == null ) {
		        	throw new FormValidationException( "Le nom du site est obligatoire." );
			 	       
		        }
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
