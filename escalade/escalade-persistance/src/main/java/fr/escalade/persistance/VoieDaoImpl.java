package fr.escalade.persistance;

import static fr.escalade.persistance.DaoUtilitaire.fermeturesSilencieuses;
import static fr.escalade.persistance.DaoUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.escalade.beans.Secteur;
import fr.escalade.beans.Site;
import fr.escalade.beans.Voie;

public class VoieDaoImpl implements VoieDao {

	private static final String SQL_INSERT = "INSERT INTO voie(id_secteur, id_cotation, nom_voie, altitude, nbr_longueur,id_expo) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT = "SELECT * FROM voie ORDER BY id_voie";
    private static final String SQL_SELECT_PAR_ID = "SELECT * FROM voie WHERE id_voie = ?";
    private static final String SQL_SELECT_PAR_NOM = "SELECT * FROM voie WHERE nom_voie = ?";
    private static final String SQL_SELECT_PAR_SECTEUR = "SELECT * FROM  voie v, secteur s WHERE v.id_secteur=s.id_secteur AND v.id_secteur=?";
   
    private  DaoFactory  daoFactory;
    
	public VoieDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Voie voie, Secteur secteur) throws DaoException {
		    Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet valeursAutoGenerees = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
	            		secteur.getIdsect(),
	            		voie.getCotation().getIdcot(),
	                    voie.getNom_voie(),
	                    voie.getAltitude(),
	                    voie.getNbr_longueur(),
	                    voie.getExposition().getId_expo());
	            
	            int statut = preparedStatement.executeUpdate();
	            if ( statut == 0 ) {
	                throw new DaoException( "Échec de la création de la voie, aucune ligne ajoutée dans la table." );
	            }
	            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	            if ( valeursAutoGenerees.next() ) {
	            	voie.setId_voie( valeursAutoGenerees.getLong( 1 ) );
	                
	            } else {
	                throw new DaoException( "Échec de la création de la voie en base, aucun ID auto-généré retourné." );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	        }

	}
	
	 private Voie trouver( String sql, Object... objets ) throws DaoException {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        Voie voie = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
	            resultSet = preparedStatement.executeQuery();
	           
	            if ( resultSet.next() ) {
	            	voie = map( resultSet );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return voie;
	    }

	@Override
	public Voie trouver(long id_voie) throws DaoException {
		return trouver( SQL_SELECT_PAR_ID, id_voie);
	}

	@Override
	public Voie trouver(String nomvoie) throws DaoException {
		return trouver( SQL_SELECT_PAR_NOM, nomvoie);
	}
	
	@Override
	public List<Voie> lister() throws DaoException {
		  Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        List<Voie> voies = new ArrayList<Voie>();

	        try {
	            connection = daoFactory.getConnection();
	            preparedStatement = connection.prepareStatement( SQL_SELECT );
	            resultSet = preparedStatement.executeQuery();
	            while ( resultSet.next() ) {
	            	voies.add( map( resultSet ) );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connection );
	        }

	        return voies;
	}

	@Override
	public List<Voie> lister(long idsect) throws DaoException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Voie> voies = new ArrayList<Voie>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connection, SQL_SELECT_PAR_SECTEUR, false, idsect);
                    resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
            	voies.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DaoException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return voies;
	}

	   private  Voie map( ResultSet resultSet ) throws SQLException {
		    Voie voie = new Voie();
	        voie.setId_voie(resultSet.getLong( "id_voie"));
	        
	        SecteurDao secteurDao =daoFactory.getSecteurDao();
	        voie.setSecteur(secteurDao.trouver(resultSet.getLong("id_secteur")));
	        
            CotationDao cotationDao =  daoFactory.getCotationDao();
	        voie.setCotation(cotationDao.trouver(resultSet.getLong("id_cotation")));
	        
	        voie.setNom_voie(resultSet.getString( "nom_voie"));
	        voie.setAltitude(resultSet.getFloat("altitude"));
	        voie.setNbr_longueur(resultSet.getInt("nbr_longueur"));
	        
	        ExpositionDao expositionDao = daoFactory.getExpositionDao();
	        voie.setExposition(expositionDao.trouver(resultSet.getLong("id_expo")));
	        
	        return voie;
	    }

}
