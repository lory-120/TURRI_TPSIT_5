package it.model;

public class Libro {
	
	private String isbn;
	private String titolo;
	private String lingua;
	private int anno;
	private double costo;
	private String idAutore;
	private String idEditore;
	
	public Libro() {}
	
	public Libro(String isbn, String titolo, String lingua, int anno,
			double costo, String idAutore, String idEditore) {
		this.isbn = isbn;
		this.titolo = titolo;
		this.lingua = lingua;
		this.anno = anno;
		this.costo = costo;
		this.idAutore = idAutore;
		this.idEditore = idEditore;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getLingua() {
		return lingua;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public String getIdAutore() {
		return idAutore;
	}
	public void setIdAutore(String idAutore) {
		this.idAutore = idAutore;
	}
	public String getIdEditore() {
		return idEditore;
	}
	public void setIdEditore(String idEditore) {
		this.idEditore = idEditore;
	}
	
	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titolo=" + titolo + ", lingua=" + lingua + ", anno=" + anno
				+ ", costo=" + costo + ", idAutore=" + idAutore + ", idEditore="
				+ idEditore + "]";
	}
	
}
