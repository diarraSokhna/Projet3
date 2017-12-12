package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Secteur;
import fr.escalade.beans.Site;
import fr.escalade.persistance.ClassementDao;
import fr.escalade.persistance.DaoFactory;
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
    public static final String ATT_FORMSECT         = "formSect";
    
    public static final String SESSION_SECTEURS  = "sessionSecteur";

    public static final String VUE    = "/WEB-INF/vue/ajoutSite.jsp";
	

    private SecteurDao secteurDao;
    
    public AjoutSecteur() {}

    public void init() throws ServletException{
    	this.secteurDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSecteurDao();
   	    
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		    HttpSession session = request.getSession();
	   
	         
	       //ajout secteur
	 		
	 		AjoutSecteurForm formsect = new AjoutSecteurForm( secteurDao );
	 		Secteur secteur = formsect.creerSecteur(request);
	 		
	 		
	 		
	         if ( formsect.getErreurs().isEmpty() ) {
	             session.setAttribute( SESSION_SECTEURS, secteur );
	             
	         }else {
	    	     session.setAttribute( SESSION_SECTEURS, null );
	        
	         }	
	 		
	        request.setAttribute(ATT_SECTEUR, secteur);
		    request.setAttribute(ATT_FORMSECT, formsect);
	 		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	 	   
		
	}

}
