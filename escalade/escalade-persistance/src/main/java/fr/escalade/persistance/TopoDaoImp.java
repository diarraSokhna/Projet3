package fr.escalade.persistance;

import static fr.escalade.persistance.DaoUtilitaire.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.escalade.beans.Topo;
import fr.escalade.persistance.DaoException;
import fr.escalade.persistance.TopoDao;

public class TopoDaoImp implements TopoDao {

	private static final String SQL_INSERT = "INSERT INTO topo(nom, description, nbr_page, id_user, image) VALUES(?, ?, ?, ?, ?)";
	private static final String SQL_SELECT = "SELECT id_topo, nom, description, nbr_page, id_user, image FROM topo ORDER BY id_topo";
	private static final String SQL_SELECT_PAR_NOM = "SELECT id_topo, nom,description, nbr_page, id_user, image FROM topo WHERE nom = ?";
	private static final String SQL_SELECT_PAR_ID = "SELECT id_topo, nom,description, nbr_page, id_user, image FROM topo WHERE id_topo = ?";
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM topo WHERE id_topo = ?";

	private DaoFactory daoFactory;

	TopoDaoImp(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void creer(Topo topo) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, topo.getNom(),
					topo.getDescription(), topo.getNbpage(), topo.getUtilisateur().getIduser(), topo.getImage());

			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Échec de la création du topo, aucune ligne ajoutée dans la table.");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				topo.setIdtopo(valeursAutoGenerees.getLong(1));

			} else {
				throw new DaoException("Échec de la création du topo en base, aucun ID auto-généré retourné.");
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
			fermeturesSilencieuses(preparedStatement1, connexion);
		}
	}

	private Topo trouver(String sql, Object... objets) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Topo topo = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				topo = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return topo;
	}

	public List<Topo> lister() throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Topo> topos = new ArrayList<Topo>();

		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				topos.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}

		return topos;
	}

	private Topo map(ResultSet resultSet) throws SQLException {
		Topo topo = new Topo();
		topo.setIdtopo(resultSet.getLong("id_topo"));
		topo.setNom(resultSet.getString("nom"));
		topo.setDescription(resultSet.getString("description"));
		topo.setNbpage(resultSet.getInt("nbr_page"));

		UtilisateurDao utilisateurDao = daoFactory.getUtilisateurDao();
		topo.setUtilisateur(utilisateurDao.trouverParId(resultSet.getLong("id_user")));
		;
		topo.setImage(resultSet.getString("image"));

		return topo;
	}

	public Topo trouver(String nom) throws DaoException {
		return trouver(SQL_SELECT_PAR_NOM, nom);
	}

	public Topo trouver(Long id_topo) throws DaoException {
		return trouver(SQL_SELECT_PAR_ID, id_topo);
	}

	public void supprimer(Topo topo) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE_PAR_ID, true, topo.getIdtopo());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Échec de la suppression du topo, aucune ligne supprimée de la table.");
			} else {
				topo.setIdtopo(0);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

}
