package fr.escalade.beans;

import java.io.Serializable;

public class Departement implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private long id_dep;
    private Pays pays;
    private String nom_dep;
    private int cp;
    
	public Departement() {}

	public Departement(long id_dep, Pays pays, String nom_dep, int cp) {
		super();
		this.id_dep = id_dep;
		this.pays = pays;
		this.nom_dep = nom_dep;
		this.cp = cp;
	}

	public long getId_dep() {
		return id_dep;
	}

	public void setId_dep(long id_dep) {
		this.id_dep = id_dep;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public String getNom_dep() {
		return nom_dep;
	}

	public void setNom_dep(String nom_dep) {
		this.nom_dep = nom_dep;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}
    
    
}
