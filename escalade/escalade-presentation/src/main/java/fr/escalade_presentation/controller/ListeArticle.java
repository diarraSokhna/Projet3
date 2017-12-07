package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.persistance.ArticleDao;
import fr.escalade.persistance.CommentaireDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.TopoDao;

@WebServlet("/ListeArticle")
public class ListeArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	    public static final String CONF_DAO_FACTORY = "daofactory";
	    public static final String ATT_ARTICLE       = "article";
	    public static final String VUE  = "/WEB-INF/vue/listeArticles.jsp";

	    public static final String SESSION_ARTICLES  = "sessionArticle";
	    public static final String PARAM_ID_ART = "idArt";
	    
	    private ArticleDao articleDao;
    
	public void init() throws ServletException {
		
        this.articleDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getArticleDao();
        
		
	}
	
    public ListeArticle() {}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("articles", articleDao.lister());
		
//		int idArt = Integer.parseInt(getValeurParametre( request, PARAM_ID_ART )); 
//		request.setAttribute("rowCount", articleDao.count(idArt));
		
	    //on dit a notre servlet d'afficher la page jsp
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
