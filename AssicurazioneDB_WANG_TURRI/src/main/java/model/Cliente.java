package model;

import java.io.Serializable;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id_cliente;
	private String cognome;
	private String nome;
	private String email;
	private String telefono;
	private String indirizzo;
	private String cap;
	private String citta;
	private String provincia;
	
	private int n_polizze;
	
	public Cliente() {}

	public Cliente(String id_cliente, String cognome, String nome, String email, String telefono, String indirizzo, String citta, String provincia) {
	    this.id_cliente = id_cliente;
	    this.cognome = cognome;
	    this.nome = nome;
	    this.email = email;
	    this.telefono = telefono;
	    this.indirizzo = indirizzo;
	    this.citta = citta;
	    this.provincia = provincia;
	}

	
	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getN_polizze() {
		return n_polizze;
	}

	public void setN_polizze(int n_polizze) {
		this.n_polizze = n_polizze;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
