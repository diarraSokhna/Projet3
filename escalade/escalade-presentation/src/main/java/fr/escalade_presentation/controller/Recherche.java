package fr.escalade_presentation.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.VilleDao;


@WebServlet("/Recherche")
public class Recherche extends HttpServlet {
	 private static final long serialVersionUID = 1L; 
	 public static final String CONF_DAO_FACTORY = "daofactory";
     private static final String CHOIX_PAYS = "idpays";
	 private static final String CHOIX_VILLE = "idville";
	 private VilleDao villeDao;
	 private PaysDao paysDao;
	 
    public Recherche() { }

    public void init() throws ServletException {
    	
       
        this.paysDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPaysDao();
    	this.villeDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getVilleDao();

    }

    
    
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectedValue = request.getParameter(CHOIX_PAYS);
		long idpays = Long.parseLong(selectedValue);
	    Map<Long, Long> villes = (Map<Long, Long>) villeDao.trouverpar(idpays);
	    String json = new Gson().toJson(villes);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
