package fr.escalade.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Site implements Serializable{
	private static final long serialVersionUID = 1L;

	private long idsite;
	private String nomsite; 
	private Pays pays;
	private String image;
	private Classement classement;
	private Secteur secteur;
	
	public Site() {}
	
	public Site(long idsite, String nomsite, Pays pays, String image, Classement classement,
			Secteur secteur) {
		super();
		this.idsite = idsite;
		this.nomsite = nomsite;
		this.pays = pays;
		this.image = image;
		this.classement = classement;
		this.secteur = secteur;
	}

	public long getIdsite() {
		return idsite;
	}

	public void setIdsite(long idsite) {
		this.idsite = idsite;
	}

	public String getNomsite() {
		return nomsite;
	}

	public void setNomsite(String nomsite) {
		this.nomsite = nomsite;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Classement getClassement() {
		return classement;
	}

	public void setClassement(Classement classement) {
		this.classement = classement;
	}

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}


}
