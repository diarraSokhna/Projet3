package fr.escalade.persistance;

import java.util.List;


import fr.escalade.beans.Commentaire;

public interface CommentaireDao {
	
	
	 void laisser( Commentaire commentaire ) throws DaoException;
	 
	 List<Commentaire> lister(Long idArt) throws DaoException;
	 
	 void supprimer( Commentaire commentaire ) throws DaoException;
	 
	 

}
