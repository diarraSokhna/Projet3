package fr.escalade.persistance;

import java.util.List;



import fr.escalade.beans.Utilisateur;

public interface UtilisateurDao {

	   void creer( Utilisateur utilisateur ) throws DaoException;

	    Utilisateur trouverParMailPass( String email, String motpasse) throws DaoException;
	    
	    Utilisateur trouverParMail( String email) throws DaoException;
	    
	    Utilisateur trouverParId( Long idUser) throws DaoException;
	    
	    List<Utilisateur> lister() throws DaoException;
	    
	    void supprimer(Utilisateur utilisateur) throws DaoException;
	    

} 
