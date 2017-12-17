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
import fr.escalade.beans.Site;
import fr.escalade.beans.Voie;
import fr.escalade.persistance.ClassementDao;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.ExpositionDao;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.SecteurDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade_metier.forms.AjoutSecteurForm;
import fr.escalade_metier.forms.AjoutSiteForm;

@WebServlet("/AjoutSecteur")
public class AjoutSecteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CONF_DAO_FACTORY = "daofactory";
    
    public static final String ATT_SECTEUR      = "secteur";
    public static final String ATT_FORMSECT        = "formsect";
    
    public static final String SESSION_SECTEURS  = "sessionSecteur";
    public static final String SESSION_VOIES  = "sessionVoie";

    public static final String VUE    = "/WEB-INF/vue/ajoutSiteSecteurVoie.jsp";
	

    private SecteurDao secteurDao;
    private CotationDao cotationDao;
    private ExpositionDao expositionDao;
    
    
    public AjoutSecteur() {}

    public void init() throws ServletException{
    	this.secteurDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSecteurDao();
    	this.cotationDao =( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCotationDao();
   	    this.expositionDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getExpositionDao();
    	   
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	
	}

	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		   
	 		AjoutSecteurForm formsect = new AjoutSecteurForm( secteurDao );
	 		Secteur secteur = formsect.creerSecteur(request);
			
	 	    HttpSession session = request.getSession();
	 		
			request.setAttribute(ATT_SECTEUR, secteur);
			request.setAttribute(ATT_FORMSECT, formsect);
		
			
			
			if ( formsect.getErreurs().isEmpty() ) {
				
//				 Map<String, Voie> voies = (HashMap<String, Voie>) session.getAttribute( SESSION_VOIES );
//		            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
//		            if ( voies == null ) {
//		                voies = new HashMap<String, Voie>();
//		            }
//		            /* Puis ajout du client de la commande courante dans la map */
//		            voies.put( secteur.getVoie().getNom_voie(), secteur.getVoie() );
//		            /* Et enfin (ré)enregistrement de la map en session */
//		            session.setAttribute( SESSION_VOIES, voies );

	           
				LinkedHashMap<String, Secteur> secteurs = (LinkedHashMap<String, Secteur>) session.getAttribute( SESSION_SECTEURS );
	           
	            if ( secteurs == null ) {
	                secteurs = new LinkedHashMap<String, Secteur>();
	            }
	           secteurs.put( secteur.getNomsect(), secteur );
	           session.setAttribute( SESSION_SECTEURS, secteurs );
		}
			
			request.setAttribute("cotations", cotationDao.lister());
			request.setAttribute("expositions",expositionDao.lister());
			
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

	 	   
		
	}

}
