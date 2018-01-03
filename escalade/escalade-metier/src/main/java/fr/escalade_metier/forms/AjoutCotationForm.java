package fr.escalade_metier.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.escalade.beans.Cotation;
import fr.escalade.beans.Pays;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.PaysDao;

public class AjoutCotationForm {
	
	private static final String CHAMP_LIBELLE_COTA  = "libelleCota";
	private static final String CHAMP_TYPE_ESCALADE  = "typeEscalade";
	private String resultat;
	
	private Map<String, String> erreurs = new HashMap<String, String>();
    
	private CotationDao cotationDao;
	
	public AjoutCotationForm(CotationDao cotationDao) {
		this.cotationDao = cotationDao;
	}
	

	public AjoutCotationForm() {}


	public Map<String, String> getErreurs() {
	        return erreurs;
	    }

	public String getResultat() {
	        return resultat;
	    }

	public Cotation creerCotation( HttpServletRequest request ) {
		String libellecot = getValeurChamp( request, CHAMP_LIBELLE_COTA );
		String typeesclade = getValeurChamp( request, CHAMP_TYPE_ESCALADE );
		
		Cotation cotation = new Cotation();
		traiterTypeEscalade( typeesclade, cotation );
		traiterLibelleCotation( libellecot, cotation );
		
        try {
            if ( erreurs.isEmpty() ) {
                cotationDao.creer( cotation );
                resultat = "Succès de la création de la cotation.";
            } else {
                resultat = "Échec de la création de cotation.";
            }
        } catch ( DaoException e ) {
            setErreur( "imprévu", "Erreur imprévue lors de la création." );
            resultat = "Échec de la création de la coation : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }

        return cotation;
		
		
	}
	
	private void traiterLibelleCotation(String libellecot, Cotation cotation) {
		   try {
	            validationLibelleCotation( libellecot );
	        } catch ( FormValidationException e ) {
	            setErreur( CHAMP_LIBELLE_COTA, e.getMessage() );
	        }
	        cotation.setLibelle_cot(libellecot);
	}

	private void traiterTypeEscalade(String typeescalade, Cotation cotation) {
		   try {
	            validationTypeEscalade( typeescalade );
	        } catch ( FormValidationException e ) {
	            setErreur( CHAMP_TYPE_ESCALADE, e.getMessage() );
	        }
	        cotation.setLibelle_cot(typeescalade);
	}
	
	
	private void validationLibelleCotation(String libellecot) throws FormValidationException {
		
		 if ( libellecot != null && libellecot.length() < 2 ) {
	            throw new FormValidationException( "Le libellé de la cotation doit contenir au moins 2 caractères." );
	        }else if(libellecot == null){
	        	throw new FormValidationException( "Le libellé de la cotation est obligatoire." );
		 	       
	        }
		
	}

	private void validationTypeEscalade(String typeescalade) throws FormValidationException {
		
		 if ( typeescalade != null && typeescalade.length() < 5 ) {
	            throw new FormValidationException( "Le type d'escalade doit contenir au moins 5 caractères." );
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
