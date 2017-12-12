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


public class AjoutSite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String CHEMIN           = "chemin";
    public static final String ATT_Site      = "site";
    public static final String ATT_FORM         = "form";
    
    public static final String SESSION_SITES  = "sessionSite";

    public static final String VUE    = "/WEB-INF/vue/ajoutSite.jsp";
	
    private SiteDao siteDao;
    private PaysDao paysDao;
    private ClassementDao classementDao;
    public AjoutSite() {}

    public void init() throws ServletException{
    	this.siteDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSiteDao();
    	this.paysDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPaysDao();
    	this.classementDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getClassementDao();
    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("payss", paysDao.lister());
		request.setAttribute("classements", classementDao.lister());
		request.setAttribute("sites",siteDao.lister());
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ajout site
		String chemin =  this.getServletConfig().getInitParameter(CHEMIN);
		
		AjoutSiteForm form = new AjoutSiteForm( siteDao, paysDao, classementDao );
		Site site = form.creerSite(request, chemin);
		
		request.setAttribute(ATT_Site, site);
		request.setAttribute(ATT_FORM, form);
		
		
		HttpSession session = request.getSession();
	         if ( form.getErreurs().isEmpty() ) {
	             session.setAttribute( SESSION_SITES, site );
	             
	    }else {
	    	session.setAttribute( SESSION_SITES, null );
	        
	    }
	         
	      
	     	request.setAttribute(ATT_Site, site);
			request.setAttribute(ATT_FORM, form);
		
	 		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	 	   
		
	}

}
