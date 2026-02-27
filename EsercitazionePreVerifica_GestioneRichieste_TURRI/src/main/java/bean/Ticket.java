package bean;

import java.io.Serializable;

public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	private int ID;
	private String richiesta;
	
	public Ticket() {}

	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getRichiesta() {
		return richiesta;
	}

	public void setRichiesta(String richiesta) {
		this.richiesta = richiesta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
