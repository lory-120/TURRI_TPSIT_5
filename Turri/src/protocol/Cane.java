package protocol;

import java.util.ArrayList;

public class Cane {

	private String microchip;
	private String nome;
	private String razza;
	private String proprietario;
	private ArrayList<Integer> voti;
	
	public Cane(String microchip, String nome, String razza, String proprietario) {
		this.microchip = microchip;
		this.nome = nome;
		this.razza = razza;
		this.proprietario = proprietario;
		this.voti = new ArrayList<Integer>();
	}

	//metodi get/set
	public String getMicrochip() {
		return microchip;
	}
	public void setMicrochip(String microchip) {
		this.microchip = microchip;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRazza() {
		return razza;
	}
	public void setRazza(String razza) {
		this.razza = razza;
	}
	public String getProprietario() {
		return proprietario;
	}
	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}
	public ArrayList<Integer> getVoti() {
		return voti;
	}
	public void setVoti(ArrayList<Integer> voti) {
		this.voti = voti;
	}
	public void aggiungiVoto(int voto) throws IllegalArgumentException {
		if(voto < 1 || voto > 100) {
			throw new IllegalArgumentException("Il voto deve essere compreso tra 1 e 100. Valore non ammesso : " + voto);
		} else {
			voti.add(voto);
		}
	}
	public double getMediaVoti() {
		double sum = 0;
		int div = 0;
		for(Integer v : voti) {
			sum += v;
			div++;
		}
		
		return sum/div;
	}
	
	@Override
	public String toString() {
		return nome + ", di razza " + razza + ". E' di " + proprietario;
	}
	
}
