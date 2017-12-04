package fr.escalade.persistance;

import static fr.escalade.persistance.DaoUtilitaire.fermeturesSilencieuses;
import static fr.escalade.persistance.DaoUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.escalade.beans.Commentaire;

public class CommentaireDaoImpl  implements CommentaireDao{

	   private static final String SQL_SELECT_PAR_ART = "SELECT c.id_com, libelle_com, c.id_art , c.id_user, c.date_com FROM  commentaire c, article a WHERE c.id_art=a.id_art AND c.id_art=?";
	   private static final String SQL_INSERT = "INSERT INTO commentaire (libelle_com, date_com, id_user, id_art) VALUES (?, NOW(), ?, ?)";
	   private static final String SQL_DELETE_PAR_ID = "DELETE FROM commentaire WHERE id_com = ?";
	   
	   private DaoFactory  daoFactory;
	 
	
	public CommentaireDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	

	public void laisser(Commentaire commentaire) throws DaoException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
      

        try {
            
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, 
            		commentaire.getLibelle(),
            		commentaire.getUtilisateur().getIduser(),
            		commentaire.getArticle().getId_art());
                    
            int  statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DaoException( "Échec de l'ajout du commentaire, aucune ligne ajoutée dans la table." );
            }
            
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
               commentaire.setId_com( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DaoException( "Échec de l'ajout du commentaire en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DaoException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    	
		
	}
	
	public List<Commentaire> lister(Long id_Art) throws DaoException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Commentaire> commentaires = new ArrayList<Commentaire>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connection, SQL_SELECT_PAR_ART, true, id_Art);
                    resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                commentaires.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DaoException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return commentaires;
	}
	
	private Commentaire map( ResultSet resultSet ) throws SQLException {
		Commentaire commentaire = new Commentaire();
		commentaire.setId_com( resultSet.getLong("id_com"));
		commentaire.setLibelle( resultSet.getString( "libelle_com" ));
		commentaire.setDatecom( resultSet.getDate( "date_com" ));
		
		UtilisateurDao utilisateurDao = daoFactory.getUtilisateurDao();
		commentaire.setUtilisateur(utilisateurDao.trouver(resultSet.getLong("id_user")));
		
		ArticleDao articleDao = daoFactory.getArticleDao();
	    commentaire.setArticle(articleDao.trouver(resultSet.getLong("id_art")));
	    
	    return commentaire;
	}

	public void supprimer(Commentaire commentaire) throws DaoException {
		   Connection connexion = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, commentaire.getId_com());
	            int statut = preparedStatement.executeUpdate();
	            if ( statut == 0 ) {
	                throw new DaoException( "Échec de la suppression de l'article, aucune ligne supprimée de la table." );
	            } else {
	                commentaire.setId_com(0);	
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( preparedStatement, connexion );
	        }
		
	}


	
}
