package model;


public class Prodotto{
	private final int CODICE;
	private int quantita;
	
	public Prodotto(int codice, int quantita) {
		CODICE=codice;
		this.quantita=quantita;
	}
	
	public Prodotto(String campiDaFile[]) throws NumberFormatException{
		this.CODICE=Integer.parseInt(campiDaFile[0]);
		this.quantita=Integer.parseInt(campiDaFile[1]);
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public int getCODICE() {
		return CODICE;
	}

	@Override
	public String toString() {
		return "Prodotto [CODICE=" + CODICE + ", quantita=" + quantita + "]";
	}
}
