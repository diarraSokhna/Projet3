package fr.escalade.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.escalade.beans.Utilisateur;
import static fr.escalade.persistance.DaoUtilitaire.*;

public class UtilisateurDaoImp implements UtilisateurDao{

	   private DaoFactory  daoFactory;
	   private static final String SQL_SELECT_PAR_EMAIL = "SELECT id_user, nom, prenom, adresse, tel, email, passw, dateinscription FROM Utilisateur WHERE email = ?";
	   private static final String SQL_INSERT = "INSERT INTO Utilisateur (nom, prenom, adresse, tel, email, passw, dateinscription) VALUES (?, ?, ?, ?, ?, ?, NOW())";
	   
	   //pr accesder aux methodes de daofactory comm getconnection on crée un conctructeur ki prend en argument un obj daofactory
	  UtilisateurDaoImp(DaoFactory daoFactory) {
	
		this.daoFactory = daoFactory;
	}

	/* Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao */
    public Utilisateur trouver( String email ) throws DaoException {
    	   Connection connexion = null;
    	    PreparedStatement preparedStatement = null;
    	    ResultSet resultSet = null;
    	    Utilisateur utilisateur = null;

    	    try {
    	        /* Récupération d'une connexion depuis la Factory */
    	        connexion = daoFactory.getConnection();
                preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_EMAIL, false, email );
    	        resultSet = preparedStatement.executeQuery();
    	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
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
    
    /* Implémentation de la méthode creer() définie dans l'interface UtilisateurDao */
    public void creer( Utilisateur utilisateur ) throws IllegalArgumentException, DaoException {
    	
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, 
                    utilisateur.getNom(),
                    utilisateur.getPrenom(),
                    utilisateur.getAdresse(),
                    utilisateur.getTel(),
                    utilisateur.getEmail(), 
                    utilisateur.getMotpass()); 
                    
           int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DaoException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                utilisateur.setId_user( valeursAutoGenerees.getInt( 1 ) );
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
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des utilisateurs (un
	 * ResultSet) et un bean Utilisateur.
	 */
	private static Utilisateur map( ResultSet resultSet ) throws SQLException {
	    Utilisateur utilisateur = new Utilisateur();
	    utilisateur.setId_user( resultSet.getInt("id_user"));
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
