package utilities;

public class Tavolo {
	private int posti;
	private boolean prenotato;
	private String nomePrenotazione;
	
	public Tavolo(int posti) {
			this.posti=posti;
			prenotato=false;
			nomePrenotazione=null;
	}
	
	public int getPosti() {
		return posti;
	}
	public boolean isPrenotato() {
		return prenotato;
	}

	public void prenota(String nome) {
		nomePrenotazione=nome;
		prenotato=true;
	}
	public void libera() {
		nomePrenotazione=null;
		prenotato=false;
	}
	@Override
	public String toString() {
		String msg="il tavolo ha " +posti+" posti"+ (prenotato?" e è prenotato a nome "+ nomePrenotazione:" non è prenotato");
		return msg;
	}
}
