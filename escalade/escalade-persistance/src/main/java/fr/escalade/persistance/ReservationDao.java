package fr.escalade.persistance;

import java.util.Date;
import java.util.List;

import fr.escalade.beans.Reservation;

public interface ReservationDao {

	void creer(Reservation reservation) throws DaoException;

	List<Reservation> lister() throws DaoException;

	List<Reservation> lister(long id_user) throws DaoException;

	Reservation trouver(Date dateresa, long id_resa) throws DaoException;

	Reservation trouver(long id_resa) throws DaoException;

	void supprimer(Reservation reservation) throws DaoException;

}
