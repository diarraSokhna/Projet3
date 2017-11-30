package fr.escalade.beans;

import java.io.Serializable;

public class Coordonnee implements Serializable{
	private static final long serialVersionUID = 1L;
    
	private long idcoor;
	private float  x;
	private float  y;
	
	public Coordonnee() {}
	
	public Coordonnee(long idcoor, float x, float y) {
		super();
		this.idcoor = idcoor;
		this.x = x;
		this.y = y;
	}

	public long getIdcoor() {
		return idcoor;
	}

	public void setIdcoor(long idcoor) {
		this.idcoor = idcoor;
	}

	public double getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
	
	
}
