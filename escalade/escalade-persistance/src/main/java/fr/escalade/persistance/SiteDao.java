package fr.escalade.persistance;

import java.util.List;

import fr.escalade.beans.Pays;
import fr.escalade.beans.Site;


public interface SiteDao {
	
	  Site creer(Site site) throws DaoException;
	  
	  List<Site> lister() throws DaoException;
	  
	  List<Site> lister(long idpays) throws DaoException;
	  
	  List<Site> listerParTopo(long idtopo) throws DaoException;
	  
	  void supprimer( Site site ) throws DaoException;
	  
	  Site trouver( Long id_site ) throws DaoException;

	  Site trouver(String nomsite ) throws DaoException;
}
