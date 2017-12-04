package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.beans.Topo;
import fr.escalade.persistance.TopoDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.PaysDao;
import fr.escalade_metier.forms.CreationTopoForm;

@MultipartConfig
public class CreationTopo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	    public static final String CONF_DAO_FACTORY = "daofactory";
	    public static final String CHEMIN           = "chemin";
	    public static final String ATT_TOPO       = "topo";
	    public static final String ATT_FORM         = "form";

	    public static final String VUE    = "/WEB-INF/vue/creationTopo.jsp";
	
	
	  private TopoDao topoDao;
	  private PaysDao paysDao;
       
    
	public void init() throws ServletException {
		 this.topoDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTopoDao();
		 this.paysDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPaysDao();	
	}
  
    public CreationTopo() { }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("payss", paysDao.lister());
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chemin = this.getServletConfig().getInitParameter( CHEMIN );

		CreationTopoForm form = new CreationTopoForm( topoDao );

        Topo topo = form.creerTopo( request, chemin );

        request.setAttribute( ATT_TOPO, topo );
        request.setAttribute( ATT_FORM, form );


            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
	
}
