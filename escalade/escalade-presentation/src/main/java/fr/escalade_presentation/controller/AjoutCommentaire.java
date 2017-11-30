package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Commentaire;
import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.CommentaireDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.UtilisateurDao;
import fr.escalade_metier.forms.AjoutCommentaireForm;
import fr.escalade_metier.forms.ConnexionForm;

/**
 * Servlet implementation class AjoutCommentaire
 */
@WebServlet("/AjoutCommentaire")
public class AjoutCommentaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String ATT_COMMENTAIRE        = "commentaire";
	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/WEB-INF/vue/detailArticle.jsp";
    
    
    public AjoutCommentaire() {
	}

    private CommentaireDao  commentaireDao;
    
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.commentaireDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCommentaireDao();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
       this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
       AjoutCommentaireForm form = new AjoutCommentaireForm(commentaireDao);

        /* Traitement de la requête et récupération du bean en résultant */
        Commentaire commentaire = form.ajouterCommentaire( request );
       
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_COMMENTAIRE, commentaire );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }}
