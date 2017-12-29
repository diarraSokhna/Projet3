package fr.escalade.persistance;

import static fr.escalade.persistance.DaoUtilitaire.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.escalade.beans.Topo;
import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.UtilisateurDao;


public class UtilisateurDaoImp implements UtilisateurDao{

	   private DaoFactory  daoFactory;
	   private static final String SQL_SELECT_PAR_EMAIL_PASS = "SELECT * FROM Utilisateur WHERE email = ? and passw=?";
	   private static final String SQL_SELECT_PAR_EMAIL = "SELECT * FROM utilisateur WHERE email = ?";
      private static final String SQL_SELECT = "SELECT * FROM Utilisateur ORDER BY id_user";
	   private static final String SQL_SELECT_PAR_ID = "SELECT * FROM Utilisateur WHERE id_user = ?";
	   private static final String SQL_INSERT = "INSERT INTO Utilisateur (nom, prenom, adresse, tel, email, passw, dateinscription, id_role) VALUES (?, ?, ?, ?, ?, ?, NOW(), 3)";
	   private static final String SQL_DELETE_PAR_ID = "DELETE FROM utilisateur WHERE id_user = ?";
	   
	    UtilisateurDaoImp(DaoFactory daoFactory) {
	
		this.daoFactory = daoFactory;
	}

    public void creer( Utilisateur utilisateur ) throws IllegalArgumentException, DaoException {
    	
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, 
                    utilisateur.getNom(),
                    utilisateur.getPrenom(),
                    utilisateur.getAdresse(),
                    utilisateur.getTel(),
                    utilisateur.getEmail(), 
                    utilisateur.getMotpass()); 
                    
           int statut = preparedStatement.executeUpdate();
           if ( statut == 0 ) {
                throw new DaoException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
           valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                utilisateur.setId_user( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DaoException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DaoException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    	
    }

	public List<Utilisateur> lister() throws DaoException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

	        try {
	            connection = daoFactory.getConnection();
	            preparedStatement = connection.prepareStatement( SQL_SELECT );
	            resultSet = preparedStatement.executeQuery();
	            while ( resultSet.next() ) {
	                utilisateurs.add( map( resultSet ) );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connection );
	        }

	        return utilisateurs;
	}


	
	public Utilisateur trouver(Long idUser) throws DaoException {
		 return trouver( SQL_SELECT_PAR_ID, idUser );
	}
	
	
	  
	public Utilisateur trouver( String email, String motpasse ) throws DaoException {
			 return trouver( SQL_SELECT_PAR_EMAIL_PASS, email, motpasse);
	    }
	
	public Utilisateur trouver(String email) throws DaoException {
		   return trouver( SQL_SELECT_PAR_EMAIL, email);
	}

	  private Utilisateur trouver( String sql, Object... objets ) throws DaoException {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        Utilisateur utilisateur = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
	            resultSet = preparedStatement.executeQuery();
	           
	            if ( resultSet.next() ) {
	                utilisateur = map( resultSet );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return utilisateur;
	    }
	
	
	public void supprimer(Utilisateur utilisateur) throws DaoException {
		  Connection connexion = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, utilisateur.getIduser() );
	            int statut = preparedStatement.executeUpdate();
	            if ( statut == 0 ) {
	                throw new DaoException( "Échec de la suppression de l'utilsateur, aucune ligne supprimée de la table." );
	            } else {
	                utilisateur.setId_user( null );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( preparedStatement, connexion );
	        }
	}
	
	

    private static Utilisateur map( ResultSet resultSet ) throws SQLException {
	    Utilisateur utilisateur = new Utilisateur();
	    utilisateur.setId_user( resultSet.getLong("id_user"));
	    utilisateur.setNom( resultSet.getString( "nom" ) );
	    utilisateur.setPrenom( resultSet.getString( "prenom" ) );
	    utilisateur.setAdresse( resultSet.getString( "adresse" ) );
	    utilisateur.setTel( resultSet.getInt( "tel" ) );
	    utilisateur.setEmail( resultSet.getString( "email" ) );
	    utilisateur.setMotpass( resultSet.getString( "passw" ) );
	    utilisateur.setDateinscription( resultSet.getTimestamp( "dateinscription" ) );
	    
	    return utilisateur;
	}

	

	

	
	
}
