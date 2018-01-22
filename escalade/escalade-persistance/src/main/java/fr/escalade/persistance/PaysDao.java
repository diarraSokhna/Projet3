package fr.escalade.persistance;

import java.util.List;

import fr.escalade.beans.Pays;

public interface PaysDao {
	
	void creer(Pays pays) throws DaoException;

	List<Pays> lister() throws DaoException;

	void supprimer(Pays pays) throws DaoException;

	Pays trouver(Long id_pays) throws DaoException;

	Pays trouver(String nom_pays) throws DaoException;
}
