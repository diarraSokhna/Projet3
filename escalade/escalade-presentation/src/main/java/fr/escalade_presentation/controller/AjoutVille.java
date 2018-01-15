package fr.escalade_presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.beans.Ville;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.VilleDao;
import fr.escalade_metier.forms.AjoutVilleForm;

@WebServlet("/AjoutVille")
public class AjoutVille extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";

	public static final String ATT_VILLE = "ville";
	public static final String ATT_FORM = "form";

	public static final String VUE = "/restreint/ajoutVille.jsp";
	public static final String VUE_SUCCES = "/escalade-presentation/AjoutSite";
	private PaysDao paysDao;
	private VilleDao villeDao;

	public void init() throws ServletException {
		this.villeDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getVilleDao();
		this.paysDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPaysDao();

	}

	public AjoutVille() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("payss", paysDao.lister());
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AjoutVilleForm form = new AjoutVilleForm(villeDao, paysDao);
		Ville ville = form.creerVille(request);

		request.setAttribute(ATT_VILLE, ville);
		request.setAttribute(ATT_FORM, form);

		if (form.getErreurs().isEmpty()) {

			response.sendRedirect(VUE_SUCCES);
		} else {
			request.setAttribute("payss", paysDao.lister());
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

		}

	}

}
