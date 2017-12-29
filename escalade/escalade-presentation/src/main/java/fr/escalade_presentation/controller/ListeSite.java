package fr.escalade_presentation.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import fr.escalade.beans.Article;
import fr.escalade.beans.Classement;
import fr.escalade.beans.Cotation;
import fr.escalade.beans.Pays;
import fr.escalade.beans.Site;
import fr.escalade.beans.Ville;
import fr.escalade.persistance.ArticleDao;
import fr.escalade.persistance.ClassementDao;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.VilleDao;
import fr.escalade_metier.forms.AjoutArticleForm;
import fr.escalade_metier.forms.RechercheForm;

@WebServlet("/ListeSite")
public class ListeSite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public static final String CONF_DAO_FACTORY = "daofactory";
	private static final String CHOIX_PAYS = "idpays";
    private static final String CHOIX_CLASSEMENT = "idclass";
    private static final String CHOIX_VILLE = "idville";
    private static final String CHOIX_COTATION = "idcotation";
    public static final String ATT_SITE       = "site";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_PAYS = "sessionPays";

    public static final String VUE  = "/WEB-INF/vue/listeSite.jsp";
    public static final String PARAM_ID = "id";
    
    private SiteDao siteDao;
    private PaysDao paysDao;
    private CotationDao cotationDao;
    private ClassementDao classementDao;
    private VilleDao villeDao;

public void init() throws ServletException {
	
    this.siteDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getSiteDao();
    this.paysDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPaysDao();
	this.classementDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getClassementDao();
	this.cotationDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCotationDao();
	this.villeDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getVilleDao();

}

public ListeSite() {}

@SuppressWarnings("unchecked")
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("payss", paysDao.lister());
		request.setAttribute("classements", classementDao.lister());
		request.setAttribute("cotations", cotationDao.lister());

		String idPays = request.getParameter(CHOIX_PAYS);
		System.out.println("idpays"+idPays);
		if (idPays != null) {
			long id_pays = Long.parseLong(idPays);
		
			Map<Long, Long> villes = (Map<Long, Long>) villeDao.trouverpar(id_pays);
			String json = new Gson().toJson(villes);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} 
//			else {
//			request.setAttribute("villes", villeDao.lister());
//		}
		request.setAttribute("sites", siteDao.lister());

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	
	
}
private String getValeurChamp(HttpServletRequest request, String nomChamp) {
	 String valeur = request.getParameter( nomChamp );
       if ( valeur == null || valeur.trim().length() == 0 ) {
           return null;
       } else {
           return valeur;
       }
      
}

}