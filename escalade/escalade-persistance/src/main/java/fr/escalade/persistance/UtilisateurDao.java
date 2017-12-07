package fr.escalade.persistance;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.escalade.beans.Utilisateur;

public interface UtilisateurDao {

	   void creer( Utilisateur utilisateur ) throws DaoException;

	    Utilisateur trouver( String email, String motpasse) throws DaoException;
	    
	    Utilisateur trouver( String email) throws DaoException;
	    
	    Utilisateur trouver( Long idUser) throws DaoException;
	    
	    List<Utilisateur> lister() throws DaoException;
	    
	    void supprimer(Utilisateur utilisateur) throws DaoException;
	    

} 
