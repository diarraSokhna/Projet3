package fr.escalade.persistance;

import java.util.List;

import fr.escalade.beans.Exposition;

public interface ExpositionDao {
	
	 void creer( Exposition exposition ) throws DaoException;

	 Exposition trouver( String libelle) throws DaoException;
	 
	 Exposition trouver( Long id_expo) throws DaoException;
	    
	 List<Exposition> lister() throws DaoException;
	 
	 void supprimer( Exposition exposition ) throws DaoException;

}
