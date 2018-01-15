package fr.escalade_presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Article;
import fr.escalade.persistance.ArticleDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade_metier.forms.AjoutArticleForm;

public class AjoutArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String CHEMIN = "chemin";
	public static final String ATT_ARTICLE = "article";
	public static final String ATT_FORM = "form";
	public static final String SESSION_ARTICLES = "sessionArticle";

	public static final String VUE_SUCCES = "/escalade-presentation/Accueil";
	public static final String VUE_FORM = "/restreint/ajoutArticle.jsp";

	private ArticleDao articleDao;

	public void init() throws ServletException {
		this.articleDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getArticleDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String chemin = this.getServletConfig().getInitParameter(CHEMIN);
		AjoutArticleForm form = new AjoutArticleForm(articleDao);
		Article article = form.creerArticle(request, chemin);

		request.setAttribute(ATT_ARTICLE, article);
		request.setAttribute(ATT_FORM, form);

		HttpSession session = request.getSession();
		if (form.getErreurs().isEmpty()) {

			session.setAttribute(SESSION_ARTICLES, article);
			response.sendRedirect(VUE_SUCCES);

		} else {
			session.setAttribute(SESSION_ARTICLES, null);
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);

		}

	}
}