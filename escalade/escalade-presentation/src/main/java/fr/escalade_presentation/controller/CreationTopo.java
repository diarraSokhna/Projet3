package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.beans.Topo;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.TopoDao;
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
       
    
//	public void init() throws ServletException {
//		   /* Récupération d'une instance de notre DAO topo */
//        this.topoDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTopoDao();
//	}
  
    public CreationTopo() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
         * Lecture du paramètre 'chemin' passé à la servlet via la déclaration
         * dans le web.xml
         */
        String chemin = this.getServletConfig().getInitParameter( CHEMIN );

        /* Préparation de l'objet formulaire */
        CreationTopoForm form = new CreationTopoForm( topoDao );

        /* Traitement de la requête et récupération du bean en résultant */
        Topo topo = form.creerTopo( request, chemin );

        /* Ajout du bean et de l'objet métier à l'objet requête */
        request.setAttribute( ATT_TOPO, topo );
        request.setAttribute( ATT_FORM, form );


            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
	
}
