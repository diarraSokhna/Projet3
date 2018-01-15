package fr.escalade.beans;

import java.io.Serializable;

public class Pays implements Serializable {
	private static final long serialVersionUID = 1L;

	private long idpays;
	private String nompays;

	public Pays() {
	}

	public Pays(long idpays, String nompays) {
		super();
		this.idpays = idpays;
		this.nompays = nompays;
	}

	public long getIdpays() {
		return idpays;
	}

	public void setIdpays(long idpays) {
		this.idpays = idpays;
	}

	public String getNompays() {
		return nompays;
	}

	public void setNompays(String nompays) {
		this.nompays = nompays;
	}

}
