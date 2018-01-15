package fr.escalade.persistance;

import static fr.escalade.persistance.DaoUtilitaire.fermeturesSilencieuses;
import static fr.escalade.persistance.DaoUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.escalade.beans.Classement;

public class ClassementDaoImpl implements ClassementDao {

	private static final String SQL_SELECT_PAR_LIBELLE = "SELECT * FROM classement WHERE libelle_class = ?";
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM classement WHERE id_class = ?";
	private static final String SQL_SELECT = "SELECT * FROM classement ORDER BY id_class";
	private static final String SQL_INSERT = "INSERT INTO classement (libelle_class) VALUES (?)";
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM classement WHERE id_class = ?";

	private DaoFactory daoFactory;

	public ClassementDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Classement classement) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true,
					classement.getLibelle_class());

			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Échec de la création du classement, aucune ligne ajoutée dans la table.");
			}

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				classement.setId_class(valeursAutoGenerees.getLong(1));
			} else {
				throw new DaoException("Échec de la création du classement en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}

	}

	private Classement trouver(String sql, Object... objets) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Classement classement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				classement = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return classement;
	}

	@Override
	public Classement trouver(String libelle) throws DaoException {
		return trouver(SQL_SELECT_PAR_LIBELLE, libelle);
	}

	@Override
	public Classement trouver(Long id_class) throws DaoException {
		return trouver(SQL_SELECT_PAR_ID, id_class);
	}

	@Override
	public List<Classement> lister() throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Classement> classements = new ArrayList<Classement>();

		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				classements.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}

		return classements;
	}

	@Override
	public void supprimer(Classement classement) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE_PAR_ID, true,
					classement.getId_class());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Échec de la suppression du classement, aucune ligne supprimée de la table.");
			} else {
				classement.setId_class(0);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

	private Classement map(ResultSet resultSet) throws SQLException {
		Classement classement = new Classement();
		classement.setId_class(resultSet.getLong("id_class"));
		classement.setLibelle_class(resultSet.getString("libelle_class"));

		return classement;
	}

}
