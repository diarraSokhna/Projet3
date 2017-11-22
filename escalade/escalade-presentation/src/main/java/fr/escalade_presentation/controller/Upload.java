package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.escalade.beans.Fichier;
import fr.escalade_metier.forms.UploadForm;


public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CHEMIN      = "chemin";
	
    public static final String ATT_FICHIER = "fichier";
	public static final String ATT_FORM    = "form";
	public static final String VUE = "/WEB-INF/vue/upload.jsp";
	


    public Upload() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'envoi de fichiers */
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 /*
         * Lecture du paramètre 'chemin' passé à la servlet via la déclaration
         * dans le web.xml
         */
        String chemin = this.getServletConfig().getInitParameter( CHEMIN );

        /* Préparation de l'objet formulaire */
        UploadForm form = new UploadForm();

        /* Traitement de la requête et récupération du bean en résultant */
        Fichier fichier = form.enregistrerFichier( request, chemin );

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_FICHIER, fichier );

	    this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}
		
	
}
