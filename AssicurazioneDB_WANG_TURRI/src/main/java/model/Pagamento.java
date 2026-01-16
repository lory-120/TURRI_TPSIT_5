package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id_pagamento;
	private String id_polizza;
	private LocalDate dt_scadenza;
	private LocalDate dt_pagamento;
	//non c'è estremi_pagamento
	private double importo;
	private String note;
	
	public Pagamento() {}
	
	public Pagamento(String id_pagamento, String id_polizza, LocalDate dt_scadenza, LocalDate dt_pagamento, double importo, String note) {
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

	public LocalDate getDt_scadenza() {
		return dt_scadenza;
	}

	public void setDt_scadenza(LocalDate dt_scadenza) {
		this.dt_scadenza = dt_scadenza;
	}

	public LocalDate getDt_pagamento() {
		return dt_pagamento;
	}

	public void setDt_pagamento(LocalDate dt_pagamento) {
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
