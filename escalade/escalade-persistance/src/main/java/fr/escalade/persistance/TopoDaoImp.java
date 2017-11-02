package fr.escalade.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.escalade.beans.BeanException;
import fr.escalade.beans.Topo;

public class TopoDaoImp implements TopoDao{
     private DaoFactory daoFactory;
     
	 TopoDaoImp(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
   


	public List<Topo> lister() throws DaoException {
		List<Topo> topos = new ArrayList<Topo>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String sql;
		
		
		try {
			
			sql="Select nom,description,nbr_page from topo";
		    conn = daoFactory.getConnection();
	        statement = conn.createStatement();
		    resultSet = statement.executeQuery(sql);
		   
		    //r�cup�ration des donn�es
	    while (resultSet.next()){
		     String nom = resultSet.getString("nom");
		     String description = resultSet.getString("description");
		     int nbpage = resultSet.getInt("nbr_page");
		     
		     //on  r�e un objet utilisateur ou on stocke les donn�es recup�r� depuis notre table
		      Topo topo =  new Topo();
		      topo.setNom(nom);
		      topo.setDescription(description);
		      topo.setNbpage(nbpage);
		      
		      //on ajoute les utilisateurs ds notre liste 
		       topos.add(topo);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la base de donn�es");
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la base de donn�es");
			}
		}
		return topos;
	}

}
