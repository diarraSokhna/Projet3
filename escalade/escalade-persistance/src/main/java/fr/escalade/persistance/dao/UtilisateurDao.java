package fr.escalade.persistance.dao;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.escalade.beans.Utilisateur;

@Repository
public interface UtilisateurDao extends CrudRepository<Utilisateur , Long>{

//	   void creer( Utilisateur utilisateur ) ;
//
//	    Utilisateur trouver( String email );
//	    
    List<Utilisateur> lister();

}
