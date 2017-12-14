package fr.escalade_presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.UtilisateurDao;
import fr.escalade_metier.forms.ConnexionForm;

@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String ATT_USER         = "utilisateur";
	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/WEB-INF/vue/connection.jsp";
    public static final String VUE_SUCCES              = "/escalade-presentation/Accueil";
    
    
    public Connection() {
	}

    private UtilisateurDao  utilisateurDao;
    
    public void init() throws ServletException {
        this.utilisateurDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
         this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        ConnexionForm form = new ConnexionForm(utilisateurDao);

        Utilisateur utilisateur = form.connecterUtilisateur( request );
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );
     
        HttpSession session = request.getSession();
        
        if ( form.getErreurs().isEmpty()) {
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            response.sendRedirect(VUE_SUCCES);
            
        } else {
            session.setAttribute( ATT_SESSION_USER, null );
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
            
        }

        

        
    }
}