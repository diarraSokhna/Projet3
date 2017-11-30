package fr.escalade.beans;

import java.io.Serializable;
import java.util.Date;

public class Commentaire implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id_com;
	private String libelle; 
	private Date datecom;
	private long id_user;
	private long id_art;
	
	public Commentaire() {}

	public Commentaire(long id_com, String libelle, Date datecom, long id_user, long id_art) {
		super();
		this.id_com = id_com;
		this.libelle = libelle;
		this.datecom = datecom;
		this.id_user = id_user;
		this.id_art = id_art;
	}

	public long getId_com() {
		return id_com;
	}

	public void setId_com(long id_com) {
		this.id_com = id_com;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Date getDatecom() {
		return datecom;
	}

	public void setDatecom(Date datecom) {
		this.datecom = datecom;
	}

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public long getId_art() {
		return id_art;
	}

	public void setId_art(long id_art) {
		this.id_art = id_art;
	}
	
	
	
	
}
