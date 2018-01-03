package fr.escalade_presentation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.UtilisateurDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade_metier.forms.InscriptionForm;

@WebServlet("/Inscription" )
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    
    public static final String VUE_FORM              = "/WEB-INF/vue/inscription.jsp";
    public static final String VUE_SUCCES         = "/escalade-presentation/Connection";
   
    public Inscription() { }

    private UtilisateurDao  utilisateurDao;

    public void init() throws ServletException {
        this.utilisateurDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        InscriptionForm form = new InscriptionForm( utilisateurDao );
        Utilisateur utilisateur = form.inscrireUtilisateur( request );
        
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );
        
        if ( form.getErreurs().isEmpty()) {
           response.sendRedirect(VUE_SUCCES);
            
        } else {
        	this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
          
        }
  
	}

}
