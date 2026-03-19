package model;

import java.io.Serializable;

import dao.GeneratoreID;

public class Ticket implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int ID;
	private Urgenza urgenza;
	private String richiesta;
	private boolean isDone;
	
	public Ticket() {
		this.ID = GeneratoreID.getNewID();
		this.isDone = false;
	}
	
	public Ticket(int ID) {
		this.ID = ID;
		this.isDone = false;
	}
	
	public Ticket(Urgenza urgenza, String richiesta) {
		this.ID = GeneratoreID.getNewID();
		this.urgenza = urgenza;
		this.richiesta = richiesta;
		this.isDone = false;
	}

	public Urgenza getUrgenza() {
		return urgenza;
	}

	public void setUrgenza(Urgenza urgenza) {
		this.urgenza = urgenza;
	}

	public String getRichiesta() {
		return richiesta;
	}

	public void setRichiesta(String richiesta) {
		this.richiesta = richiesta;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
}
