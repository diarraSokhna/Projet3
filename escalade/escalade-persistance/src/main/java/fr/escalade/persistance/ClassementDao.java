package fr.escalade.persistance;

import java.util.List;

import fr.escalade.beans.Classement;

public interface ClassementDao {

	void creer(Classement classement) throws DaoException;

	Classement trouver(String libelle) throws DaoException;

	Classement trouver(Long id_class) throws DaoException;

	List<Classement> lister() throws DaoException;

	void supprimer(Classement classement) throws DaoException;

}
