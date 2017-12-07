package fr.escalade_metier.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

import fr.escalade.beans.Utilisateur;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.UtilisateurDao;

public final class ConnexionForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "motdepasse";
    
    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();
    
    StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
    
    
    private UtilisateurDao      utilisateurDao;
    Utilisateur utilisateur = new Utilisateur();
    

	public ConnexionForm() {
	}

	public ConnexionForm(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
    	
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );

        Utilisateur utilisateur = new Utilisateur();

       
        try {
            validationEmailPasse(email,motDePasse);
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
            setErreur( CHAMP_PASS, e.getMessage() );
        }
    
         utilisateur.setEmail( email );
         utilisateur.setMotpass(motDePasse);
         
          //  String motdepassbase = utilisateurDao.trouver(email).getMotpass();
         // && passwordEncryptor.checkPassword(motDePasse, motdepassbase)
     
        if ( erreurs.isEmpty()) {
            resultat = "Succès de la connexion.";
            utilisateur.setId_user(utilisateurDao.trouver(email, motDePasse).getIduser());
        } else {
            resultat = "Échec de la connexion.";
        }

        return utilisateur;
    }

   
    private void validationEmailPasse( String email, String motDePasse) throws Exception {
        if ( utilisateurDao.trouver(email,motDePasse) == null  ) {
            throw new Exception( "L'adresse mail ou le mot de passe est incorrect." );
        }
    }
    
 
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}