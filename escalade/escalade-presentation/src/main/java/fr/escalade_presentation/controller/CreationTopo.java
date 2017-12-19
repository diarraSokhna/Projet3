package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.beans.Topo;
import fr.escalade.persistance.TopoDao;
import fr.escalade.persistance.TopoSiteDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.SiteDao;
import fr.escalade_metier.forms.CreationTopoForm;


public class CreationTopo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	    public static final String CONF_DAO_FACTORY = "daofactory";
	    public static final String CHEMIN           = "chemin";
	    public static final String ATT_TOPO       = "topo";
	    public static final String ATT_FORM         = "form";

	    public static final String VUE_FORM    = "/WEB-INF/vue/creationTopo.jsp";
	    public static final String VUE_SUCCES    = "/escalade-presentation/ListeTopo";
	
		private TopoDao topoDao;
		private SiteDao siteDao;
		 private TopoSiteDao topoSiteDao;   
    
	public void init() throws ServletException {
		 this.topoDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTopoDao();
		 this.siteDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSiteDao();	
		 this.topoSiteDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTopoSiteDao();	
					
	}
  
    public CreationTopo() { }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("sites", siteDao.lister());
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chemin = this.getServletConfig().getInitParameter( CHEMIN );

		CreationTopoForm form = new CreationTopoForm( topoDao, siteDao, topoSiteDao );

        Topo topo = form.creerTopo( request, chemin );

        request.setAttribute( ATT_TOPO, topo );
        request.setAttribute( ATT_FORM, form );
        
        if ( form.getErreurs().isEmpty() ) {
        	response.sendRedirect(VUE_SUCCES);
        }else {
        	request.setAttribute("sites", siteDao.lister());
        	this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        }
	}

}
	

