package fr.escalade.persistance;

import java.util.List;

import fr.escalade.beans.Cotation;

public interface CotationDao {

	  void creer(Cotation cotation) throws DaoException;
	
	  List<Cotation> lister() throws DaoException;
	  
	  Cotation trouver( Long id_cot ) throws DaoException;

	  Cotation trouver(String libelle_cot ) throws DaoException;
	
}
