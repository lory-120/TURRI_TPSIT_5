package model;

import java.io.Serializable;

public class Attivita implements Serializable {

	private static final long serialVersionUID = 1L;
	private String testo;
	private boolean isDone;
	
	public Attivita() {
		this.isDone = false;
	}

	public Attivita(String testo) {
		this.testo = testo;
		this.isDone = false;
	}
	
	
	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
}
