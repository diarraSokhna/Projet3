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

/**
 * Servlet implementation class DetailsArticle
 */
@WebServlet("/DetailsArticle")
public class DetailsArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 public static final String CONF_DAO_FACTORY = "daofactory";
	 public static final String ATT_COMMENTAIRE        = "commentaire";
	    public static final String ATT_FORM         = "form";
	 public static final String PARAM_TITRE_ART = "titrearticle";
	 public static final String PARAM_ID_ART = "idArt";
	 
	    public static final String VUE  = "/WEB-INF/vue/detailArticle.jsp";
	    
	private ArticleDao articleDao;
	private CommentaireDao commentaireDao;
      
   
	public void init() throws ServletException {
		
		  /* Récupération d'une instance de notre DAO topo */
       this.articleDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getArticleDao();
       this.commentaireDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCommentaireDao();
		
		
	}
	
   public DetailsArticle() { }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String titrearticle = getValeurParametre( request, PARAM_TITRE_ART ); 
		request.setAttribute("article", articleDao.trouver(titrearticle));
		
	
		Long idArt = Long.parseLong(getValeurParametre( request, PARAM_ID_ART )); 
		request.setAttribute("commentaires", commentaireDao.lister(idArt));
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    AjoutCommentaireForm form = new AjoutCommentaireForm(commentaireDao);

	        /* Traitement de la requête et récupération du bean en résultant */
	        Commentaire commentaire = form.ajouterCommentaire( request );
	       
	        request.setAttribute( ATT_FORM, form );
	        request.setAttribute( ATT_COMMENTAIRE, commentaire );

	        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		
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
