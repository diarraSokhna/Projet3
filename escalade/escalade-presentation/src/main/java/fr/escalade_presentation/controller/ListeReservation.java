package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.ClassementDao;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.PaysDao;
import fr.escalade.persistance.ReservationDao;
import fr.escalade.persistance.SiteDao;
import fr.escalade.persistance.VilleDao;


@WebServlet("/ListeReservation")
public class ListeReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CONF_DAO_FACTORY = "daofactory";

    public static final String ATT_SESSION_USER = "sessionUtilisateur";

    public static final String VUE = "/restreint/listeReservation.jsp";
	
	
  
    public ListeReservation() {}

    private ReservationDao reservationDao;

	public void init() throws ServletException {

		this.reservationDao = ((DaoFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getReservationDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		 HttpSession session = request.getSession();
	     Utilisateur user = (Utilisateur) session.getAttribute(ATT_SESSION_USER);
	     if(user !=null){
	     long id_user = user.getIduser();
	     request.setAttribute("reservations", reservationDao.lister(id_user));
	     }
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
