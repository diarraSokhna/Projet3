package fr.escalade.persistance;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.escalade.beans.Utilisateur;

public interface UtilisateurDao {

	   void creer( Utilisateur utilisateur ) throws DaoException;

	    Utilisateur trouver( String email) throws DaoException;
	    
	    Utilisateur trouverParPasse( String motdepasse) throws DaoException;
	    
	    List<Utilisateur> lister() throws DaoException;
	    

} 
