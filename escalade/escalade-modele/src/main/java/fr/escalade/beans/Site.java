package fr.escalade.beans;

import java.io.Serializable;

public class Site implements Serializable{
	private static final long serialVersionUID = 1L;

	private long idsite;
	private String nomsite; 
	private String exposition; 
	private String classement;
	private Pays pays;
	
	public Site() {}

	public Site(long idsite, String nomsite, String exposition, String classement, Pays pays) {
		super();
		this.idsite = idsite;
		this.nomsite = nomsite;
		this.exposition = exposition;
		this.classement = classement;
		this.pays = pays;
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

	public String getExposition() {
		return exposition;
	}

	public void setExposition(String exposition) {
		this.exposition = exposition;
	}

	public String getClassement() {
		return classement;
	}

	public void setClassement(String classement) {
		this.classement = classement;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

}
