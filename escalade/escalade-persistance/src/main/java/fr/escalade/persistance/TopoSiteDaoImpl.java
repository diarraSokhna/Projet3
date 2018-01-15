package fr.escalade.persistance;

import static fr.escalade.persistance.DaoUtilitaire.fermeturesSilencieuses;
import static fr.escalade.persistance.DaoUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.escalade.beans.TopoSite;

public class TopoSiteDaoImpl implements TopoSiteDao {

	private static final String SQL_INSERT = "INSERT INTO topo_site( id_site, id_topo) VALUES(?, ?)";
	private static final String SQL_SELECT = "SELECT * FROM topo_site ORDER BY id";
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM topo_site WHERE id = ?";

	private DaoFactory daoFactory;

	public TopoSiteDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(TopoSite toposite) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true,
					toposite.getSite().getIdsite(), toposite.getTopo().getIdtopo());

			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Échec de l'ajout du site dans topo, aucune ligne ajoutée dans la table.");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				toposite.setId_topo_site(valeursAutoGenerees.getLong(1));
			} else {
				throw new DaoException("Échec de création  toposite en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}

	}

	private TopoSite trouver(String sql, Object... objets) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		TopoSite topoSite = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				topoSite = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return topoSite;
	}

	@Override
	public TopoSite trouver(Long id_topo_site) throws DaoException {
		return trouver(SQL_SELECT_PAR_ID, id_topo_site);
	}

	@Override
	public List<TopoSite> lister() throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<TopoSite> topoSites = new ArrayList<TopoSite>();

		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				topoSites.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}

		return topoSites;
	}

	@Override
	public TopoSite trouverpar(Long id_site) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	private TopoSite map(ResultSet resultSet) throws SQLException {
		TopoSite topoSite = new TopoSite();
		SiteDao siteDao = daoFactory.getSiteDao();
		topoSite.setSite(siteDao.trouver(resultSet.getLong("id_site")));

		TopoDao topoDao = daoFactory.getTopoDao();
		topoSite.setTopo(topoDao.trouver(resultSet.getLong("id_topo")));

		return topoSite;
	}

}
