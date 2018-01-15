package fr.escalade.beans;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id_art;
	private Date datepubli;
	private String titre;
	private String contenu;
	private Utilisateur utilisateur;
	private String photo;

	public Article() {
	}

	public Article(long id_art, Date datepubli, String titre, String contenu, Utilisateur utilisateur, String photo) {
		super();
		this.id_art = id_art;
		this.datepubli = datepubli;
		this.titre = titre;
		this.contenu = contenu;
		this.utilisateur = utilisateur;
		this.photo = photo;
	}

	public long getId_art() {
		return id_art;
	}

	public void setId_art(long id_art) {
		this.id_art = id_art;
	}

	public Date getDatepubli() {
		return datepubli;
	}

	public void setDatepubli(Date datepubli) {
		this.datepubli = datepubli;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
