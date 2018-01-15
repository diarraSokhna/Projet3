package fr.escalade.persistance;

import static fr.escalade.persistance.DaoUtilitaire.fermeturesSilencieuses;
import static fr.escalade.persistance.DaoUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.escalade.beans.Article;

public class ArticleDaoImpl implements ArticleDao {

	private static final String SQL_SELECT_PAR_TITRE = "SELECT * FROM article WHERE titre = ?";
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM article WHERE id_art = ?";
	private static final String SQL_SELECT = "SELECT * FROM article ORDER BY id_art";
	private static final String SQL_INSERT = "INSERT INTO article (date, titre, contenu, id_user, photoa) VALUES (NOW(), ?, ?, ?, ?)";
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM article WHERE id_art = ?";

	private DaoFactory daoFactory;

	public ArticleDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void creer(Article article) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, article.getTitre(),
					article.getContenu(), article.getUtilisateur().getIduser(), article.getPhoto());

			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Échec de la création de l'article, aucune ligne ajoutée dans la table.");
			}

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				article.setId_art(valeursAutoGenerees.getLong(1));
			} else {
				throw new DaoException("Échec de la création de l'article en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}

	}

	private Article trouver(String sql, Object... objets) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Article article = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				article = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return article;
	}

	public Article trouver(String titre) throws DaoException {
		return trouver(SQL_SELECT_PAR_TITRE, titre);
	}

	public Article trouver(Long id_art) throws DaoException {
		return trouver(SQL_SELECT_PAR_ID, id_art);
	}

	public List<Article> lister() throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Article> articles = new ArrayList<Article>();

		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				articles.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}

		return articles;
	}

	private Article map(ResultSet resultSet) throws SQLException {
		Article article = new Article();
		article.setId_art(resultSet.getLong("id_art"));
		article.setDatepubli(resultSet.getDate("date"));
		article.setTitre(resultSet.getString("titre"));
		article.setContenu(resultSet.getString("contenu"));

		UtilisateurDao utilisateurDao = daoFactory.getUtilisateurDao();
		article.setUtilisateur(utilisateurDao.trouverParId(resultSet.getLong("id_user")));
		article.setPhoto(resultSet.getString("photoa"));

		return article;
	}

	public void supprimer(Article article) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE_PAR_ID, true, article.getId_art());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Échec de la suppression de l'article, aucune ligne supprimée de la table.");
			} else {
				article.setId_art(0);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

}
