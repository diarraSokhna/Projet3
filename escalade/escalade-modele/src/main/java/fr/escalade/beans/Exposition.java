package fr.escalade.beans;

import java.io.Serializable;

public class Exposition implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id_expo;
	private String libelle_expo;
	
	public Exposition() {}

	public Exposition(long id_expo, String libelle_expo) {
		super();
		this.id_expo = id_expo;
		this.libelle_expo = libelle_expo;
	}

	public long getId_expo() {
		return id_expo;
	}

	public void setId_expo(long id_expo) {
		this.id_expo = id_expo;
	}

	public String getLibelle_expo() {
		return libelle_expo;
	}

	public void setLibelle_expo(String libelle_expo) {
		this.libelle_expo = libelle_expo;
	}
	
	
	

}
