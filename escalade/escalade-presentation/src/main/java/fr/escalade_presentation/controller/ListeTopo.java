package fr.escalade_presentation.controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.TopoDao;


public class TopoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TopoDao topoDao;
       
    
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.topoDao =  daoFactory.getTopoDao();
		
	}
	
    public TopoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	     try {
	            request.setAttribute("topos", topoDao.lister());
	        }
	        catch (DaoException e) {
	            request.setAttribute("erreur", e.getMessage());
	        }
	    //on dit a notre servlet d'afficher la page jsp
		this.getServletContext().getRequestDispatcher("/WEB-INF/vue/listeTopos.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//   ConnectionForm form = new ConnectionForm();
//   form.v�rifierIndentifiants(request);
//   request.setAttribute("form", form);
		
//		 try {
//		//on cr�e un objet utilisateur
//		Utilisateur utilisateur =  new Utilisateur();
//		//on charge les donn�es
////		utilisateur.setId_p(Integer.parseInt(request.getParameter("id_p")));
//		
//		utilisateur.setNom(request.getParameter("nom"));
//		utilisateur.setPrenom(request.getParameter("prenom"));
//		utilisateur.setAdresse(request.getParameter("adresse"));
//		
//		utilisateurDao.ajouter(utilisateur);
//		//on recup�re tous les utilisateurs
//		request.setAttribute("utilisateurs", utilisateurDao.lister());
//		
//		   }
//	        catch (Exception e) {
//	            request.setAttribute("erreur", e.getMessage());
//	        }
//		 
//        this.getServletContext().getRequestDispatcher("/WEB-INF/vue/listeGrimpeurs.jsp").forward(request, response);
		
	}

}
