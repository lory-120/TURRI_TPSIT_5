package model;

import java.time.LocalDate;

public class Personale {

	private String matricola;
	private String dipartimento;
	private String nominativo;
	private String qualifica;
	private LocalDate dataNascita;
	private double stipendio;
	
	public Personale(String matricola, String dipartimenti, String nominativo, String qualifica,
			LocalDate dataNascita, double stipendio) {
		
	}
	
	
	

	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	public String getDipartimento() {
		return dipartimento;
	}
	public void setDipartimento(String dipartimenti) {
		this.dipartimento = dipartimenti;
	}
	public String getNominativo() {
		return nominativo;
	}
	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}
	public String getQualifica() {
		return qualifica;
	}
	public void setQualifica(String qualifica) {
		this.qualifica = qualifica;
	}
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	public double getStipendio() {
		return stipendio;
	}
	public void setStipendio(double stipendio) {
		this.stipendio = stipendio;
	}
	
}
