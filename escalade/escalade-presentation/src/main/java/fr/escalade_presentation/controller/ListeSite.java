package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.persistance.ArticleDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.SiteDao;

@WebServlet("/ListeSite")
public class ListeSite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_SITE       = "site";
    public static final String VUE  = "/WEB-INF/vue/listeSite.jsp";
    
    private SiteDao siteDao;

public void init() throws ServletException {
	
    this.siteDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSiteDao();
    
	
}

public ListeSite() {}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	 request.setAttribute("sites", siteDao.lister());
	
     this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	
}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
}



}
