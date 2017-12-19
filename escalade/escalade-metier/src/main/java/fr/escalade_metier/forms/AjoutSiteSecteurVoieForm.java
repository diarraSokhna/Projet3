package fr.escalade_metier.forms;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Cotation;
import fr.escalade.beans.Exposition;
import fr.escalade.beans.Secteur;
import fr.escalade.beans.Site;
import fr.escalade.beans.Voie;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.ExpositionDao;
import fr.escalade.persistance.SecteurDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.VoieDao;

public class AjoutSiteSecteurVoieForm {
	
	 public static final String SESSION_SITES  = "sessionSite";
	 public static final String SESSION_SECTEURS  = "sessionSecteur";
	 public static final String SESSION_VOIES  = "sessionVoie";
	
	    private SiteDao siteDao;
	    private SecteurDao secteurDao;
	    private VoieDao voieDao;
	 
	 private String   resultat;
	 private Map<String, String> erreurs = new HashMap<String, String>();
	    
	 
	  public Map<String, String> getErreurs() {
	        return erreurs;
	    }

	    public String getResultat() {
	        return resultat;
	    }
	    
	 @SuppressWarnings("unchecked")
	public Site ajouterSiteSecteurVoie(HttpServletRequest request){
	    	
		    
	        HttpSession session = request.getSession();
	        
	        Site  site =  (Site) session.getAttribute( SESSION_SITES );
	        
	        LinkedHashMap<String, Secteur> secteurs = (LinkedHashMap<String, Secteur>) session.getAttribute( SESSION_SECTEURS );
	       
	        LinkedHashMap<String, Voie> voies = (LinkedHashMap<String, Voie>) session.getAttribute( SESSION_VOIES );
	           
	       
	        try {
	            if ( erreurs.isEmpty() ) {
	            	
	            	siteDao.creer(site);
	            
//                 voieDao.creer(voie);
	                resultat = "Succès d'ajout de la voie.";
	            } else {
	                resultat = "Échec d'ajout de la voie.";
	            }
	        } catch ( DaoException e ) {
	            setErreur( "imprévu", "Erreur imprévue lors de l'ajout." );
	            resultat = "Échec de l'ajout de la voie : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	            e.printStackTrace();
	        }
	        return site;
	    }
	 
	 private void setErreur( String champ, String message ) {
	        erreurs.put( champ, message );
	    }
	 

}
