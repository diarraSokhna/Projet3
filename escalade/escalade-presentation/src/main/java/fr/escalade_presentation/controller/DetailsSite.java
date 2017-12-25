package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.SecteurDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.TopoDao;
import fr.escalade.persistance.VoieDao;


@WebServlet("/DetailsSite")
public class DetailsSite extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	 public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String PARAM_NOM_SITE = "nomsite";
	 public static final String PARAM_ID_SITE = "idsite";
	 
	 public static final String VUE  = "/WEB-INF/vue/detailSite.jsp";
	    
	  private SiteDao siteDao;
	  private SecteurDao secteurDao;
      private VoieDao voieDao;
    public DetailsSite() {}

    public void init() throws ServletException {
  	  this.siteDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSiteDao();
  	  this.secteurDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSecteurDao();
  	  this.voieDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getVoieDao();
   	
  	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomsite = getValeurParametre( request, PARAM_NOM_SITE ); 
		
		String idsite = getValeurParametre( request, PARAM_ID_SITE ); 
		long id_site = Long.parseLong(idsite);
		
		
		
		request.setAttribute("site", siteDao.trouver(nomsite));
		request.setAttribute("secteurs", secteurDao.lister(id_site));
		request.setAttribute("voies", voieDao.lister(id_site));
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
