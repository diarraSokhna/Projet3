package fr.escalade.persistance;

import java.util.List;

import fr.escalade.beans.Secteur;
import fr.escalade.beans.Site;

public interface SecteurDao {

	void creer(Secteur secteur, Site site) throws DaoException;

	Secteur trouver(long id_sect) throws DaoException;

	List<Secteur> lister() throws DaoException;

	List<Secteur> lister(long idsite) throws DaoException;

	Secteur trouver(String nomsect) throws DaoException;

}
