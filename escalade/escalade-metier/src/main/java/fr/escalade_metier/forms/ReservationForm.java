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
   
	    
	    private static final String CHAMP_UTILISATEUR = "utilisateur";
	    private static final String CHAMP_DATE = "datepicker";
	    private static final String CHOIX_TOPO    = "topo";
	
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

	         String idtopo = getValeurChamp( request, CHOIX_TOPO);
	         
	       
	         Long id_topo = Long.parseLong(idtopo);
		     Topo topo = topoDao.trouver(id_topo);
	         
		     Reservation reservation = new Reservation();
		     
		     SimpleDateFormat formatter = new SimpleDateFormat("yyy-MMM-dd");
		     Date datepicher;
			try {
				datepicher = formatter.parse(getValeurChamp( request, CHAMP_DATE ));
				 reservation.setDate_resa(datepicher);
			} catch (ParseException e1) {
				
				e1.printStackTrace();
			}
		        
		     
	        
	         
		      
	         if ( topo == null ) {
		            setErreur( CHOIX_TOPO, "Merci de choisir un topo.");
		            }
	         
	         if ( utilisateur == null ) {
		            setErreur( CHAMP_UTILISATEUR, "Merci de choisir un utilisateur.");
		            }
	       
	         
//	         traiterDate( datepicher, reservation );
	         
	        
	       
	        try {
	            if ( erreurs.isEmpty() ) {
	            	
	              reservationDao.creer( reservation );
	                
	                resultat = "Succès de la création de la réservation.";
	            } else {
	                resultat = "Échec de la création de la réservation.";
	            }
	        } catch ( DaoException e ) {
	            setErreur( "imprévu", "Erreur imprévue lors de la création." );
	            resultat = "Échec de la création de la réservation : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	            e.printStackTrace();
	        }

	        return reservation;
	    }
	    
//	    private void traiterDate(String datepicher, Reservation reservation) {
// 			
// 			Date date= null;
// 			   try {
// 				  date= validationDate( datepicher );
// 		        } catch ( FormValidationException e ) {
// 		            setErreur( CHAMP_DATE, e.getMessage() );
// 		        }
// 		        reservation.setDate_resa(date);
// 			
// 		}
//	    
//	private Date validationDate(String datepicher) throws FormValidationException {
//			
//		    Date temp;
//	        if ( datepicher != null ) {
//	            try {
//	                temp = Date.parse(datepicher);
//	                if ( temp < 0 ) {
//	                    throw new FormValidationException( "Le nombre de page doit être un nombre positif." );
//	                }
//	            } catch ( NumberFormatException e ) {
//	                temp = 0;
//	                throw new FormValidationException( "Le nombre de page doit être un nombre." );
//	            }
//	        } else {
//	            temp = 0;
//	            throw new FormValidationException( "Merci d'entrer le nombre de page." );
//	        }
//	        return temp;
//	    }
//			
	    
	    
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
