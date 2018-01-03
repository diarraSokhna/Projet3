package fr.escalade.beans;

import java.io.Serializable;
import java.util.ArrayList;


public class Topo implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	
	private long idtopo;
	private String nom;
	private String description;
	private int nbpage;
	private Utilisateur utilisateur;
	private String image;
	private ArrayList<Site> listSite = new ArrayList<Site>();
	
	public Topo() {}

	
	
	public Topo(long idtopo, String nom, String description, int nbpage, Utilisateur utilisateur, String image,
			ArrayList<Site> listSite) {
		super();
		this.idtopo = idtopo;
		this.nom = nom;
		this.description = description;
		this.nbpage = nbpage;
		this.utilisateur = utilisateur;
		this.image = image;
		this.listSite = listSite;
	}



	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public long getIdtopo() {
		return idtopo;
	}
	public void setIdtopo(long idtopo) {
		this.idtopo = idtopo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNbpage() {
		return nbpage;
	}
	public void setNbpage(int nbpage) {
		this.nbpage = nbpage;
	}


	public ArrayList<Site> getListSite() {
		return listSite;
	}


	public void setListSite(ArrayList<Site> listSite) {
		this.listSite = listSite;
	}
	
	public void addSite(Site site){
		this.listSite.add(site);
	}
	
	 public void removeSite(Site site ) {
		    this.listSite.remove(site);
		  }
	
	public Site getSite(int indice){
		return this.listSite.get(indice);
	}
	
	
}
