package fr.escalade.persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.sql.DataSource;

import fr.escalade.beans.Topo;
import static fr.escalade.persistance.DaoUtilitaire.*;

@Named
public class TopoDaoImp extends AbstractDaoImpl implements TopoDao{
	
    private static final String SQL_INSERT = "INSERT INTO topo(nom, description, nbr_page, id_user, image) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_SELECT = "SELECT id_topo, nom, description, nbr_page, id_user, image FROM topo ORDER BY id_topo";
    
    
    private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
//     private DaoFactory daoFactory;
//     
	
     //constructeur avec argument
	public TopoDaoImp(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	 /* Implémentation de la méthode définie dans l'interface TopoDao */
	   
	    public TopoDaoImp() {
	}

		public void creer( Topo topo ) throws DaoException {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet valeursAutoGenerees = null;

	        try {
	            connexion = dataSource.getConnection();
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
	            connection = dataSource.getConnection();
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
	    
	 
	 
	 
//		public void crer(Topo topo) throws DaoException {
//			Connection conn = null;
//			PreparedStatement preparedStatement = null;
//			String sql;
//			File file = new File("");
//			FileInputStream fis = null;
//			try {
//				fis = new FileInputStream(file);
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			
//			try {
//				conn = daoFactory.getConnection();
//			    sql = "INSERT INTO topo(nom,description,nbr_page,id_user) VALUES(?,?,?,?)";
//			    preparedStatement = conn.prepareStatement(sql);
//			    preparedStatement.setString(1, topo.getNom());
//			    preparedStatement.setString(2, topo.getDescription());
//			    preparedStatement.setInt(3, topo.getNbpage());
//			    
////			    preparedStatement.setBinaryStream(4, fis, file.length());
//			    preparedStatement.setInt(4, topo.getIduser());
////			    preparedStatement.setString(6, file.getName());
//			    
//			  //on execute la mise a jour des donnees
//			    preparedStatement.executeUpdate();
//	            conn.commit();
//	        } catch (SQLException e) {
//	            try {
//	                if (conn != null) {
//	                    conn.rollback();
//	                }
//	            } catch (SQLException e2) {
//	            }
//	            throw new DaoException("Impossible de communiquer avec la base de données");
//	        }
//	        finally {
//	            try {
//	                if (conn != null) {
//	                    conn.close();  
//	                }
//	            } catch (SQLException e) {
//	                throw new DaoException("Impossible de communiquer avec la base de données");
//	            }
//	        }
//			
//		}


//	public List<Topo> lister() throws DaoException {
//		List<Topo> topos = new ArrayList<Topo>();
//		Connection conn = null;
//		Statement statement = null;
//		ResultSet resultSet = null;
//		String sql;
//		
//		
//		try {
//			
//			sql="Select nom,description,nbr_page from topo";
//		    conn = daoFactory.getConnection();
//	        statement = conn.createStatement();
//		    resultSet = statement.executeQuery(sql);
//		   
//		    //r�cup�ration des donn�es
//	    while (resultSet.next()){
//		     String nom = resultSet.getString("nom");
//		     String description = resultSet.getString("description");
//		     int nbpage = resultSet.getInt("nbr_page");
//		     
//		     //on  r�e un objet utilisateur ou on stocke les donn�es recup�r� depuis notre table
//		      Topo topo =  new Topo();
//		      topo.setNom(nom);
//		      topo.setDescription(description);
//		      topo.setNbpage(nbpage);
//		      
//		      //on ajoute les utilisateurs ds notre liste 
//		       topos.add(topo);
//			}
//		} catch (SQLException e) {
//			throw new DaoException("Impossible de communiquer avec la base de donn�es");
//		} finally {
//			try {
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				throw new DaoException("Impossible de communiquer avec la base de donn�es");
//			}
//		}
//		return topos;
//	}

}
