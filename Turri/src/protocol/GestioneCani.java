package protocol;

import java.util.ArrayList;

public class GestioneCani {

	private ArrayList<Cane> cani;
	
	public GestioneCani() {
		this.cani = new ArrayList<Cane>();
	}
	
	public void aggiungiCane(Cane c) {
		cani.add(c);
	}
	
	public void registraVoto(String microchip, int voto) throws NullPointerException, IllegalArgumentException {
		Cane cane = ricercaCani(microchip);
		if(cane == null) {
			throw new NullPointerException("Cane non trovato, microchip sconosciuto: " + microchip);
		}
		cane.aggiungiVoto(voto);
	}
	
	public double getMediaCane(String microchip) throws NullPointerException {
		Cane cane = ricercaCani(microchip);
		if(cane == null) {
			throw new NullPointerException("Cane non trovato, microchip sconosciuto: " + microchip);
		}
		return cane.getMediaVoti();
	}
	
	public String getInfoCane(String microchip) throws NullPointerException {
		Cane cane = ricercaCani(microchip);
		if(cane == null) {
			throw new NullPointerException("Cane non trovato, microchip sconosciuto: " + microchip);
		}
		return cane.toString();
	}
	
	
	private Cane ricercaCani(String microchip) {
		for(Cane c : cani) {
			if(c.getMicrochip().equals(microchip)) {
				return c;
			}
		}
		return null;
	}
	
}
