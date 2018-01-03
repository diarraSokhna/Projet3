package fr.escalade.persistance;



import java.util.List;

import fr.escalade.beans.TopoSite;

public interface TopoSiteDao {

	void creer (TopoSite toposite) throws DaoException;
	
	List<TopoSite> lister() throws DaoException;
	
	TopoSite trouver(Long id_topo_site) throws DaoException;
	
	TopoSite trouverpar(Long id_site) throws DaoException;
	
	
	
}
