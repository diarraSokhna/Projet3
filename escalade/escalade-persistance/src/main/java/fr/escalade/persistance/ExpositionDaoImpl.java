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
import fr.escalade.beans.Exposition;

public class ExpositionDaoImpl implements ExpositionDao{
    
	   private static final String SQL_SELECT_PAR_LIBELLE = "SELECT * FROM exposition WHERE libelle_expo = ?";
	   private static final String SQL_SELECT_PAR_ID = "SELECT * FROM exposition WHERE id_expo = ?";
	   private static final String SQL_SELECT = "SELECT * FROM exposition ORDER BY id_expo";
	   private static final String SQL_INSERT = "INSERT INTO exposition (libelle_expo) VALUES (?)";
	   private static final String SQL_DELETE_PAR_ID = "DELETE FROM exposition WHERE id_expo = ?";
	   
	   private  DaoFactory  daoFactory;
	   
   public ExpositionDaoImpl(DaoFactory daoFactory) {
	   this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Exposition exposition) throws DaoException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
      
        try {
            
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, 
            		exposition.getLibelle_expo());
                    
            int  statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DaoException( "Échec de la création de l'exposition, aucune ligne ajoutée dans la table." );
            }
            
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
               exposition.setId_expo( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DaoException( "Échec de la création de l'exposition en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DaoException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
		
	}
	
	 private Exposition trouver( String sql, Object... objets ) throws DaoException {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        Exposition exposition = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
	            resultSet = preparedStatement.executeQuery();
	           
	            if ( resultSet.next() ) {
	                exposition = map( resultSet );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return exposition;
	    }
	 
	
	@Override
	public Exposition trouver(String libelle) throws DaoException {
		return trouver( SQL_SELECT_PAR_LIBELLE, libelle);
	}

	@Override
	public Exposition trouver(Long id_expo) throws DaoException {
		return trouver( SQL_SELECT_PAR_ID, id_expo);
	}

	@Override
	public List<Exposition> lister() throws DaoException {
	    Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Exposition> expositions = new ArrayList<Exposition>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
            	expositions.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DaoException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return expositions;
	}

	@Override
	public void supprimer(Exposition exposition) throws DaoException {
	    Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, exposition.getId_expo());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DaoException( "Échec de la suppression de l'exposion, aucune ligne supprimée de la table." );
            } else {
                exposition.setId_expo(0);	
            }
        } catch ( SQLException e ) {
            throw new DaoException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
		
	}
	
	private  Exposition map( ResultSet resultSet ) throws SQLException {
		Exposition exposition = new Exposition();
		exposition.setId_expo( resultSet.getLong("id_expo"));
		exposition.setLibelle_expo( resultSet.getString( "libelle_expo" ));
	    
	    return exposition;
	}

	

}
