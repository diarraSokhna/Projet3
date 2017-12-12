package fr.escalade.persistance;

import java.util.Date;
import java.util.List;

import fr.escalade.beans.Reservation;


public interface ReservationDao {

	 void creer(Reservation reservation) throws DaoException;
	  
	  List<Reservation> lister() throws DaoException;
	  
	  Reservation trouver( Date dateresa ) throws DaoException;
	  
	  Reservation trouver( Long id_resa ) throws DaoException;
	  
	  void supprimer( Reservation reservation ) throws DaoException;
	
}
