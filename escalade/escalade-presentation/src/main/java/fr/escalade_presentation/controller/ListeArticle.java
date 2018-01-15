package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.persistance.ArticleDao;
import fr.escalade.persistance.DaoFactory;

@WebServlet("/ListeArticle")
public class ListeArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String VUE = "/WEB-INF/vue/listeArticles.jsp";
	private ArticleDao articleDao;

	public void init() throws ServletException {

		this.articleDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getArticleDao();

	}

	public ListeArticle() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("articles", articleDao.lister());

		// int idArt = Integer.parseInt(getValeurParametre( request,
		// PARAM_ID_ART ));
		// request.setAttribute("rowCount", articleDao.count(idArt));

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
