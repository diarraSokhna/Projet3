package fr.escalade.persistance;

import static fr.escalade.persistance.DaoUtilitaire.fermeturesSilencieuses;
import static fr.escalade.persistance.DaoUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.escalade.beans.Reservation;
import fr.escalade.beans.Topo;

public class ReservationDaoImpl implements ReservationDao {

	private static final String SQL_INSERT = "INSERT INTO reservation(date_resa, id_topo, id_user) VALUES(?, ?, ?)";
    private static final String SQL_SELECT = "SELECT * FROM reservation ORDER BY id";
    private static final String SQL_SELECT_PAR_DATE_TOPO = "SELECT * FROM reservation WHERE date_resa = ? and id_topo= ?";
    private static final String SQL_SELECT_PAR_ID = "SELECT * FROM reservation WHERE id = ?";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM reservation WHERE id = ?";
	
    private DaoFactory daoFactory;
    
	public ReservationDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Reservation reservation) throws DaoException {
	    Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
            		reservation.getDate_resa(),
            		reservation.getTopo().getIdtopo(), 
            		reservation.getUtilisateur().getIduser());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DaoException( "Échec de la création de la réservation, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
            	reservation.setId_resa( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DaoException( "Échec de la création de la réservation en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DaoException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
		
	}
	
	 private Reservation trouver( String sql, Object... objets ) throws DaoException {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        Reservation reservation = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
	            resultSet = preparedStatement.executeQuery();
	           
	            if ( resultSet.next() ) {
	            	reservation = map( resultSet );
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return reservation;
	    }
	 
		@Override
		public Reservation trouver(Date dateresa, long id_topo) throws DaoException {
			return trouver (SQL_SELECT_PAR_DATE_TOPO, dateresa, id_topo);
		}

		@Override
		public Reservation trouver(long id_resa) throws DaoException {
			return trouver(SQL_SELECT_PAR_ID, id_resa);
		}
	 
		@Override
		public List<Reservation> lister() throws DaoException {
		     Connection connection = null;
		        PreparedStatement preparedStatement = null;
		        ResultSet resultSet = null;
		        List<Reservation> reservations = new ArrayList<Reservation>();

		        try {
		            connection = daoFactory.getConnection();
		            preparedStatement = connection.prepareStatement( SQL_SELECT );
		            resultSet = preparedStatement.executeQuery();
		            while ( resultSet.next() ) {
		            	reservations.add( map( resultSet ) );
		            }
		        } catch ( SQLException e ) {
		            throw new DaoException( e );
		        } finally {
		            fermeturesSilencieuses( resultSet, preparedStatement, connection );
		        }

		        return reservations;
		}

		 private  Reservation map( ResultSet resultSet ) throws SQLException {
			    Reservation reservation = new Reservation();
			    reservation.setId_resa(resultSet.getLong( "id"));
		        reservation.setDate_resa(resultSet.getDate( "date_resa"));
		        
		        UtilisateurDao utilisateurDao = daoFactory.getUtilisateurDao();
		        TopoDao topoDao = daoFactory.getTopoDao();
		        reservation.setUtilisateur(utilisateurDao.trouverParId(resultSet.getLong("id_user")));
		        reservation.setTopo(topoDao.trouver(resultSet.getLong("id_topo")));
		       
		        return reservation;
		    }

	@Override
	public void supprimer(Reservation reservation) throws DaoException {
		    Connection connexion = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, reservation.getId_resa());
	            int statut = preparedStatement.executeUpdate();
	            if ( statut == 0 ) {
	                throw new DaoException( "Échec de la suppression de la reservation, aucune ligne supprimée de la table." );
	            } else {
	            	reservation.setId_resa(0);	
	            }
	        } catch ( SQLException e ) {
	            throw new DaoException( e );
	        } finally {
	            fermeturesSilencieuses( preparedStatement, connexion );
	        }
		
	}

}
