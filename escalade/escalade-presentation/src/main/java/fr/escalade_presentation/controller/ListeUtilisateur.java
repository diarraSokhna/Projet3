package fr.escalade_presentation.controller;


import java.io.IOException;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import fr.escalade.metier.service.UtilisateurService;

@EnableJpaRepositories(basePackages="fr.escalade.persistance.dao")
@WebServlet("/ListeUtilisateur" )
public class ListeUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	    public static final String VUE  = "/WEB-INF/vue/listeUtilisateurs.jsp";
	    
		@Autowired
	     private UtilisateurService utilisateurService;
	
    public ListeUtilisateur() {}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    
			request.setAttribute("utilisateurs", utilisateurService.lister());
		
	      //on dit a notre servlet d'afficher la page jsp
		  this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
