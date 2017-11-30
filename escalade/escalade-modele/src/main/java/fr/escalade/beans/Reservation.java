package fr.escalade.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Reservation implements Serializable{
	private static final long serialVersionUID = 1L;

	private long id_resa;
	private Timestamp date_resa;
	private long id_topo;
	private long id_user;
	
	public Reservation() {}

	public Reservation(long id_resa, Timestamp date_resa, long id_topo, long id_user) {
		super();
		this.id_resa = id_resa;
		this.date_resa = date_resa;
		this.id_topo = id_topo;
		this.id_user = id_user;
	}

	public long getId_resa() {
		return id_resa;
	}

	public void setId_resa(long id_resa) {
		this.id_resa = id_resa;
	}

	public Timestamp getDate_resa() {
		return date_resa;
	}

	public void setDate_resa(Timestamp date_resa) {
		this.date_resa = date_resa;
	}

	public long getId_topo() {
		return id_topo;
	}

	public void setId_topo(long id_topo) {
		this.id_topo = id_topo;
	}

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}
	
	
	
} 
