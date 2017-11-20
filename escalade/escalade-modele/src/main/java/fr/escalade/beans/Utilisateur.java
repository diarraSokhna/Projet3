package fr.escalade.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="utilisateur")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_user" , nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long iduser;
	
	@Column(name = "nom", length = 30, nullable = false) 
	private String nom;
	
	@Column(name = "prenom", length = 30, nullable = false) 
	private String prenom;
	
	@Column(name = "adresse", length = 30, nullable = true) 
	private String adresse;
	
	@Column(name = "tel", length = 30, nullable = true) 
	private int tel;
	
	@Column(name = "email", length = 30, nullable = false, unique=true) 
	private String email;
	
	@Column(name = "motpass", length = 30, nullable = false) 
	private String motpass;
	
	@Column(name = "dateinscription", length = 30, nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp dateinscription;
	
	@Column(name = "photo", length = 300, nullable = true) 
	private String photo;
	
	
	public Utilisateur() {
		
	}
	
	
	public Utilisateur(String nom, String prenom, String adresse, int tel, String email, String motpass,
			Timestamp dateinscription, String photo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
		this.motpass = motpass;
		this.dateinscription = dateinscription;
		this.photo = photo;
	}


	public long getIduser() {
		return iduser;
	}
	public void setId_user(int iduser) {
		this.iduser = iduser;
	}
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotpass() {
		return motpass;
	}
	public void setMotpass(String motpass) {
		this.motpass = motpass;
	}
	public Timestamp getDateinscription() {
		return dateinscription;
	}
	public void setDateinscription(Timestamp dateinscription) {
		this.dateinscription = dateinscription;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
}
