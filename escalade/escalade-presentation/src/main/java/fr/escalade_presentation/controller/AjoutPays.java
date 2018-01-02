package fr.escalade_presentation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Pays;
import fr.escalade.beans.Voie;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.PaysDao;
import fr.escalade_metier.forms.AjoutPaysForm;
import fr.escalade_metier.forms.AjoutVoieForm;

@WebServlet("/AjoutPays")
public class AjoutPays extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public static final String CONF_DAO_FACTORY = "daofactory";
    
    public static final String ATT_PAYS      = "pays";
    public static final String ATT_FORM       = "form";

    public static final String VUE    = "/restreint/ajoutPays.jsp";
    public static final String VUE_SUCCES   = "/escalade-presentation/AjoutSite";
    private PaysDao paysDao;
    
    public void init() throws ServletException{
    	this.paysDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPaysDao();
    	 
    }
    public AjoutPays() { }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("payss", paysDao.lister());
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AjoutPaysForm form = new AjoutPaysForm( paysDao );
 		Pays pays = form.creerPays(request);
 		
		request.setAttribute(ATT_PAYS, pays);
		request.setAttribute(ATT_FORM, form);
	    
		
		 if ( form.getErreurs().isEmpty() ) {
	        	
	        	response.sendRedirect(VUE_SUCCES);
	        }else {
	        	request.setAttribute("payss", paysDao.lister());
	        	this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

	        }
		
		
	}

}
