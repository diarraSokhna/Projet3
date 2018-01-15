package fr.escalade_presentation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fr.escalade.beans.Ville;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.VilleDao;

@WebServlet("/ChargerVille")
public class ChargerVille extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String PARAM_ID_PAYS = "idpays";

	private VilleDao villeDao;

	public ChargerVille() {

	}

	public void init() throws ServletException {
		this.villeDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getVilleDao();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idpays = getValeurParametre(request, PARAM_ID_PAYS).trim();
		List<Ville> villes = new ArrayList<Ville>();
		String json = null;
		if (idpays != null) {
			long id_pays = Long.parseLong(idpays);
			villes = villeDao.lister(id_pays);
			request.setAttribute("villes", villes);
			json = new Gson().toJson(villes);
			response.setContentType("application/json");
			response.getWriter().write(json);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
