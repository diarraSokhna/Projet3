package fr.escalade.persistance;

import java.util.List;

import fr.escalade.beans.Pays;
import fr.escalade.beans.Ville;

public interface VilleDao {
	
	void creer(Ville ville) throws DaoException;
	
	Ville trouver (long id_ville) throws DaoException;
	
	Ville trouverpar (long id_pays) throws DaoException;
	
	List<Ville> lister() throws DaoException;
	
	List<Ville> lister(long idpays) throws DaoException;
	
	Ville trouver(String nomville) throws DaoException;

}
