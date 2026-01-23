package model;

import java.io.Serializable;
import java.sql.Date;

public class Servizio implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cod_fiscale;
	private String cod_meccanografico;
	private Date dt_inizio;
	private Date dt_fine;
	private String ruolo;
	private int num_ore_settimanali;
	private String note;
	
	public Servizio() {}

	
	
	public String getCod_fiscale() {
		return cod_fiscale;
	}

	public void setCod_fiscale(String cod_fiscale) {
		this.cod_fiscale = cod_fiscale;
	}

	public String getCod_meccanografico() {
		return cod_meccanografico;
	}

	public void setCod_meccanografico(String cod_meccanografico) {
		this.cod_meccanografico = cod_meccanografico;
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

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public int getNum_ore_settimanali() {
		return num_ore_settimanali;
	}

	public void setNum_ore_settimanali(int num_ore_settimanali) {
		this.num_ore_settimanali = num_ore_settimanali;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
