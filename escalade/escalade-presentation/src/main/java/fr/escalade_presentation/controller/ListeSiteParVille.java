package fr.escalade_presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Ville;
import fr.escalade.persistance.ClassementDao;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.VilleDao;

@WebServlet("/ListeSiteParVille")
public class ListeSiteParVille extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";
	private static final String CHOIX_VILLE = "idville";

	public static final String ATT_SESSION_VILLE = "sessionVille";
	public static final String ATT_SESSION_SITE = "sessionSite";

	public static final String VUE = "/WEB-INF/vue/listeSite.jsp";
	public static final String PARAM_ID = "id";
	public static final String PARAM_NOM_VILLE = "nomVille";

	private SiteDao siteDao;
	private PaysDao paysDao;
	private CotationDao cotationDao;
	private ClassementDao classementDao;
	private VilleDao villeDao;

	public ListeSiteParVille() {
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
		String idville = getValeurParametre(request, CHOIX_VILLE).trim();
		long id_ville = Long.parseLong(idville);
		Ville ville = villeDao.trouver(id_ville);

		HttpSession session = request.getSession();
		session.setAttribute(ATT_SESSION_VILLE, ville);

		if (ATT_SESSION_VILLE != null) {
			request.setAttribute("sites", siteDao.listerParVille(id_ville));
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
