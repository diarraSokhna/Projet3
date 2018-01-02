package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.beans.Cotation;
import fr.escalade.beans.Pays;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.PaysDao;
import fr.escalade_metier.forms.AjoutCotationForm;
import fr.escalade_metier.forms.AjoutPaysForm;

@WebServlet("/AjoutCotation")
public class AjoutCotation extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public static final String CONF_DAO_FACTORY = "daofactory";
    
    public static final String ATT_COTATION      = "cotation";
    public static final String ATT_FORM       = "form";

    public static final String VUE    = "/restreint/ajoutCotation.jsp";
    public static final String VUE_SUCCES   = "/escalade-presentation/AjoutVoie";
    private CotationDao cotationDao;
    
    public void init() throws ServletException{
    	this.cotationDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCotationDao();
    	 
    }
	
    public AjoutCotation() { }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
		AjoutCotationForm form = new AjoutCotationForm( cotationDao );
 		Cotation cotation = form.creerCotation(request);
 		
		request.setAttribute(ATT_COTATION, cotation);
		request.setAttribute(ATT_FORM, form);
	    
		
		 if ( form.getErreurs().isEmpty() ) {
	        	
	        	response.sendRedirect(VUE_SUCCES);
	        }else {
	        	this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

	        }
		
		
	}
		
	}


