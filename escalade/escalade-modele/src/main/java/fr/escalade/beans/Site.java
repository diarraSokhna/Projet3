package fr.escalade.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Site implements Serializable{
	private static final long serialVersionUID = 1L;

	private long idsite;
	private String nomsite; 
	private Pays pays;
	private String image;
	private Classement classement;
	private ArrayList<Secteur> listeSecteur= new ArrayList<Secteur>();
	
	public Site() {}
	
	public Site(long idsite, String nomsite, Pays pays, String image, Classement classement,
			ArrayList<Secteur> listeSecteur) {
		super();
		this.idsite = idsite;
		this.nomsite = nomsite;
		this.pays = pays;
		this.image = image;
		this.classement = classement;
		this.listeSecteur = listeSecteur;
	}

	public long getIdsite() {
		return idsite;
	}

	public void setIdsite(long idsite) {
		this.idsite = idsite;
	}

	public String getNomsite() {
		return nomsite;
	}

	public void setNomsite(String nomsite) {
		this.nomsite = nomsite;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Classement getClassement() {
		return classement;
	}

	public void setClassement(Classement classement) {
		this.classement = classement;
	}

	public ArrayList<Secteur> getListeSecteur() {
		return listeSecteur;
	}

	public void setListeSecteur(ArrayList<Secteur> listeSecteur) {
		this.listeSecteur = listeSecteur;
	}

	public void addSecteur(Secteur secteur){
		this.listeSecteur.add(secteur);
	}
	
	 public void removeSite(Site site ) {
		    this.listeSecteur.remove(site);
		  }
	
	public Secteur getSecteur(int indice){
		return this.listeSecteur.get(indice);
	}
	
}
