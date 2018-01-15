package fr.escalade.beans;

import java.io.Serializable;

public class Classement implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id_class;
	private String libelle_class;

	public Classement() {
	}

	public Classement(long id_class, String libelle_class) {
		super();
		this.id_class = id_class;
		this.libelle_class = libelle_class;
	}

	public long getId_class() {
		return id_class;
	}

	public void setId_class(long id_class) {
		this.id_class = id_class;
	}

	public String getLibelle_class() {
		return libelle_class;
	}

	public void setLibelle_class(String libelle_class) {
		this.libelle_class = libelle_class;
	}

}
