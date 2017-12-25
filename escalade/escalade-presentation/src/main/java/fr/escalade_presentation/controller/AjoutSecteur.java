package fr.escalade_presentation.controller;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Secteur;
import fr.escalade.beans.Site;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.ExpositionDao;
import fr.escalade.persistance.SecteurDao;
import fr.escalade_metier.forms.AjoutSecteurForm;

@WebServlet("/AjoutSecteur")
public class AjoutSecteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CONF_DAO_FACTORY = "daofactory";
    
    public static final String ATT_SECTEUR      = "secteur";
    public static final String ATT_FORMSECT        = "formsect";
    
    public static final String SESSION_SECTEURS  = "sessionSecteur";
    public static final String SESSION_SITES  = "sessionSite";

    public static final String VUE    = "/restreint/ajoutSiteSecteurVoie.jsp";
	

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

		request.setAttribute("cotations", cotationDao.lister());
		request.setAttribute("expositions",expositionDao.lister());
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
				
	            
				LinkedHashMap<String, Secteur> secteurs = (LinkedHashMap<String, Secteur>) session.getAttribute( SESSION_SECTEURS );
			
	            if ( secteurs == null ) {
	                 secteurs = new LinkedHashMap<String, Secteur>();
	            }
	            
	           secteurs.put( secteur.getNomsect(), secteur );
	           session.setAttribute( SESSION_SECTEURS, secteurs );
		}else {}
			
			request.setAttribute("cotations", cotationDao.lister());
			request.setAttribute("expositions",expositionDao.lister());
			
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

	 	   
		
	}

}
