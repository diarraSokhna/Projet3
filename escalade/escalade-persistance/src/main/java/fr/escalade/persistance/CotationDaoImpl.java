package fr.escalade.persistance;

import static fr.escalade.persistance.DaoUtilitaire.fermeturesSilencieuses;
import static fr.escalade.persistance.DaoUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.escalade.beans.Cotation;

public class CotationDaoImpl implements CotationDao {

	private static final String SQL_INSERT = "INSERT INTO cotation(type_escalade, libelle_cotation) VALUES(?, ?)";
	private static final String SQL_SELECT = "SELECT * FROM cotation ORDER BY id_cotation";
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM cotation WHERE id_cotation = ?";
	private static final String SQL_SELECT_PAR_LIBELLE = "SELECT * FROM cotation WHERE libelle_cotation = ?";

	private DaoFactory daoFactory;

	public CotationDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Cotation cotation) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, cotation.getType_escalade(),
					cotation.getLibelle_cot());

			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Échec de la création de la cotation, aucune ligne ajoutée dans la table.");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				cotation.setIdcot(valeursAutoGenerees.getLong(1));

			} else {
				throw new DaoException("Échec de la création de la cotation en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}

	}

	private Cotation trouver(String sql, Object... objets) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Cotation cotation = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				cotation = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return cotation;
	}

	@Override
	public Cotation trouver(Long id_cot) throws DaoException {
		return trouver(SQL_SELECT_PAR_ID, id_cot);
	}

	@Override
	public Cotation trouver(String libelle_cot) throws DaoException {
		return trouver(SQL_SELECT_PAR_LIBELLE, libelle_cot);
	}

	@Override
	public List<Cotation> lister() throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Cotation> cotations = new ArrayList<Cotation>();

		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cotations.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}

		return cotations;
	}

	private Cotation map(ResultSet resultSet) throws SQLException {
		Cotation cotation = new Cotation();
		cotation.setIdcot(resultSet.getLong("id_cotation"));
		cotation.setType_escalade(resultSet.getString("type_escalade"));
		cotation.setLibelle_cot(resultSet.getString("libelle_cotation"));

		return cotation;
	}

}
