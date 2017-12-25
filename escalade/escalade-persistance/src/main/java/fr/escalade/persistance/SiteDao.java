package fr.escalade.persistance;

import java.util.List;

import fr.escalade.beans.Classement;
import fr.escalade.beans.Cotation;
import fr.escalade.beans.Pays;
import fr.escalade.beans.Site;
import fr.escalade.beans.Topo;
import fr.escalade.beans.Ville;


public interface SiteDao {
	
	  Site creer(Site site) throws DaoException;
	  
	  List<Site> lister() throws DaoException;
	  
	  List<Site> listerParPays(long idpays) throws DaoException;
	  
	  List<Site> listerParClassement(long idclassement) throws DaoException;
	  
	  List<Site> listerParCotation(long idcotation) throws DaoException;
	  
	  List<Site> listerParVille(long idville) throws DaoException;
	  
	  List<Site> listerParTopo(long idtopo) throws DaoException;
	  
	  void supprimer( Site site ) throws DaoException;
	  
	  Site trouver( Long id_site ) throws DaoException;

	  Site trouver(String nomsite ) throws DaoException;
}
