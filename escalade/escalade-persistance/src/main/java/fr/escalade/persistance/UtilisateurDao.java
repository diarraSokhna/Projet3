package fr.escalade.persistance;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.escalade.beans.Utilisateur;

public interface UtilisateurDao extends CrudRepository<Utilisateur , Long>{

	   void creer( Utilisateur utilisateur ) throws DaoException;

	    Utilisateur trouver( String email ) throws DaoException;
	    
	    List<Utilisateur> lister() throws DaoException;

}
