package fr.escalade.persistance;

import static fr.escalade.persistance.DaoUtilitaire.fermeturesSilencieuses;
import static fr.escalade.persistance.DaoUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.escalade.beans.Article;

public class ArticleDaoImpl implements ArticleDao{
	
	
	   private static final String SQL_SELECT_PAR_TITRE = "SELECT * FROM article WHERE titre = ?";
	   private static final String SQL_SELECT = "SELECT * FROM article ORDER BY id_art";
	   private static final String SQL_INSERT = "INSERT INTO article (date, titre, contenu, id_user, photoa) VALUES (NOW(), ?, ?, 13, ?)";
	   private static final String SQL_COUNT = "SELECT COUNT(*) from article a,commentaire c where a.id_art=? AND a.id_art=c.id_art";
	   
	   
	   
	   private DaoFactory  daoFactory;
	   private Integer rowCount;
	   
	public ArticleDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void creer(Article article) throws DaoException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
      

        try {
            
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, 
            		article.getTitre(),
            		article.getContenu(),
            		article.getPhoto());
                    
            int  statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DaoException( "Échec de la création de l'article, aucune ligne ajoutée dans la table." );
            }
            
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
               article.setId_art( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DaoException( "Échec de la création de l'article en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DaoException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    	
	}

	public Article trouver(String titre) throws DaoException {
		 Connection connexion = null;
 	    PreparedStatement preparedStatement = null;
 	    ResultSet resultSet = null;
 	    Article article = null;

 	    try {
 	        /* Récupération d'une connexion depuis la Factory */
 	        connexion = daoFactory.getConnection();
             preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_TITRE, false, titre );
 	        resultSet = preparedStatement.executeQuery();
 	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
 	        if ( resultSet.next() ) {
 	            article = map( resultSet );
 	        }
 	    } catch ( SQLException e ) {
 	        throw new DaoException( e );
 	    } finally {
 	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
 	    }

 	    return article;
	}

	public List<Article> lister() throws DaoException {
		 Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        List<Article> articles = new ArrayList<Article>();

	        try {
	            connection = daoFactory.getConnection();
	            preparedStatement = connection.prepareStatement( SQL_SELECT );
	            resultSet = preparedStatement.executeQuery();
	            while ( resultSet.next() ) {
	                articles.add( map( resultSet ) );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connection );
	        }

	        return articles;
	}
	
	public int count(int idArt) throws DaoException {
		
	    Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
         
 
        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connection, SQL_COUNT, true, idArt);
            resultSet = preparedStatement.executeQuery();
             
             rowCount = resultSet.getInt(1);
             
             
        } catch ( SQLException e ) {
            throw new DaoException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }
 
        return rowCount;
}
	
	private static Article map( ResultSet resultSet ) throws SQLException {
		Article article = new Article();
		article.setId_art( resultSet.getLong("id_art"));
		article.setDatepubli( resultSet.getDate( "date" ));
	    article.setTitre( resultSet.getString( "titre" ));
	    article.setContenu( resultSet.getString( "contenu" ));
	    article.setId_user( resultSet.getLong( "id_user" ) );
	    article.setPhoto( resultSet.getString( "photoa" ) );
	    
	    return article;
	}

	

}
