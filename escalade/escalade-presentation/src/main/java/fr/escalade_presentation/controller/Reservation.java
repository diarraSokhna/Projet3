package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.ReservationDao;
import fr.escalade.persistance.TopoDao;
import fr.escalade_metier.forms.ReservationForm;

@WebServlet("/Reservation")
public class Reservation extends HttpServlet {
	   private static final long serialVersionUID = 1L;
    
	    public static final String CONF_DAO_FACTORY = "daofactory";
	    public static final String ATT_RESERVATION      = "reservation";
	    public static final String ATT_FORM         = "form";
	    
	    public static final String VUE    = "/WEB-INF/vue/reservationTopo.jsp";
   
	    private ReservationDao reservationDao;
	    private TopoDao topoDao;
	    
   public void init() throws ServletException {
			 this.reservationDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getReservationDao();
			 this.topoDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTopoDao();	
			 	
		}    
	    
    public Reservation() { }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("topos", topoDao.lister());
		request.setAttribute("reservations", reservationDao.lister());
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ReservationForm form = new ReservationForm(reservationDao,topoDao);
        
		fr.escalade.beans.Reservation reservation = form.creerReservation(request);
        

        request.setAttribute( ATT_RESERVATION, reservation );
        request.setAttribute( ATT_FORM, form );
        
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    	
	}

}
