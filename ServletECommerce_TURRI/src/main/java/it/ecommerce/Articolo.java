package it.ecommerce;

public class Articolo {

	private String ID;
	private String nome;
	private double prezzo;
	
	public Articolo(String ID, String nome, double prezzo) {
		this.ID = ID;
		this.nome = nome;
		this.prezzo = prezzo;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public String toString() {
		return "Articolo [ID=" + ID + ", nome=" + nome + ", prezzo=" + prezzo + "]";
	}
	
}
