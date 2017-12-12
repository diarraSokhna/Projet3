package fr.escalade_metier.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.escalade.beans.Pays;
import fr.escalade.beans.Site;
import fr.escalade.persistance.ClassementDao;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.SiteDao;

public class RechercheForm {
		
	private static final String CHOIX_PAYS = "idpays";
    private static final String CHOIX_CLASSEMENT = "idclass";
    
    private String   resultat;
    
    private Map<String, String> erreurs = new HashMap<String, String>();
   
    private SiteDao siteDao;
    private PaysDao paysDao;
    private ClassementDao classementDao;
    
	public RechercheForm(SiteDao siteDao, PaysDao paysDao, ClassementDao classementDao) {
		super();
		this.siteDao = siteDao;
		this.paysDao = paysDao;
		this.classementDao = classementDao;
	}
    
    public List<Site> rechercherSite(HttpServletRequest request){
    	List<Site> sites = new ArrayList<Site>();
    	
    	String idpays = getValeurChamp( request, CHOIX_PAYS);
    	Long id_pays = Long.parseLong(idpays);
    	Pays pays = paysDao.trouver(id_pays);
    	
    	try {
            if ( erreurs.isEmpty() ) {
            	
                siteDao.lister(id_pays);
                resultat = "Succès de la recherche du site.";
            } else {
                resultat = "Échec de la recherche du site.";
            }
        } catch ( DaoException e ) {
            setErreur( "imprévu", "Erreur imprévue lors de la recherche." );
            resultat = "Échec de la recherche du site : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }
    	return sites;
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
