package fr.escalade.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Secteur implements Serializable {
	private static final long serialVersionUID = 1L;

	private long idsect;
	private String nomsect;
	private Site site;
	private List<Voie> voies = new ArrayList<Voie>();

	public Secteur() {
	}

	public Secteur(long idsect, String nomsect, Site site, List<Voie> voies) {
		super();
		this.idsect = idsect;
		this.nomsect = nomsect;
		this.site = site;
		this.voies = voies;
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

	public List<Voie> getVoies() {
		return voies;
	}

	public void setVoies(List<Voie> voies) {
		this.voies = voies;
	}

	public void addVoie(Voie voie) {
		this.voies.add(voie);
	}

	public void removeVoie(Voie voie) {
		this.voies.remove(voie);
	}

}
