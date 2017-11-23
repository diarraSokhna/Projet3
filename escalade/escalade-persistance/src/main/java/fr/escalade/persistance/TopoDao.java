package fr.escalade.persistance;

import java.util.List;

import fr.escalade.beans.Topo;

public interface TopoDao {

	//définition de la fonction créer qui crée un topo
		void creer(Topo topo) throws DaoException;
	//fonction lister qui liste les topos
	  List<Topo> lister() throws DaoException;
}
