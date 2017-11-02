package fr.escalade.persistance;

import java.util.List;

import fr.escalade.beans.Topo;

public interface TopoDao {

	//fonction lister qui liste les topos
	List<Topo> lister() throws DaoException;
}
