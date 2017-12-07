package fr.escalade.beans;

import java.io.Serializable;

public class Secteur implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long  idsect;
	private String nomsect; 
	private Site site;
	
	public Secteur() {}

	public Secteur(long idsect, String nomsect, Site site) {
		super();
		this.idsect = idsect;
		this.nomsect = nomsect;
		this.site = site;
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

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	
	

}
