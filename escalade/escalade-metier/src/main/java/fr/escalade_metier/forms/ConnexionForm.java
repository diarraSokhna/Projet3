package fr.escalade_metier.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.UtilisateurDao;

public final class ConnexionForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "motdepasse";

    private static final String ALGO_CHIFFREMENT = "SHA-256";
    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();
    
    StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
    
    
    private UtilisateurDao      utilisateurDao;
    Utilisateur utilisateur = new Utilisateur();
    

	public ConnexionForm() {
	}

	public ConnexionForm(UtilisateurDao utilisateurDao) {
		super();
		this.utilisateurDao = utilisateurDao;
	}

	public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );

        Utilisateur utilisateur = new Utilisateur();

        /* Validation du champ email. */
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        utilisateur.setEmail( email );

        
        /* Validation du champ mot de passe. */
        
        try {
            validationMotDePasse( motDePasse );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        
        
        utilisateur.setMotpass(motDePasse);

        /* Initialisation du résultat global de la validation. */
        
        
        if ( erreurs.isEmpty()) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }

        return utilisateur;
    }

   
    private void validationEmail( String email ) throws Exception {
        if ( utilisateurDao.trouver( email ) == null  ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }
    
    private void validationMotDePasse( String motDePasse ) throws FormValidationException {
    	String motdepassbase = utilisateur.getMotpass();
    	   if (utilisateurDao.trouverParPasse(motDePasse) == null &&  passwordEncryptor.checkPassword(motDePasse, motdepassbase)){
            throw new FormValidationException( "mot de passe incorrect." );
        }else{
        	throw new FormValidationException( "Mot de passe correct." );
        }
    }

   
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
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