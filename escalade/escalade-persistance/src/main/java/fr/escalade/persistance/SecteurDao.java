package fr.escalade.persistance;

import java.util.List;

import fr.escalade.beans.Secteur;

public interface SecteurDao {

	void creer(Secteur secteur) throws DaoException;
	
	Secteur trouver (long id_sect) throws DaoException;
	
	List<Secteur> lister() throws DaoException;
	
	Secteur trouver(String nomsect) throws DaoException;
	 
}
