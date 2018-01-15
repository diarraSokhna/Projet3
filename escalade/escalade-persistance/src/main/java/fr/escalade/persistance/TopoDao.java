package fr.escalade.persistance;

import java.util.List;

import fr.escalade.beans.Topo;

public interface TopoDao {

	void creer(Topo topo) throws DaoException;

	List<Topo> lister() throws DaoException;

	Topo trouver(String nom) throws DaoException;

	Topo trouver(Long id_topo) throws DaoException;

	void supprimer(Topo topo) throws DaoException;
}
