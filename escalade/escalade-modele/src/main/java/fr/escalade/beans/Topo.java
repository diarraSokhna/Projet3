package fr.escalade.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Topo implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id_topo" )
	private long idtopo;
	private String nom;
	private String description;
	@Column( name = "nbr_page" )
	private int nbpage;
	@Column( name = "id_user" )
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
