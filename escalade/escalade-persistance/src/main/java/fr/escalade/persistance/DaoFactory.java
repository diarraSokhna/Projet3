
package fr.escalade.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import fr.escalade.persistance.DaoConfigurationException;
import fr.escalade.persistance.TopoDao;
import fr.escalade.persistance.UtilisateurDao;

public class DaoFactory {
	
	private static final String FICHIER_PROPERTIES = "fr/escalade/config/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
	private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";

	BoneCP connexionPool = null;

	public DaoFactory(BoneCP connexionPool) {

		this.connexionPool = connexionPool;
	}

	public static DaoFactory getInstance() throws DaoConfigurationException {
		Properties properties = new Properties();
		String url;
		String driver;
		String nomUtilisateur;
		String motDePasse;
		BoneCP connexionPool = null;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

		if (fichierProperties == null) {
			throw new DaoConfigurationException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
		}

		try {
			properties.load(fichierProperties);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
			motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
		} catch (IOException e) {
			throw new DaoConfigurationException("Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e);
		}

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DaoConfigurationException("Le driver est introuvable dans le classpath.", e);
		}

		try {
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(url);
			config.setUsername(nomUtilisateur);
			config.setPassword(motDePasse);
			/* Paramétrage de la taille du pool */
			config.setMinConnectionsPerPartition(10);
			config.setMaxConnectionsPerPartition(15);
			config.setPartitionCount(2);
			/*
			 * Création du pool à partir de la configuration, via l'objet BoneCP
			 */
			connexionPool = new BoneCP(config);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoConfigurationException("Erreur de configuration du pool de connexions.", e);
		}

		DaoFactory instance = new DaoFactory(connexionPool);
		return instance;
	}

	Connection getConnection() throws SQLException {
		return connexionPool.getConnection();
	}

	public UtilisateurDao getUtilisateurDao() {
		return new UtilisateurDaoImp(this);
	}

	public TopoDao getTopoDao() {
		return new TopoDaoImp(this);
	}

	public ArticleDao getArticleDao() {
		return new ArticleDaoImpl(this);
	}

	public CommentaireDao getCommentaireDao() {
		return new CommentaireDaoImpl(this);
	}

	public PaysDao getPaysDao() {
		return new PaysDaoImpl(this);
	}

	public SiteDao getSiteDao() {
		return new SiteDaoImpl(this);
	}

	public ClassementDao getClassementDao() {
		return new ClassementDaoImpl(this);
	}

	public ExpositionDao getExpositionDao() {
		return new ExpositionDaoImpl(this);
	}

	public TopoSiteDao getTopoSiteDao() {
		return new TopoSiteDaoImpl(this);
	}

	public ReservationDao getReservationDao() {
		return new ReservationDaoImpl(this);
	}

	public SecteurDao getSecteurDao() {
		return new SecteurDaoImpl(this);
	}

	public VoieDao getVoieDao() {
		return new VoieDaoImpl(this);
	}

	public CotationDao getCotationDao() {
		return new CotationDaoImpl(this);
	}

	public VilleDao getVilleDao() {
		return new VilleDaoImpl(this);
	}
}
