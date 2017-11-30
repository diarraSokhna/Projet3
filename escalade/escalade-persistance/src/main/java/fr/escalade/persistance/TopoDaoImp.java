package fr.escalade.persistance;

import static fr.escalade.persistance.DaoUtilitaire.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.escalade.beans.Topo;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.TopoDao;


public class TopoDaoImp implements TopoDao{
	
    private static final String SQL_INSERT = "INSERT INTO topo(nom, description, nbr_page, id_user, image) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_SELECT = "SELECT id_topo, nom, description, nbr_page, id_user, image FROM topo ORDER BY id_topo";
    private static final String SQL_SELECT_PAR_NOM = "SELECT id_topo, nom,description, nbr_page, id_user, image FROM topo WHERE nom = ?";
    
     private DaoFactory daoFactory;
     
     //constructeur avec argument
	 TopoDaoImp(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
   
	 /* Implémentation de la méthode définie dans l'interface TopoDao */
	    
	    public void creer( Topo topo ) throws DaoException {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet valeursAutoGenerees = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
	                    topo.getNom(), 
	                    topo.getDescription(), 
	                    topo.getNbpage(), 
	                    topo.getIduser(), 
	                    topo.getImage() );
	            int statut = preparedStatement.executeUpdate();
	            if ( statut == 0 ) {
	                throw new DaoException( "Échec de la création du topo, aucune ligne ajoutée dans la table." );
	            }
	            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	            if ( valeursAutoGenerees.next() ) {
	                topo.setIdtopo( valeursAutoGenerees.getLong( 1 ) );
	            } else {
	                throw new DaoException( "Échec de la création du topo en base, aucun ID auto-généré retourné." );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	        }
	    }

	  
	    public List<Topo> lister() throws DaoException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        List<Topo> topos = new ArrayList<Topo>();

	        try {
	            connection = daoFactory.getConnection();
	            preparedStatement = connection.prepareStatement( SQL_SELECT );
	            resultSet = preparedStatement.executeQuery();
	            while ( resultSet.next() ) {
	                topos.add( map( resultSet ) );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connection );
	        }

	        return topos;
	    }
	    
	    /*
	     * Simple méthode utilitaire permettant de faire la correspondance (le
	     * mapping) entre une ligne issue de la table des topos (un ResultSet) et
	     * un bean Topo.
	     */
	    private static Topo map( ResultSet resultSet ) throws SQLException {
	        Topo topo = new Topo();
	        
	        topo.setIdtopo(resultSet.getLong( "id_topo"));
	        topo.setNom(resultSet.getString( "nom"));
	        topo.setDescription(resultSet.getString( "description"));
	        topo.setNbpage(resultSet.getInt( "nbr_page"));
	        topo.setIduser(resultSet.getInt( "id_user"));
	        topo.setImage( resultSet.getString( "image" ) );
	       
	        return topo;
	    }
      
		public Topo trouver(String nom) throws DaoException {
			
		    Connection connexion = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;
		    Topo topo = null;

		    try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = daoFactory.getConnection();
		        preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_NOM, false, nom );
		        resultSet = preparedStatement.executeQuery();
		        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
		        if ( resultSet.next() ) {
		            topo = map( resultSet );
		        }
		    } catch ( SQLException e ) {
		        throw new DaoException( e );
		    } finally {
		        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		    }

		    return topo;
		}
	    
	 


}
