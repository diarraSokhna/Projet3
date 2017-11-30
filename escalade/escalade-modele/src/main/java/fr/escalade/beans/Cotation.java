package fr.escalade.beans;

import java.io.Serializable;

public class Cotation implements Serializable{
	private static final long serialVersionUID = 1L;

	private long idcot;
	private String type_escalade;
	
	public Cotation() {}

	public Cotation(long idcot, String type_escalade) {
		super();
		this.idcot = idcot;
		this.type_escalade = type_escalade;
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
	
	
	
	
}
