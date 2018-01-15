package fr.escalade_presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.persistance.ClassementDao;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.VilleDao;

@WebServlet("/ListeSite")
public class ListeSite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_SESSION_SITE = "sessionSite";

	public static final String VUE = "/WEB-INF/vue/listeSite.jsp";

	private SiteDao siteDao;
	private PaysDao paysDao;
	private CotationDao cotationDao;
	private ClassementDao classementDao;
	private VilleDao villeDao;

	public void init() throws ServletException {

		this.siteDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getSiteDao();
		this.paysDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPaysDao();
		this.classementDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getClassementDao();
		this.cotationDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCotationDao();
		this.villeDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getVilleDao();

	}

	public ListeSite() {
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

	}

}