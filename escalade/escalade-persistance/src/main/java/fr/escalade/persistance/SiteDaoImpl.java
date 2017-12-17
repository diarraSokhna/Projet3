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
import fr.escalade.beans.Pays;
import fr.escalade.beans.Site;

public class SiteDaoImpl implements SiteDao{
    
	private static final String SQL_INSERT = "INSERT INTO site(nom_site, id_pays, image,id_class) VALUES(?, ?, ?, ?)";
    private static final String SQL_SELECT = "SELECT * FROM site ORDER BY id_site";
    private static final String SQL_SELECT_PAR_ID = "SELECT * FROM site WHERE id_site = ?";
    private static final String SQL_SELECT_PAR_NOM = "SELECT * FROM site WHERE nom_site = ?";
    private static final String SQL_SELECT_PAR_PAYS = "SELECT * FROM  site s, pays p WHERE s.id_pays=p.id_pays AND s.id_pays=?";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM site WHERE id_site = ?";
	
    private  DaoFactory  daoFactory;
    
	public SiteDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public Site creer(Site site) throws DaoException {
		    Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet valeursAutoGenerees = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
	                    site.getNomsite(),
	                    site.getPays().getIdpays(),
	                    site.getImage(),
	                    site.getClassement().getId_class());
	            
	            int statut = preparedStatement.executeUpdate();
	            if ( statut == 0 ) {
	                throw new DaoException( "Échec de la création du site, aucune ligne ajoutée dans la table." );
	            }
	            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	            if ( valeursAutoGenerees.next() ) {
	                site.setIdsite( valeursAutoGenerees.getLong( 1 ) );
	                
	            } else {
	                throw new DaoException( "Échec de la création du site en base, aucun ID auto-généré retourné." );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	        }
	        return site;
		
	}
	
	 private Site trouver( String sql, Object... objets ) throws DaoException {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        Site site = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
	            resultSet = preparedStatement.executeQuery();
	           
	            if ( resultSet.next() ) {
	                site = map( resultSet );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return site;
	    }
    
	public Site trouver(Long id_site) throws DaoException {
		return trouver( SQL_SELECT_PAR_ID, id_site);
	}
		
	@Override
	public Site trouver(String nomsite) throws DaoException {
		return trouver( SQL_SELECT_PAR_NOM, nomsite);
	}

	public List<Site> lister() throws DaoException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        List<Site> sites = new ArrayList<Site>();

	        try {
	            connection = daoFactory.getConnection();
	            preparedStatement = connection.prepareStatement( SQL_SELECT );
	            resultSet = preparedStatement.executeQuery();
	            while ( resultSet.next() ) {
	                sites.add( map( resultSet ) );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connection );
	        }

	        return sites;
	}

	@Override
	public List<Site> lister(long idpays) throws DaoException {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Site> sites = new ArrayList<Site>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connection, SQL_SELECT_PAR_PAYS, false, idpays);
                    resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
            	sites.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DaoException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return sites;
	}
	

	public void supprimer(Site site) throws DaoException {
		    Connection connexion = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, site.getIdsite());
	            int statut = preparedStatement.executeUpdate();
	            if ( statut == 0 ) {
	                throw new DaoException( "Échec de la suppression du site, aucune ligne supprimée de la table." );
	            } else {
	                site.setIdsite(0);	
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( preparedStatement, connexion );
	        }
	}


    private  Site map( ResultSet resultSet ) throws SQLException {
        Site site = new Site();
        site.setIdsite(resultSet.getLong( "id_site"));
        site.setNomsite(resultSet.getString( "nom_site"));
        
        PaysDao paysDao = daoFactory.getPaysDao();
        site.setPays(paysDao.trouver(resultSet.getLong("id_pays")));
        site.setImage(resultSet.getString("image"));
        
        ClassementDao classementDao = daoFactory.getClassementDao();
        site.setClassement(classementDao.trouver(resultSet.getLong("id_class")));
        
        return site;
    }


	
}

