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

public class SecteurDaoImpl implements SecteurDao {
	
	private static final String SQL_INSERT = "INSERT INTO secteur(id_site, nom_secteur) VALUES(?, ?)";
    private static final String SQL_SELECT = "SELECT * FROM secteur ORDER BY id_secteur";
    private static final String SQL_SELECT_PAR_ID = "SELECT * FROM secteur WHERE id_secteur = ?";
    private static final String SQL_SELECT_PAR_NOM = "SELECT * FROM secteur WHERE nom_secteur = ?";
   
    private  DaoFactory  daoFactory;
    
	public SecteurDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Secteur secteur) throws DaoException {
		    Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet valeursAutoGenerees = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
	            		// secteur.getSite().getIdsite(),
	            		 secteur.getNomsect());
	            
	            int statut = preparedStatement.executeUpdate();
	            if ( statut == 0 ) {
	                throw new DaoException( "Échec de la création du secteur, aucune ligne ajoutée dans la table." );
	            }
	            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	            if ( valeursAutoGenerees.next() ) {
	            	secteur.setIdsect( valeursAutoGenerees.getLong( 1 ) );
	                
	            } else {
	                throw new DaoException( "Échec de la création du secteur en base, aucun ID auto-généré retourné." );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	        }

	}
	
	 private Secteur trouver( String sql, Object... objets ) throws DaoException {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        Secteur secteur = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
	            resultSet = preparedStatement.executeQuery();
	           
	            if ( resultSet.next() ) {
	                secteur = map( resultSet );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return secteur;
	    }
 

	@Override
	public Secteur trouver(long id_sect) throws DaoException {
		return trouver( SQL_SELECT_PAR_ID, id_sect);
	}
	
	@Override
	public Secteur trouver(String nomsect) throws DaoException {
		return trouver( SQL_SELECT_PAR_NOM, nomsect);
	}


	@Override
	public List<Secteur> lister() throws DaoException {
		    Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        List<Secteur> secteurs = new ArrayList<Secteur>();

	        try {
	            connection = daoFactory.getConnection();
	            preparedStatement = connection.prepareStatement( SQL_SELECT );
	            resultSet = preparedStatement.executeQuery();
	            while ( resultSet.next() ) {
	            	secteurs.add( map( resultSet ) );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connection );
	        }

	        return secteurs;
	}

	
	 private  Secteur map( ResultSet resultSet ) throws SQLException {
	        Secteur secteur = new Secteur();
	        secteur.setIdsect(resultSet.getLong( "id_secteur"));
	        secteur.setNomsect(resultSet.getString( "nom_secteur"));
	        
	        SiteDao siteDao = daoFactory.getSiteDao();
	       // secteur.setSite(siteDao.trouver(resultSet.getLong("id_site")));
	        return secteur;
	    }
}
