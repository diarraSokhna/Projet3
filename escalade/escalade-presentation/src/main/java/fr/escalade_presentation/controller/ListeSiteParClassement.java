package fr.escalade_presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Classement;
import fr.escalade.persistance.ClassementDao;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.VilleDao;

@WebServlet("/ListeSiteParClassement")
public class ListeSiteParClassement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";
	private static final String CHOIX_CLASSEMENT = "idclass";

	public static final String ATT_SESSION_CLASSEMENT = "sessionClassement";
	public static final String ATT_SESSION_SITE = "sessionSite";

	public static final String VUE = "/WEB-INF/vue/listeSite.jsp";
	public static final String PARAM_ID = "id";
	public static final String PARAM_LIBELLE_CLASSEMENT = "libelleClassement";

	private SiteDao siteDao;
	private PaysDao paysDao;
	private CotationDao cotationDao;
	private ClassementDao classementDao;
	private VilleDao villeDao;

	public ListeSiteParClassement() {
	}

	public void init() throws ServletException {

		this.siteDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getSiteDao();
		this.paysDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPaysDao();
		this.classementDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getClassementDao();
		this.cotationDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCotationDao();
		this.villeDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getVilleDao();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("payss", paysDao.lister());
		request.setAttribute("classements", classementDao.lister());
		request.setAttribute("cotations", cotationDao.lister());
		request.setAttribute("villes", villeDao.lister());

		request.setAttribute("sites", siteDao.lister());

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idclassement = getValeurParametre(request, CHOIX_CLASSEMENT).trim();
		long id_classement = Long.parseLong(idclassement);
		Classement classement = classementDao.trouver(id_classement);

		HttpSession session = request.getSession();
		session.setAttribute(ATT_SESSION_CLASSEMENT, classement);

		if (ATT_SESSION_CLASSEMENT != null) {
			request.setAttribute("sites", siteDao.listerParClassement(id_classement));
		}

		request.setAttribute("payss", paysDao.lister());
		request.setAttribute("classements", classementDao.lister());
		request.setAttribute("cotations", cotationDao.lister());
		request.setAttribute("villes", villeDao.lister());

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}

	private static String getValeurParametre(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}

}
