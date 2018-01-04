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
import fr.escalade.beans.Secteur;
import fr.escalade.beans.Site;
import fr.escalade.beans.Ville;

public class VilleDaoImpl implements VilleDao {

	private static final String SQL_INSERT = "INSERT INTO ville(id_pays, nom_ville, cp) VALUES(?, ?, ?)";
    private static final String SQL_SELECT = "SELECT * FROM ville ORDER BY id_ville";
    private static final String SQL_SELECT_PAR_ID = "SELECT * FROM ville WHERE id_ville = ?";
    private static final String SQL_SELECT_PAR_NOM = "SELECT * FROM ville WHERE nom_ville = ?";
    private static final String SQL_SELECT_PAR_PAYS = "SELECT * FROM ville WHERE id_pays = ?";
    
    private  DaoFactory  daoFactory;
    
	public VilleDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Ville ville) throws DaoException {
			Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet valeursAutoGenerees = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
	            		ville.getPays().getIdpays(),
	            		ville.getNom_ville(),
	            		ville.getCp());
	            
	            int statut = preparedStatement.executeUpdate();
	            if ( statut == 0 ) {
	                throw new DaoException( "Échec de la création de la ville, aucune ligne ajoutée dans la table." );
	            }
	            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	            if ( valeursAutoGenerees.next() ) {
	            	ville.setId_ville( valeursAutoGenerees.getLong( 1 ) );
	                
	            } else {
	                throw new DaoException( "Échec de la création de la ville en base, aucun ID auto-généré retourné." );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	        }

	}
	
	 private Ville trouver( String sql, Object... objets ) throws DaoException {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        Ville ville = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
	            resultSet = preparedStatement.executeQuery();
	           
	            if ( resultSet.next() ) {
	                ville = map( resultSet );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return ville;
	    }

	 @Override
		public Ville trouverpar(long id_pays) throws DaoException {
		
	       Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        Ville ville = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_PAYS, false, id_pays );
	            resultSet = preparedStatement.executeQuery();
	           
	            if ( resultSet.next() ) {
	                ville = map( resultSet );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return ville;
		 
		}


	
	
	@Override
	public Ville trouver(long id_ville) throws DaoException {
		return trouver(SQL_SELECT_PAR_ID, id_ville);
	}

	@Override
	public List<Ville> lister() throws DaoException {
	    Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Ville> villes = new ArrayList<Ville>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
            	villes.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DaoException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return villes;
	}

	@Override
	public Ville trouver(String nomville) throws DaoException {
		return trouver(SQL_SELECT_PAR_NOM, nomville);
	}
	
	 private  Ville map( ResultSet resultSet ) throws SQLException {
	        Ville ville = new Ville();
	        ville.setId_ville(resultSet.getLong( "id_ville"));
	        ville.setNom_ville(resultSet.getString( "nom_ville"));
	        
	        PaysDao paysDao = daoFactory.getPaysDao();
	        ville.setPays(paysDao.trouver(resultSet.getLong("id_pays")));
	        
	        ville.setCp(resultSet.getInt("cp"));
	        
	        return ville;
	    }

	@Override
	public List<Ville> lister(long idpays) throws DaoException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Ville> villes = new ArrayList<Ville>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connection, SQL_SELECT_PAR_PAYS, false, idpays);
                    resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
            	villes.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DaoException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return villes;
	}

	
}
