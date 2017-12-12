package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.TopoDao;


@WebServlet("/DetailsSite")
public class DetailsSite extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	 public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String PARAM_NOM_SITE = "nomsite";

	 public static final String VUE  = "/WEB-INF/vue/detailSite.jsp";
	    
	  private SiteDao siteDao;
      
    public DetailsSite() {}

    public void init() throws ServletException {
  	  this.siteDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSiteDao();
  	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomsite = getValeurParametre( request, PARAM_NOM_SITE ); 
		request.setAttribute("site", siteDao.trouver(nomsite));
		
	   this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	 private static String getValeurParametre( HttpServletRequest request, String nomChamp ) {
	        String valeur = request.getParameter( nomChamp );
	        if ( valeur == null || valeur.trim().length() == 0 ) {
	            return null;
	        } else {
	            return valeur;
	        }
	    }
}
