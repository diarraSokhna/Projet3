package fr.escalade_presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Article;
import fr.escalade.beans.Pays;
import fr.escalade.beans.Site;
import fr.escalade.persistance.ArticleDao;
import fr.escalade.persistance.ClassementDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade_metier.forms.AjoutArticleForm;
import fr.escalade_metier.forms.RechercheForm;

@WebServlet("/ListeSite")
public class ListeSite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public static final String CONF_DAO_FACTORY = "daofactory";
    private static final String CHAMP_PAYS      = "idpays";
    public static final String ATT_SITE       = "site";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_PAYS = "sessionPays";

    public static final String VUE  = "/WEB-INF/vue/listeSite.jsp";
    public static final String PARAM_ID = "id";
    
    private SiteDao siteDao;
    private PaysDao paysDao;
    private ClassementDao classementDao;

public void init() throws ServletException {
	
    this.siteDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSiteDao();
    this.paysDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPaysDao();
	this.classementDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getClassementDao();
}

public ListeSite() {}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setAttribute("payss", paysDao.lister());
	  request.setAttribute("classements", classementDao.lister());
	  request.setAttribute("sites", siteDao.lister());
	
      this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String idpays = getValeurParametre( request, CHAMP_PAYS);
		 
	      long id_pays = Long.parseLong(idpays);
//	      Pays pays = paysDao.trouver(id_pays);
	
		  request.setAttribute("sites", siteDao.lister(id_pays)); 
		  
		  request.setAttribute("payss", paysDao.lister());
		  request.setAttribute("classements", classementDao.lister());
		  this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	
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