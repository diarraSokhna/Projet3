package fr.escalade_metier.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.UtilisateurDao;

public final class InscriptionForm {
	 private static final String CHAMP_NOM     = "nom";
	 private static final String CHAMP_PRENOM        = "prenom";
     private static final String CHAMP_EMAIL      = "email";
     private static final String CHAMP_ID_ROLE      = "id_role";
     private static final String CHAMP_PASS       = "motdepasse";
     private static final String CHAMP_CONF       = "confirmation";
    
     private static final String ALGO_CHIFFREMENT = "SHA-256";

    private String  resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
  
	private UtilisateurDao      utilisateurDao;

	public InscriptionForm(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}
	

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    
    public String getResultat() {
        return resultat;
    }
	
	public Utilisateur inscrireUtilisateur( HttpServletRequest request ) {
		
		String nom = getValeurChamp( request, CHAMP_NOM );
		String prenom = getValeurChamp( request, CHAMP_PRENOM );
	    String email = getValeurChamp( request, CHAMP_EMAIL );
	    String motDePasse = getValeurChamp( request, CHAMP_PASS );
	    String confirmation = getValeurChamp( request, CHAMP_CONF );
	    
	    Utilisateur utilisateur = new Utilisateur();
	    
	    try {
	    	
	        traiterEmail( email, utilisateur );
	        traiterMotsDePasse( motDePasse, confirmation, utilisateur );
	        traiterNom( nom, utilisateur );
	        traiterPrenom( prenom, utilisateur );

	        if ( erreurs.isEmpty() ) {
	            utilisateurDao.creer( utilisateur );
	            resultat = "Succès de l'inscription.";
	        } else {
	            resultat = "Échec de l'inscription.";
	        }
	    } catch ( DaoException e ) {
	        resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	        e.printStackTrace();
	    }

	    return utilisateur;
	}
	
	  /*
     * Appel Ã  la validation de l'adresse email reÃ§ue et initialisation de la
     * propriÃ©tÃ© email du bean
     */
    private void traiterEmail( String email, Utilisateur utilisateur ) {
        try {
            validationEmail( email );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        utilisateur.setEmail( email );
    }

    /*
     * Appel à la validation des mots de passe reçus, chiffrement du mot de
     * passe et initialisation de la propriété motDePasse du bean
     */
    private void traiterMotsDePasse( String motDePasse, String confirmation, Utilisateur utilisateur ) {
        try {
            validationMotsDePasse( motDePasse, confirmation );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }
        /*
         * Utilisation de la bibliothèque Jasypt pour chiffrer le mot de passe
         * efficacement.
         * 
         * L'algorithme SHA-256 est ici utilisé, avec par défaut un salage
         * aléatoire et un grand nombre d'itérations de la fonction de hashage.
         * 
         * La String retournée est de longueur 56 et contient le hash en Base64.
         */
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
        passwordEncryptor.setPlainDigest( false );
        String motDePasseChiffre = passwordEncryptor.encryptPassword( motDePasse );

        utilisateur.setMotpass( motDePasseChiffre );
    }
    /*
     * Appel Ã  la validation du nom reÃ§u et initialisation de la propriÃ©tÃ© nom
     * du bean
     */
    private void traiterNom( String nom, Utilisateur utilisateur ) {
        try {
            validationNom( nom );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        utilisateur.setNom( nom );
    }
    
    private void traiterPrenom( String prenom, Utilisateur utilisateur ) {
        try {
            validationPrenom( prenom );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        utilisateur.setPrenom( prenom );
    }

    /* Validation de l'adresse email */
    private void validationEmail( String email ) throws FormValidationException {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new FormValidationException( "Merci de saisir une adresse mail valide." );
            } else if ( utilisateurDao.trouver( email ) != null ) {
                throw new FormValidationException( "Cette adresse email est déjà utilisée, merci d'en choisir une autre." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir une adresse mail." );
        }
    }
    
    /* Validation des mots de passe */
    private void validationMotsDePasse( String motDePasse, String confirmation ) throws FormValidationException {
        if ( motDePasse != null && confirmation != null ) {
            if ( !motDePasse.equals( confirmation ) ) {
                throw new FormValidationException( "Les mots de passe entrées sont différents, merci de les saisir à  nouveau." );
            } else if ( motDePasse.length() < 3 ) {
                throw new FormValidationException( "Les mots de passe doivent contenir au moins 3 caractéres." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
        }
    }

    /* Validation du nom */
    private void validationNom( String nom ) throws FormValidationException {
        if ( nom != null && nom.length() < 3 ) {
            throw new FormValidationException( "Le nom d'utilisateur doit contenir au moins 3 caractéres." );
        } 
    }
    
    private void validationPrenom( String prenom ) throws FormValidationException {
        if ( prenom != null && prenom.length() < 3 ) {
            throw new FormValidationException( "Le prénom d'utilisateur doit contenir au moins 3 caractéres." );
        }
        
        
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * MÃ©thode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
        
}
