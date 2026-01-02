package it.model;

import java.util.Date;

public class Autore {
	
	private String idAutore;
	private String cognome;
	private String nome;
	private Date dataNascita;
	private String nazione;
	private char sesso;
	
	public Autore() {}
	
	public Autore(String idAutore, String cognome, String nome, Date dataNascita,
			String nazione, char sesso) {
		this.idAutore = idAutore;
		this.cognome = cognome;
		this.nome = nome;
		this.dataNascita = dataNascita;
		this.nazione = nazione;
		this.sesso = sesso;
	}
	
	public String getIdAutore() {
		return idAutore;
	}
	public void setId_autore(String idAutore) {
		this.idAutore = idAutore;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getNazione() {
		return nazione;
	}
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	public char getSesso() {
		return sesso;
	}
	public void setSesso(char sesso) {
		this.sesso = sesso;
	}
	
	
	@Override
	public String toString() {
		return "Autore [id_autore=" + idAutore + ", cognome=" + cognome + ", nome=" + nome
				+ ", dataNascita=" + dataNascita + ", nazione=" + nazione + ", sesso=" + sesso + "]";
	}
	
}
