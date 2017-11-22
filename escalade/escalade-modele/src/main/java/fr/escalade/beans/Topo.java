package fr.escalade.beans;

import java.io.Serializable;

public class Topo implements Serializable{
    
	private long idtopo;
	private String nom;
	private String description;
	private int nbpage;
	private int iduser;
	private String image;
	
	public long getIdtopo() {
		return idtopo;
	}
	public void setIdtopo(long idtopo) {
		this.idtopo = idtopo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNbpage() {
		return nbpage;
	}
	public void setNbpage(int nbpage) {
		this.nbpage = nbpage;
	}
	
	
	
}
