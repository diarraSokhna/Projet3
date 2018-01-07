package fr.escalade_metier.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.w3c.dom.html.HTMLParagraphElement;

import fr.escalade.beans.Cotation;
import fr.escalade.beans.Exposition;
import fr.escalade.beans.Secteur;
import fr.escalade.beans.Site;
import fr.escalade.beans.Voie;
import fr.escalade.persistance.CotationDao;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.ExpositionDao;
import fr.escalade.persistance.VoieDao;

public class AjoutVoieForm {
	
	    private static final String CHAMP_NOM       = "nomvoie";
	    private static final String CHAMP_Altitude       = "altitude";
	    private static final String CHAMP_NBRLONGUEUR       = "nbrlongueur";
	    private static final String CHOIX_SECTEUR       = "idsecteur";
	    private static final String CHOIX_COTATION = "idcotation";
	    private static final String CHOIX_EXPOSITION = "idexposition";
	    
	    public static final String SESSION_SECTEURS  = "sessionSecteur";
	    public static final String SESSION_VOIES  = "sessionVoie";
	    
        private String   resultat;
	    
	    private Map<String, String> erreurs = new HashMap<String, String>();
	    
	    private VoieDao voieDao;
	    private CotationDao cotationDao;
	    private ExpositionDao expositionDao;
		public AjoutVoieForm(VoieDao voieDao, CotationDao cotationDao, ExpositionDao expositionDao) {
			super();
			this.voieDao = voieDao;
			this.cotationDao = cotationDao;
			this.expositionDao = expositionDao;
		}
	    
	    public Map<String, String> getErreurs() {
	        return erreurs;
	    }

	    public String getResultat() {
	        return resultat;
	    }
	    
	
		@SuppressWarnings("unchecked")
		public Voie ajouterVoie(HttpServletRequest request){
	    	Voie voie = new Voie();
	    	HttpSession session = request.getSession();
	    	
	    	String idcotation = getValeurChamp( request, CHOIX_COTATION);
	    	Long id_cotation = Long.parseLong(idcotation);
	    	Cotation cotation = cotationDao.trouver(id_cotation);
	    	
	    	String idexpo = getValeurChamp( request, CHOIX_EXPOSITION);
	    	Long id_expo = Long.parseLong(idexpo);
	    	Exposition exposition = expositionDao.trouver(id_expo);
	    	
	    	
	    	String altitude = getValeurChamp( request, CHAMP_Altitude );
	    	String nbrlongueur = getValeurChamp( request, CHAMP_NBRLONGUEUR );
	    	String nomvoie = getValeurChamp( request, CHAMP_NOM );
         
	        
            LinkedHashMap<String, Secteur> secteurs = (LinkedHashMap<String, Secteur>) session.getAttribute( SESSION_SECTEURS );
          
            if ( cotation == null ) {
		            setErreur( CHOIX_COTATION, "Merci de choisir une cotation.");
		            }
	        
	    	 if ( exposition == null ) {
		            setErreur( CHOIX_EXPOSITION, "Merci de choisir une exposition.");
		            }
	    	 
	    	
		      
		        
	        traiterNom( nomvoie, voie , session);
	        traiterAltitude( altitude, voie );
	        traiterNbrLongueur( nbrlongueur, voie );
	        
	        
	        voie.setCotation(cotation);
	        voie.setExposition(exposition);
	        
	        String nomsecteur = getValeurChamp(request, CHOIX_SECTEUR);
           
	        
	        try {
	            if ( erreurs.isEmpty() ) {
	            	Secteur secteur = (Secteur) secteurs.get(nomsecteur);
	                if (secteur != null){
	    	        secteur.addVoie(voie);
	    	        }else {
	    	        	 setErreur( CHOIX_SECTEUR, "Veuillez choisir un secteur!" );
	    	        	
	    	        }
	                resultat = "Succès d'ajout de la voie.";
	            } else {
	                resultat = "Échec d'ajout de la voie.";
	            }
	        } catch ( DaoException e ) {
	            setErreur( "imprévu", "Erreur imprévue lors de l'ajout." );
	            resultat = "Échec de l'ajout de la voie : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	            e.printStackTrace();
	        }
	        return voie;
	    }
	    
	  

		private void traiterNom(String nomvoie, Voie voie, HttpSession session) {
			
			   try {
		            validationNom( nomvoie, session );
		        } catch ( FormValidationException e ) {
		            setErreur( CHAMP_NOM, e.getMessage() );
		        }
		        voie.setNom_voie(nomvoie);
		
        }
	    
        private void traiterAltitude(String altitude, Voie voie) {
			
			double altitud = 0.0;
			   try {
				   altitud= validationAltitude( altitude );
		        } catch ( FormValidationException e ) {
		            setErreur( CHAMP_Altitude, e.getMessage() );
		        }
		        voie.setAltitude(altitud);
			
		}
	    
        private void traiterNbrLongueur(String nbrlongueur, Voie voie) {
			
     			int nbrlong = 0;
     			   try {
     				  nbrlong= validationNbrLongueur( nbrlongueur );
     		        } catch ( FormValidationException e ) {
     		            setErreur( CHAMP_NBRLONGUEUR, e.getMessage() );
     		        }
     		        voie.setNbr_longueur(nbrlong);
     			
     		}
        
    	@SuppressWarnings("unchecked")
		private void validationNom(String nomvoie, HttpSession session) throws FormValidationException {
    		LinkedHashMap<String, Voie> voies = (LinkedHashMap<String, Voie>) session
    				.getAttribute(SESSION_VOIES);
    		
    		if (voies != null ) {
    			Voie voieSession = (Voie) voies.get(nomvoie);

    			if (voieSession != null ) {
    				throw new FormValidationException("Cette voie existe déjà");
    			}
    		}

		     if (voieDao.trouver(nomvoie) != null){
					throw new FormValidationException("Cette voie existe déjà");
				}
			 if ( nomvoie != null && nomvoie.length() < 2 ) {
		            throw new FormValidationException( "Le nom de la voie doit contenir au moins 2 caractères." );
		        }else if(nomvoie == null) {
		        	throw new FormValidationException( "Le nom de la voie est obligatoire." );
			 	       
		        }
			
		}
    	
    	private double validationAltitude(String altitude) throws FormValidationException {
			
		    double temp = 0.0;
	        if ( altitude != null ) {
	            try {
	                temp = Double.parseDouble( altitude );
	                if ( temp < 0 ) {
	                    throw new FormValidationException( "L'altitude doit être un nombre positif." );
	                }
	            } catch ( NumberFormatException e ) {
	                temp = 0.0;
	                throw new FormValidationException( "L'altitude  doit être un double." );
	            }
	        }
            
	        return temp;
	    }
        
	private int validationNbrLongueur(String nbrlongueur) throws FormValidationException {
			
		    int temp = 0;
	        if ( nbrlongueur != null ) {
	            try {
	                temp = Integer.parseInt( nbrlongueur );
	                if ( temp < 0 ) {
	                    throw new FormValidationException( "Le nombre de longueur doit être un nombre positif." );
	                }
	            } catch ( NumberFormatException e ) {
	                temp = 0;
	                throw new FormValidationException( "Le nombre de longueur doit être un nombre." );
	            }
	        } 
	        
	        return temp;
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
