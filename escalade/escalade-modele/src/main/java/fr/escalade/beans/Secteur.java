package fr.escalade.beans;

import java.io.Serializable;

public class Secteur implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long  idsect;
	private String nomsect; 
	private long idsite;
	
	public Secteur() {}

	public Secteur(long idsect, String nomsect, long idsite) {
		super();
		this.idsect = idsect;
		this.nomsect = nomsect;
		this.idsite = idsite;
	}

	public long getIdsect() {
		return idsect;
	}

	public void setIdsect(long idsect) {
		this.idsect = idsect;
	}

	public String getNomsect() {
		return nomsect;
	}

	public void setNomsect(String nomsect) {
		this.nomsect = nomsect;
	}

	public long getIdsite() {
		return idsite;
	}

	public void setIdsite(long idsite) {
		this.idsite = idsite;
	}
	
	

}
