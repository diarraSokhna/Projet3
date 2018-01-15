package fr.escalade.beans;

import java.io.Serializable;

public class Cotation implements Serializable {
	private static final long serialVersionUID = 1L;

	private long idcot;
	private String type_escalade;
	private String libelle_cot;

	public Cotation() {
	}

	public Cotation(long idcot, String type_escalade, String libelle_cot) {
		super();
		this.idcot = idcot;
		this.type_escalade = type_escalade;
		this.libelle_cot = libelle_cot;
	}

	public long getIdcot() {
		return idcot;
	}

	public void setIdcot(long idcot) {
		this.idcot = idcot;
	}

	public String getType_escalade() {
		return type_escalade;
	}

	public void setType_escalade(String type_escalade) {
		this.type_escalade = type_escalade;
	}

	public String getLibelle_cot() {
		return libelle_cot;
	}

	public void setLibelle_cot(String libelle_cot) {
		this.libelle_cot = libelle_cot;
	}

}
