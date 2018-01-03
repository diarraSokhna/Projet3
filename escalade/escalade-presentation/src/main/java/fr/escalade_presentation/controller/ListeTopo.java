package fr.escalade_presentation.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.beans.Topo;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.TopoDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.TopoDaoImp;
import fr.escalade.persistance.TopoSiteDao;
import fr.escalade_metier.forms.CreationTopoForm;

@WebServlet("/ListeTopo")
public class ListeTopo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE   = "/WEB-INF/vue/listeTopos.jsp";
	private TopoDao topoDao;
    
	public void init() throws ServletException {
		this.topoDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTopoDao();
		
	}
	
    public ListeTopo() { }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    
		request.setAttribute("topos", topoDao.lister());
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   }
}
