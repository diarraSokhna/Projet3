package fr.escalade_metier.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.escalade.beans.Classement;
import fr.escalade.beans.Cotation;
import fr.escalade.beans.Pays;
import fr.escalade.beans.Site;
import fr.escalade.beans.Ville;
import fr.escalade.persistance.ClassementDao;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.VilleDao;

public class RechercheForm {
		
	private static final String CHOIX_PAYS = "idpays";
    private static final String CHOIX_CLASSEMENT = "idclass";
    private static final String CHOIX_VILLE = "idville";
    private static final String CHOIX_COTATION = "idcotation";
    
    private String   resultat;
    
    private Map<String, String> erreurs = new HashMap<String, String>();
   
    public String getResultat() {
        return resultat;
    }
    
    private SiteDao siteDao;
    private PaysDao paysDao;
    private CotationDao cotationDao;
    private VilleDao villeDao;
    private ClassementDao classementDao;
    
	public RechercheForm(SiteDao siteDao, PaysDao paysDao, ClassementDao classementDao, CotationDao cotationDao, VilleDao villeDao) {
		super();
		this.siteDao = siteDao;
		this.paysDao = paysDao;
		this.classementDao = classementDao;
		this.villeDao = villeDao;
		this.cotationDao = cotationDao;
	}
    
    public List<Site> rechercherSite(HttpServletRequest request){
    	List<Site> sites = new ArrayList<Site>();
    	
    	String idpays = getValeurChamp( request, CHOIX_PAYS);
    	Long id_pays = Long.parseLong(idpays);
    	Pays pays = paysDao.trouver(id_pays);
    	
    	String idclass = getValeurChamp( request, CHOIX_CLASSEMENT);
    	Long id_class = Long.parseLong(idclass);
    	Classement classement = classementDao.trouver(id_class);
    	
    	String idcotation = getValeurChamp( request, CHOIX_COTATION);
    	Long id_cotation = Long.parseLong(idcotation);
    	Cotation cotation = cotationDao.trouver(id_cotation);
    	
    	String idville = getValeurChamp( request, CHOIX_VILLE);
    	Long id_ville = Long.parseLong(idville);
    	Ville ville = villeDao.trouver(id_ville);
    	
    	
    	
    	try {
            if ( erreurs.isEmpty() ) {
            	
//                siteDao.listerParPays(pays);
//                siteDao.listerParClassement(classement);
//                siteDao.listerParCotation(cotation);
//                siteDao.listerParVille(ville);
                
                resultat = "Succès de la recherche du site.";
            } else {
                resultat = "La recherche n'a rien donnée!!.";
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
