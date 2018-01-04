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

import fr.escalade.beans.Pays;
import fr.escalade.beans.Ville;
import fr.escalade.persistance.ClassementDao;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.ExpositionDao;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.VilleDao;


@WebServlet("/ChargerVille")
public class ChargerVille extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static final String CONF_DAO_FACTORY = "daofactory";
	private static final String CHOIX_PAYS = "idpays";
	   
	 public static final String VUE    = "/restreint/ajoutSiteSecteurVoie.jsp";
		private SiteDao siteDao;
		private PaysDao paysDao;
		private ClassementDao classementDao;
		private VilleDao villeDao;
		private CotationDao cotationDao;
		private ExpositionDao expositionDao;
    public ChargerVille() {
       
    }
	public void init() throws ServletException {
		this.siteDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getSiteDao();
		this.paysDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPaysDao();
		this.classementDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getClassementDao();
		this.cotationDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCotationDao();
		this.expositionDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getExpositionDao();
		this.villeDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getVilleDao();
		

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idpays = request.getParameter(CHOIX_PAYS);
		
		if(idpays != null){
			long id_pays = Long.parseLong(idpays);
			request.setAttribute("villes", villeDao.lister(id_pays));
//			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

		}
//		else {
//			
//			request.setAttribute("villes", villeDao.lister());
////			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
//
//		}
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

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
