package it.model;

public class Editore {

	private String idEditore;
	private String ragioneSociale;
	private String indirizzo;
	private String citta;
	
	public Editore() {}
	
	public Editore(String idEditore, String ragioneSociale, String indirizzo, String citta) {
		this.idEditore = idEditore;
		this.ragioneSociale = ragioneSociale;
		this.indirizzo = indirizzo;
		this.citta = citta;
	}
	
	public String getIdEditore() {
		return idEditore;
	}
	public void setIdEditore(String idEditore) {
		this.idEditore = idEditore;
	}
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	@Override
	public String toString() {
		return "Editore [idEditore=" + idEditore + ", ragioneSociale=" + ragioneSociale
				+ ", indirizzo=" + indirizzo + ", citta=" + citta + "]";
	}
	
}
