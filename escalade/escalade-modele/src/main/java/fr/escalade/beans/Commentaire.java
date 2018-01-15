package fr.escalade.beans;

import java.io.Serializable;
import java.util.Date;

public class Commentaire implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id_com;
	private String libelle;
	private Date datecom;
	private Utilisateur utilisateur;
	private Topo topo;

	public Commentaire() {
	}

	public Commentaire(long id_com, String libelle, Date datecom, Utilisateur utilisateur, Topo topo) {
		super();
		this.id_com = id_com;
		this.libelle = libelle;
		this.datecom = datecom;
		this.utilisateur = utilisateur;
		this.topo = topo;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
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

}
