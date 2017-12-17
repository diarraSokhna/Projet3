package fr.escalade.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Secteur implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long  idsect;
	private String nomsect; 
	private Site site;
	private Voie voie ;
	
	
	public Secteur() {}



	public Secteur(long idsect, String nomsect, Site site, Voie voie) {
		super();
		this.idsect = idsect;
		this.nomsect = nomsect;
		this.site = site;
		this.voie = voie;
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

	public Voie getVoie() {
		return voie;
	}

	public void setVoie(Voie voie) {
		this.voie = voie;
	}


	

}
