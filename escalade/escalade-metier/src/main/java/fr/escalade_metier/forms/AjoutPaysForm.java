package fr.escalade_metier.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.escalade.beans.Pays;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.PaysDao;

public class AjoutPaysForm {
	
	private static final String CHAMP_NOM  = "nom";
	private String resultat;
	
	private Map<String, String> erreurs = new HashMap<String, String>();
    
	private PaysDao paysDao;
	
	public AjoutPaysForm(PaysDao paysDao) {
		this.paysDao = paysDao;
	}
	

	public AjoutPaysForm() {}


	public Map<String, String> getErreurs() {
	        return erreurs;
	    }

	public String getResultat() {
	        return resultat;
	    }

	public Pays creerPays( HttpServletRequest request ) {
		String nom = getValeurChamp( request, CHAMP_NOM );
		
		Pays pays = new Pays();
		
		traiterNom(nom,pays);
		
        try {
            if ( erreurs.isEmpty() ) {
                paysDao.creer( pays );
                resultat = "Succès de la création du pays.";
            } else {
                resultat = "Échec de la création du pays.";
            }
        } catch ( DaoException e ) {
            setErreur( "imprévu", "Erreur imprévue lors de la création." );
            resultat = "Échec de la création du pays : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }

        return pays;
		
		
	}
	
	private void traiterNom(String nom, Pays pays) {
		   try {
	            validationNom( nom );
	        } catch ( FormValidationException e ) {
	            setErreur( CHAMP_NOM, e.getMessage() );
	        }
	        pays.setNompays(nom);
	}

	
	
	private void validationNom(String nom) throws FormValidationException {
		
		 if ( nom != null && nom.length() < 2 ) {
	            throw new FormValidationException( "Le nom du payss doit contenir au moins 2 caractères." );
	        }else {
	        	throw new FormValidationException( "Le nom du pays est obligatoire." );
		 	       
	        }
		
	}

	private String getValeurChamp(HttpServletRequest request, String nomChamp) {
		 String valeur = request.getParameter( nomChamp );
	        if ( valeur == null || valeur.trim().length() == 0 ) {
	            return null;
	        } else {
	            return valeur;
	        }
	
	}
	
	private void setErreur( String champ, String message ) {
       erreurs.put( champ, message );
   }
	
	
}
