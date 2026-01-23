package model;

import java.io.Serializable;

public class Scuola implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cod_meccanografico;
	private String nome;
	private String indirizzo;
	private String citta;
	private String cap;
	private String mail;
	private String telefono;
	
	public Scuola() {}

	
	
	public String getCod_meccanografico() {
		return cod_meccanografico;
	}

	public void setCod_meccanografico(String cod_meccanografico) {
		this.cod_meccanografico = cod_meccanografico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
