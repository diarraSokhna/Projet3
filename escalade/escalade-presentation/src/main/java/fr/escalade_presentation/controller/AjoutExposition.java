package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.beans.Exposition;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.ExpositionDao;
import fr.escalade_metier.forms.AjoutExpositionForm;

@WebServlet("/AjoutExposition")
public class AjoutExposition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";

	public static final String ATT_EXPOSITION = "exposition";
	public static final String ATT_FORM = "form";

	public static final String VUE = "/restreint/ajoutExposition.jsp";
	public static final String VUE_SUCCES = "/escalade-presentation/AjoutVoie";

	private ExpositionDao expositionDao;

	public void init() throws ServletException {
		this.expositionDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getExpositionDao();

	}

	public AjoutExposition() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AjoutExpositionForm form = new AjoutExpositionForm(expositionDao);
		Exposition exposition = form.creerExposition(request);

		request.setAttribute(ATT_EXPOSITION, exposition);
		request.setAttribute(ATT_FORM, form);

		if (form.getErreurs().isEmpty()) {

			response.sendRedirect(VUE_SUCCES);
		} else {
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

		}

	}

}
