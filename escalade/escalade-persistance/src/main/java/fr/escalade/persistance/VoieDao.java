package fr.escalade.persistance;

import java.util.List;

import fr.escalade.beans.Secteur;
import fr.escalade.beans.Voie;


public interface VoieDao {

    void creer(Voie voie, Secteur secteur) throws DaoException;
	
    Voie trouver (long id_voie) throws DaoException;
	
	List<Voie> lister() throws DaoException;
	
	List<Voie> lister(long idsite) throws DaoException;
	
	Voie trouver(String nomvoie) throws DaoException;
	 
}
