package fr.escalade.persistance;

import static fr.escalade.persistance.DaoUtilitaire.fermeturesSilencieuses;
import static fr.escalade.persistance.DaoUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.escalade.beans.Pays;

public class PaysDaoImpl  implements PaysDao{
	    private static final String SQL_INSERT = "INSERT INTO pays(nom_pays) VALUES(?)";
	    private static final String SQL_SELECT = "SELECT * FROM pays ORDER BY id_pays";
	    private static final String SQL_SELECT_PAR_ID = "SELECT id_pays, nom_pays FROM pays WHERE id_pays = ?";
	    private static final String SQL_SELECT_PAR_NOM = "SELECT id_pays, nom_pays FROM pays WHERE nom_pays = ?";
	    private static final String SQL_DELETE_PAR_ID = "DELETE FROM pays WHERE id_pays = ?";
		
	    private DaoFactory daoFactory;
	public PaysDaoImpl(DaoFactory daoFactory) {
			this.daoFactory = daoFactory;
		}

	public void creer(Pays pays) throws DaoException {
		 Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet valeursAutoGenerees = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, pays.getNompays());
	            int statut = preparedStatement.executeUpdate();
	            if ( statut == 0 ) {
	                throw new DaoException( "Échec de l'ajout du pays, aucune ligne ajoutée dans la table." );
	            }
	            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	            if ( valeursAutoGenerees.next() ) {
	                pays.setIdpays( valeursAutoGenerees.getLong( 1 ));
	            } else {
	                throw new DaoException( "Échec de l'ajout du pays en base, aucun ID auto-généré retourné." );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	        }
		
	}
	
	 private Pays trouver( String sql, Object... objets ) throws DaoException {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        Pays pays = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
	            resultSet = preparedStatement.executeQuery();
	           
	            if ( resultSet.next() ) {
	                pays = map( resultSet );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return pays;
	    }
	 
	 
	 public Pays trouver(Long id_pays) throws DaoException {
		 return trouver( SQL_SELECT_PAR_ID, id_pays);
		}
	 
	 @Override
		public Pays trouver(String nom_pays) throws DaoException {
		 return trouver( SQL_SELECT_PAR_NOM, nom_pays);
		}

	public List<Pays> lister() throws DaoException {
	      Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        List<Pays> payss = new ArrayList<Pays>();

	        try {
	            connection = daoFactory.getConnection();
	            preparedStatement = connection.prepareStatement( SQL_SELECT );
	            resultSet = preparedStatement.executeQuery();
	            while ( resultSet.next() ) {
	                payss.add( map( resultSet ) );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connection );
	        }

	        return payss;
	}

	public void supprimer(Pays pays) throws DaoException {
		    Connection connexion = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, pays.getIdpays());
	            int statut = preparedStatement.executeUpdate();
	            if ( statut == 0 ) {
	                throw new DaoException( "Échec de la suppression du topo, aucune ligne supprimée de la table." );
	            } else {
	                pays.setIdpays(0);	
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( preparedStatement, connexion );
	        }
		
		
	}

	 private  Pays map( ResultSet resultSet ) throws SQLException {
	        Pays pays = new Pays();
	        pays.setIdpays(resultSet.getLong( "id_pays"));
	        pays.setNompays(resultSet.getString( "nom_pays"));
	       
	        return pays;
	    }

	
	
	

}
