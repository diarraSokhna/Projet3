package fr.escalade.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Voie implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long id_voie;
	private String nom_voie; 
	private String oriantation; 
	private String type_effort;
	private float altitude;
	private int nbr_longueur;
	private long id_sect;
	private long id_cot;
	private long id_coor;
	
	public Voie() {}

	public Voie(long id_voie, String nom_voie, String oriantation, String type_effort, float altitude, int nbr_longueur,
			long id_sect, long id_cot, long id_coor) {
		super();
		this.id_voie = id_voie;
		this.nom_voie = nom_voie;
		this.oriantation = oriantation;
		this.type_effort = type_effort;
		this.altitude = altitude;
		this.nbr_longueur = nbr_longueur;
		this.id_sect = id_sect;
		this.id_cot = id_cot;
		this.id_coor = id_coor;
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

	public String getOriantation() {
		return oriantation;
	}

	public void setOriantation(String oriantation) {
		this.oriantation = oriantation;
	}

	public String getType_effort() {
		return type_effort;
	}

	public void setType_effort(String type_effort) {
		this.type_effort = type_effort;
	}

	public float getAltitude() {
		return altitude;
	}

	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}

	public int getNbr_longueur() {
		return nbr_longueur;
	}

	public void setNbr_longueur(int nbr_longueur) {
		this.nbr_longueur = nbr_longueur;
	}

	public long getId_sect() {
		return id_sect;
	}

	public void setId_sect(long id_sect) {
		this.id_sect = id_sect;
	}

	public long getId_cot() {
		return id_cot;
	}

	public void setId_cot(long id_cot) {
		this.id_cot = id_cot;
	}

	public long getId_coor() {
		return id_coor;
	}

	public void setId_coor(long id_coor) {
		this.id_coor = id_coor;
	}
	
	

}
