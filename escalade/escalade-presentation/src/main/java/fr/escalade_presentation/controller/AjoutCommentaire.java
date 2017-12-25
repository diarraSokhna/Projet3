package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.beans.Commentaire;
import fr.escalade.persistance.ArticleDao;
import fr.escalade.persistance.CommentaireDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.TopoDao;
import fr.escalade_metier.forms.AjoutCommentaireForm;


@WebServlet("/AjoutCommentaire")
public class AjoutCommentaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String ATT_COMMENTAIRE        = "commentaire";
	 public static final String ATT_FORM         = "form";
	 

	 public static final String PARAM_NOM_TOPO= "nomtopo";
	 public static final String PARAM_ID_TOPO = "idtopo";
	 
	 public static final String VUE     = "/escalade-presentation/DetailsTopo";
	 public static final String VUE_FORM  = "/WEB-INF/vue/detailsTopo.jsp";
	 
	
	 private CommentaireDao commentaireDao;
	 private TopoDao topoDao;
	
	public void init() throws ServletException {
		   this.topoDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTopoDao();
	       this.commentaireDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCommentaireDao();
	}
		
    public AjoutCommentaire() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    AjoutCommentaireForm form = new AjoutCommentaireForm(commentaireDao,topoDao);
        Commentaire commentaire = form.ajouterCommentaire( request );
       
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_COMMENTAIRE, commentaire );

        String nomtopo = getValeurParametre( request, PARAM_NOM_TOPO ); 
		request.setAttribute("topo", topoDao.trouver(nomtopo));
		
	
		Long idtopo = Long.parseLong(getValeurParametre( request, PARAM_ID_TOPO )); 
		request.setAttribute("commentaires", commentaireDao.lister(idtopo));
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		
      
	        
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
