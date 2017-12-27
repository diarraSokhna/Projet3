package fr.escalade_presentation.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.beans.Topo;
import fr.escalade.persistance.CommentaireDao;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.TopoDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.TopoDaoImp;

@WebServlet("/DetailsTopo")
public class DetailsTopo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String PARAM_NOM_TOPO = "nomtopo";
	 public static final String PARAM_ID_TOPO = "idtopo";
	 public static final String VUE  = "/WEB-INF/vue/detailsTopo.jsp";
	    
	private TopoDao topoDao;
	private SiteDao siteDao;
	private CommentaireDao commentaireDao;
    
	
	public void init() throws ServletException {
	  this.topoDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTopoDao();
	  this.siteDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSiteDao();
	  this.commentaireDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCommentaireDao();
		
	}
	
    public DetailsTopo() { }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomtopo = getValeurParametre( request, PARAM_NOM_TOPO ); 
		String idtopo = getValeurParametre( request, PARAM_ID_TOPO );
		long id_topo = Long.parseLong(idtopo);
		
//		request.setAttribute("sites", siteDao.listerParTopo(id_topo));
		
		request.setAttribute("commentaires", commentaireDao.lister(id_topo));
		request.setAttribute("topo", topoDao.trouver(nomtopo));
		
		request.setAttribute("rowCount", commentaireDao.count(id_topo));
		
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
