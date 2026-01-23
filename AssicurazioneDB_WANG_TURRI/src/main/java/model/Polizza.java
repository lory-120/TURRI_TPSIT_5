package model;

import java.io.Serializable;
import java.sql.Date;

public class Polizza implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id_polizza;
	private String id_cliente;
	private Date dt_inizio;
	private Date dt_fine;
	private char periodicita_AST;
	private int premio_annuo;
	private String descrizione;
	
	public Polizza() {}
	
	public Polizza(String id_polizza, String id_cliente, Date dt_inizio, Date dt_fine, char periodicita_AST, int premio_annuo, String descrizione) {
		this.id_polizza = id_polizza;
		this.id_cliente = id_cliente;
		this.dt_inizio = dt_inizio;
		this.dt_fine = dt_fine;
		this.periodicita_AST = periodicita_AST;
		this.premio_annuo = premio_annuo;
		this.descrizione = descrizione;
	}


	public String getId_polizza() {
		return id_polizza;
	}

	public void setId_polizza(String id_polizza) {
		this.id_polizza = id_polizza;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Date getDt_inizio() {
		return dt_inizio;
	}

	public void setDt_inizio(Date date) {
		this.dt_inizio = date;
	}

	public Date getDt_fine() {
		return dt_fine;
	}

	public void setDt_fine(Date date) {
		this.dt_fine = date;
	}

	public char getPeriodicita_AST() {
		return periodicita_AST;
	}

	public void setPeriodicita_AST(char periodicita_AST) {
		this.periodicita_AST = periodicita_AST;
	}

	public int getPremio_annuo() {
		return premio_annuo;
	}

	public void setPremio_annuo(int premio_annuo) {
		this.premio_annuo = premio_annuo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
