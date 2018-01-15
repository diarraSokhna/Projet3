package fr.escalade.beans;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id_resa;
	private Date date_resa;
	private Topo topo;
	private Utilisateur utilisateur;

	public Reservation(long id_resa, Date date_resa, Topo topo, Utilisateur utilisateur) {
		super();
		this.id_resa = id_resa;
		this.date_resa = date_resa;
		this.topo = topo;
		this.utilisateur = utilisateur;
	}

	public Reservation() {
	}

	public long getId_resa() {
		return id_resa;
	}

	public void setId_resa(long id_resa) {
		this.id_resa = id_resa;
	}

	public Date getDate_resa() {
		return date_resa;
	}

	public void setDate_resa(Date date_resa) {
		this.date_resa = date_resa;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
