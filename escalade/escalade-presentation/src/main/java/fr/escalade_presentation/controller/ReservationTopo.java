package fr.escalade_presentation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.escalade.beans.Reservation;
import fr.escalade.persistance.DaoFactory;
import fr.escalade.persistance.ReservationDao;
import fr.escalade.persistance.TopoDao;
import fr.escalade_metier.forms.ReservationForm;

@WebServlet("/ReservationTopo")
public class ReservationTopo extends HttpServlet {
	   private static final long serialVersionUID = 1L;
    
	    public static final String CONF_DAO_FACTORY = "daofactory";
	    public static final String ATT_RESERVATION      = "reservation";
	    public static final String PARAM_ID_TOPO = "idtopo";
	    public static final String ATT_FORM         = "form";
	    
	    public static final String VUE    = "/restreint/reservationTopo.jsp";
   
	    private ReservationDao reservationDao;
	    private TopoDao topoDao;
	    
   public void init() throws ServletException {
			 this.reservationDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getReservationDao();
			 this.topoDao = ( (DaoFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getTopoDao();	
			 	
		}    
	    
    public ReservationTopo() { }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("topos", topoDao.lister());
		
		String idtopo = getValeurParametre( request, PARAM_ID_TOPO );
		long id_topo = Long.parseLong(idtopo);
		request.setAttribute("topo", topoDao.trouver(id_topo));
		request.setAttribute("reservations", reservationDao.lister());
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ReservationForm form = new ReservationForm(reservationDao,topoDao);
        
	    Reservation reservation = form.creerReservation(request);
        

        request.setAttribute( ATT_RESERVATION, reservation );
        request.setAttribute( ATT_FORM, form );
        
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    	
	}

	
	 private static String getValeurParametre( HttpServletRequest request, String nomChamp ) {
	        String valeur = request.getParameter( nomChamp );
	        if ( valeur == null || valeur.trim().length() == 0 ) {
	            return null;
	        } else {
	            return valeur;
	        }
	    }
}
