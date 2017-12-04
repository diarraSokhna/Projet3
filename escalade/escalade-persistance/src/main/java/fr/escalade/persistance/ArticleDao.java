package fr.escalade.persistance;

import java.util.List;

import fr.escalade.beans.Article;

public interface ArticleDao {

	
	 void creer( Article article ) throws DaoException;

	 Article trouver( String titre) throws DaoException;
	 
	 Article trouver( Long id_art) throws DaoException;
	    
	 List<Article> lister() throws DaoException;
	 
	 void supprimer( Article article ) throws DaoException;
	 
	 int count(int idArt) throws DaoException;
	    
}
