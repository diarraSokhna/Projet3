package fr.escalade_presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Deconnexion")
public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = -5347117242583197008L;
	
	public static final String URL_REDIRECTION = "/escalade-presentation/Accueil";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
    	 HttpSession session = request.getSession();
         session.invalidate();

         response.sendRedirect(URL_REDIRECTION);
    }
}