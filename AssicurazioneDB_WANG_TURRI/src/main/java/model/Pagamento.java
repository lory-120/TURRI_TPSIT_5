package model;

import java.io.Serializable;
import java.sql.Date;

public class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id_pagamento;
	private String id_polizza;
	private Date dt_scadenza;
	private Date dt_pagamento;
	private double importo;
	private String note;
	
	public Pagamento() {}
	
	public Pagamento(String id_pagamento, String id_polizza, Date dt_scadenza, Date dt_pagamento, double importo, String note) {
	    this.id_pagamento = id_pagamento;
	    this.id_polizza = id_polizza;
	    this.dt_scadenza = dt_scadenza;
	    this.dt_pagamento = dt_pagamento;
	    this.importo = importo;
	    this.note = note;
	}

	public String getId_pagamento() {
		return id_pagamento;
	}

	public void setId_pagamento(String id_pagamento) {
		this.id_pagamento = id_pagamento;
	}

	public String getId_polizza() {
		return id_polizza;
	}

	public void setId_polizza(String id_polizza) {
		this.id_polizza = id_polizza;
	}

	public Date getDt_scadenza() {
		return dt_scadenza;
	}

	public void setDt_scadenza(Date date) {
		this.dt_scadenza = date;
	}

	public Date getDt_pagamento() {
		return dt_pagamento;
	}

	public void setDt_pagamento(Date dt_pagamento) {
		this.dt_pagamento = dt_pagamento;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
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
