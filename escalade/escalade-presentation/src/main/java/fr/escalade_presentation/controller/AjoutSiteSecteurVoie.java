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
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.SecteurDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.VoieDao;
import fr.escalade_metier.forms.AjoutSiteForm;
import fr.escalade_metier.forms.AjoutSiteSecteurVoieForm;


@WebServlet("/AjoutSiteSecteurVoie")
public class AjoutSiteSecteurVoie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String SESSION_SITES  = "sessionSite";

    public static final String ATT_Site      = "site";
    public static final String ATT_FORM         = "form";

    public static final String VUE_SUCCES    = "/escalade-presentation/ListeSite";
    public static final String VUE    = "/WEB-INF/vue/ajoutSiteSecteurVoie.jsp";
   
    private SiteDao siteDao;
    private SecteurDao secteurDao;
    private VoieDao voieDao;
    public AjoutSiteSecteurVoie() { }

    public void init() throws ServletException{
    	this.siteDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSiteDao();
    	this.secteurDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSecteurDao();
    	this.voieDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getVoieDao();
    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AjoutSiteSecteurVoieForm form = new AjoutSiteSecteurVoieForm(siteDao, secteurDao, voieDao);
		
		Site site = form.ajouterSiteSecteurVoie(request);
		
		
		request.setAttribute(ATT_Site, site);
		request.setAttribute(ATT_FORM, form);
		
		HttpSession session = request.getSession();
		
//		session.setAttribute( SESSION_SITES, site );
		if ( form.getErreurs().isEmpty() ) {
			session.invalidate();
			 response.sendRedirect(VUE_SUCCES);
		}else {
			
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
		
		
        
	}
	

}
