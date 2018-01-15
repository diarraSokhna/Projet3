package fr.escalade_presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Site;
import fr.escalade.persistance.ClassementDao;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.ExpositionDao;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.VilleDao;
import fr.escalade_metier.forms.AjoutSiteForm;

public class AjoutSite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String CHEMIN = "chemin";
	public static final String ATT_Site = "site";
	public static final String ATT_FORMSITE = "formsite";

	public static final String PARAM_ID_PAYS = "idpays";
	public static final String ATT_SECTEUR = "secteur";
	public static final String ATT_FORMSECT = "formsect";

	public static final String ATT_SESSION_PAYS = "sessionPays";
	public static final String SESSION_SECTEURS = "sessionSecteur";
	public static final String SESSION_SITES = "sessionSite";

	public static final String VUE = "/restreint/ajoutSiteSecteurVoie.jsp";

	private SiteDao siteDao;
	private PaysDao paysDao;
	private ClassementDao classementDao;
	private VilleDao villeDao;
	private CotationDao cotationDao;
	private ExpositionDao expositionDao;

	public AjoutSite() {
	}

	public void init() throws ServletException {
		this.siteDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getSiteDao();
		this.paysDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPaysDao();
		this.classementDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getClassementDao();
		this.cotationDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCotationDao();
		this.expositionDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getExpositionDao();
		this.villeDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getVilleDao();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("payss", paysDao.lister());
		request.setAttribute("villes", villeDao.lister());
		request.setAttribute("classements", classementDao.lister());
		request.setAttribute("cotations", cotationDao.lister());
		request.setAttribute("expositions", expositionDao.lister());
		request.setAttribute("sites", siteDao.lister());

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String chemin = this.getServletConfig().getInitParameter(CHEMIN);

		AjoutSiteForm formsite = new AjoutSiteForm(siteDao, paysDao, classementDao, villeDao);
		Site site = formsite.creerSite(request, chemin);

		request.setAttribute(ATT_Site, site);
		request.setAttribute(ATT_FORMSITE, formsite);

		HttpSession session = request.getSession();

		if (formsite.getErreurs().isEmpty()) {
			session.setAttribute(SESSION_SITES, site);

		} else {
			session.setAttribute(SESSION_SITES, null);
		}

		request.setAttribute("payss", paysDao.lister());

		request.setAttribute("villes", villeDao.lister());
		request.setAttribute("classements", classementDao.lister());
		request.setAttribute("cotations", cotationDao.lister());
		request.setAttribute("expositions", expositionDao.lister());

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}

}