package fr.escalade_metier.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.escalade.beans.Reservation;
import fr.escalade.beans.Site;
import fr.escalade.beans.Topo;
import fr.escalade.beans.TopoSite;
import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.ReservationDao;
import fr.escalade.persistance.TopoDao;

public class ReservationForm {
   
	    
	    private static final String CHAMP_DATE = "dateresa";
	    private static final String CHAMP_TOPO= "idtopo";
	    
	    private static final String SESSION_UTILISATEURS        = "sessionUtilisateur";
	    
	    private String   resultat;
	    private Map<String, String> erreurs = new HashMap<String, String>();
	    
	    private ReservationDao reservationDao;
	    private TopoDao topoDao;
	    
		public ReservationForm(ReservationDao reservationDao, TopoDao topoDao) {
			this.reservationDao = reservationDao;
			this.topoDao = topoDao;
		}
	    
		public Map<String, String> getErreurs() {
	        return erreurs;
	    }

	    public String getResultat() {
	        return resultat;
	    }
	    
	    public Reservation creerReservation( HttpServletRequest request ) {
	    	 HttpSession session = request.getSession();
	         Utilisateur utilisateur =  (Utilisateur) session.getAttribute( SESSION_UTILISATEURS);

	         String idtopo = getValeurChamp( request, CHAMP_TOPO);
	         Long id_topo = Long.parseLong(idtopo);
		     Topo topo = topoDao.trouver(id_topo);
	         
		     Reservation reservation = new Reservation();
		     
		     
		     java.text.DateFormat format = new java.text.SimpleDateFormat("dd-MM-yyyy");
		     java.util.Date date;
		     String date_resa = getValeurChamp( request, CHAMP_DATE );
			try {
				date = format.parse(date_resa);
				java.sql.Date dateresa = new java.sql.Date(date.getTime());
				 reservation.setDate_resa(dateresa);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		     
	        traiterDateResa(date_resa, idtopo ,  reservation);
			
	         reservation.setTopo(topo);
	         reservation.setUtilisateur(utilisateur);
	        
	       
	        try {
	            if ( erreurs.isEmpty() ) {
	            	
	              reservationDao.creer( reservation );
	                
	                resultat = "Succès  de la réservation.";
	            } else {
	                resultat = "Échec  de la réservation.";
	            }
	        } catch ( DaoException e ) {
	            setErreur( "imprévu", "Erreur imprévue lors de la réservation." );
	            resultat = "Échec  de la réservation : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	            e.printStackTrace();
	        }

	        return reservation;
	    }
	    
	 

	    private void traiterDateResa( String date_resa, String idtopo, Reservation reservation ) {
	    	
	    	
	    	java.text.DateFormat format = new java.text.SimpleDateFormat("dd-MM-yyyy");
	    	java.util.Date date;
				try {
					date = format.parse(date_resa);
					java.sql.Date dateresa = new java.sql.Date(date.getTime());
					
					 reservation.setDate_resa(dateresa);
				        
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
	    	
	        try {
	            validationDateResa( date_resa, idtopo );
	        } catch ( FormValidationException e ) {
	            setErreur( CHAMP_DATE, e.getMessage() );
	        }
	       
	        
	    }

	    private void validationDateResa( String date_resa , String idtopo) throws FormValidationException {
	    	if (date_resa == null){
	        	throw new FormValidationException( "Merci de choisir une date de séservation." );
	        	
			}
	    	
	    	long temp = Long.parseLong(idtopo);
	    
	    	java.text.DateFormat format = new java.text.SimpleDateFormat("dd-MM-yyyy");
	    	java.util.Date date;
				try {
					date = format.parse(date_resa);
					java.sql.Date dateresa = new java.sql.Date(date.getTime());
				    
					if ( reservationDao.trouver( dateresa, temp ) != null ){
		                throw new FormValidationException( "Ce topo est réservé pour cette date, Merci d'en choisir une autre." );
		        }
		       
					
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				
	    }
	   
	    
		private String getValeurChamp(HttpServletRequest request, String nomChamp) {
			 String valeur = request.getParameter( nomChamp );
		        if ( valeur == null || valeur.trim().length() == 0 ) {
		            return null;
		        } else {
		            return valeur;
		        }
		
		}
		
		private void setErreur( String champ, String message ) {
	        erreurs.put( champ, message );
	    }
		
	
}
