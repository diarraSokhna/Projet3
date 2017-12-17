package fr.escalade_presentation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Site;


@WebServlet("/AjouterDansSession")
public class AjouterDansSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SESSION_SITES  = "sessionSite";

    public static final String ATT_Site      = "site";
    public static final String ATT_FORM         = "form";

    public static final String VUE    = "/WEB-INF/vue/ajoutSite.jsp";
   
    public AjouterDansSession() { }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Site site = new Site();
		HttpSession session = request.getSession();
		
		site.setNomsite(request.getParameter("nomsite"));
        
        session.setAttribute("site", site);
        
        List<Site> listeSites = new ArrayList<Site>();
        listeSites.add(site);
        request.setAttribute("sites", listeSites);
        
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	 	   
        
        
	}
	

}
