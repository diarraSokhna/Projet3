package fr.escalade_presentation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Site;
import fr.escalade.beans.Voie;
import fr.escalade_metier.forms.AjoutSiteForm;
import fr.escalade_metier.forms.AjoutSiteSecteurVoieForm;


@WebServlet("/AjoutSiteSecteurVoie")
public class AjoutSiteSecteurVoie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SESSION_SITES  = "sessionSite";

    public static final String ATT_Site      = "site";
    public static final String ATT_FORM         = "form";

    public static final String VUE    = "/WEB-INF/vue/ajoutSiteSecteurVoie.jsp";
   
    public AjoutSiteSecteurVoie() { }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AjoutSiteSecteurVoieForm form = new AjoutSiteSecteurVoieForm();
		
		Site site = form.ajouterSiteSecteurVoie(request);
		
		
		request.setAttribute(ATT_Site, site);
		request.setAttribute(ATT_FORM, form);
		
		HttpSession session = request.getSession();
		
		session.setAttribute( SESSION_SITES, site );
		if ( form.getErreurs().isEmpty() ) {
		
			session.invalidate();
		}
		
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	 	   
        
        
	}
	

}
