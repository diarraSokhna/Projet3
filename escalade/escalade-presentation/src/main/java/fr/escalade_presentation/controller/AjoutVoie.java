package fr.escalade_presentation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Secteur;
import fr.escalade.beans.Voie;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.ExpositionDao;
import fr.escalade.persistance.VoieDao;
import fr.escalade_metier.forms.AjoutVoieForm;

@WebServlet("/AjoutVoie")
public class AjoutVoie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CONF_DAO_FACTORY = "daofactory";
    
    public static final String ATT_VOIE      = "voie";
    public static final String ATT_FORMVOIE        = "formvoie";
    
    public static final String SESSION_VOIES  = "sessionVoie";
    
    public static final String SESSION_SECTEURS  = "sessionSecteur";

    public static final String VUE    = "/WEB-INF/vue/ajoutSiteSecteurVoie.jsp";
	
    private VoieDao voieDao;
    private CotationDao cotationDao;
    private ExpositionDao expositionDao;
    
    public AjoutVoie() { }

    public void init() throws ServletException{
    	this.voieDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getVoieDao();
   	    this.cotationDao =( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCotationDao();
   	    this.expositionDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getExpositionDao();
    	   
    	    
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("cotations", cotationDao.lister());
		request.setAttribute("expositions",expositionDao.lister());
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
	}

	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AjoutVoieForm formvoie = new AjoutVoieForm( voieDao, cotationDao, expositionDao );
 		Voie voie = formvoie.ajouterVoie(request);
		
 		HttpSession session = request.getSession();
 		
		request.setAttribute(ATT_VOIE, voie);
		request.setAttribute(ATT_FORMVOIE, formvoie);
	    
		
		if ( formvoie.getErreurs().isEmpty() ) {
			
			
//	  	 Map<String, Secteur> secteurs = (HashMap<String, Secteur>) session.getAttribute( SESSION_SECTEURS );
//            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
//	            if ( secteurs == null ) {
//	            	secteurs = new HashMap<String, Secteur>();
//	            }
//	            /* Puis ajout de la voie de du secteur courant dans la map */
//	            secteurs.put( voie.getSecteur().getNomsect(), voie.getSecteur() );
//	            /* Et enfin (ré)enregistrement de la map en session */
//	            session.setAttribute( SESSION_SECTEURS, secteurs );
//           
			LinkedHashMap<String, Voie> voies = (LinkedHashMap<String, Voie>) session.getAttribute( SESSION_VOIES );
           
            if ( voies == null ) {
                 voies = new LinkedHashMap<String, Voie>();
            }
           voies.put( voie.getNom_voie(), voie );
           session.setAttribute( SESSION_VOIES, voies );
	}
		
		request.setAttribute("cotations", cotationDao.lister());
		request.setAttribute("expositions",expositionDao.lister());
		
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

	
}
	
	}


