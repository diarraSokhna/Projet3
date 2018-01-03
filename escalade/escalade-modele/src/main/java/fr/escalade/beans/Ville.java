package fr.escalade.beans;

import java.io.Serializable;

public class Ville implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private long id_ville;
    private Pays pays;
    private String nom_ville;
    private int cp;
    
	public Ville() {}

	public Ville(long id_ville, Pays pays, String nom_ville, int cp) {
		super();
		this.id_ville = id_ville;
		this.pays = pays;
		this.nom_ville = nom_ville;
		this.cp = cp;
	}


	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	

	public long getId_ville() {
		return id_ville;
	}

	public void setId_ville(long id_ville) {
		this.id_ville = id_ville;
	}

	public String getNom_ville() {
		return nom_ville;
	}

	public void setNom_ville(String nom_ville) {
		this.nom_ville = nom_ville;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}
    
    
}
