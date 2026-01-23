package model;

import java.io.Serializable;
import java.sql.Date;

public class Docente implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cod_fiscale;
	private String cognome;
	private String nome;
	private String telefono;
	private String indirizzo;
	private String citta;
	private String cap;
	
	private Date dt_inizio;
	private Date dt_fine;
	
	public Docente() {}

	
	
	public String getCod_fiscale() {
		return cod_fiscale;
	}

	public void setCod_fiscale(String cod_fiscale) {
		this.cod_fiscale = cod_fiscale;
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

	public Date getDt_inizio() {
		return dt_inizio;
	}



	public void setDt_inizio(Date dt_inizio) {
		this.dt_inizio = dt_inizio;
	}



	public Date getDt_fine() {
		return dt_fine;
	}



	public void setDt_fine(Date dt_fine) {
		this.dt_fine = dt_fine;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
