package fr.escalade.beans;

import java.io.Serializable;

public class Voie implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id_voie;
	private String nom_voie;
	private double altitude;
	private int nbr_longueur;
	private Secteur secteur;
	private Cotation cotation;
	private Exposition exposition;

	public Voie() {
	}

	public Voie(long id_voie, String nom_voie, double altitude, int nbr_longueur, Secteur secteur, Cotation cotation,
			Exposition exposition) {
		super();
		this.id_voie = id_voie;
		this.nom_voie = nom_voie;
		this.altitude = altitude;
		this.nbr_longueur = nbr_longueur;
		this.secteur = secteur;
		this.cotation = cotation;
		this.exposition = exposition;
	}

	public long getId_voie() {
		return id_voie;
	}

	public void setId_voie(long id_voie) {
		this.id_voie = id_voie;
	}

	public String getNom_voie() {
		return nom_voie;
	}

	public void setNom_voie(String nom_voie) {
		this.nom_voie = nom_voie;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public int getNbr_longueur() {
		return nbr_longueur;
	}

	public void setNbr_longueur(int nbr_longueur) {
		this.nbr_longueur = nbr_longueur;
	}

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public Cotation getCotation() {
		return cotation;
	}

	public void setCotation(Cotation cotation) {
		this.cotation = cotation;
	}

	public Exposition getExposition() {
		return exposition;
	}

	public void setExposition(Exposition exposition) {
		this.exposition = exposition;
	}

}
