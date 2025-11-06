package utilities;

public class ProtocolloRic{// classe che uso per standardizzare la comunicazione
	private String comando;//tipo di comando
	private int ind;//indice che mi serve per liberare il tavolo
	private int nPersone;//numero di persone che serve per la prenotazioneo per creeare un tavolo
	private String nomePrenotazione;//mi serve creare una prenotazione
	
	public ProtocolloRic(String msg) {
		
		String[] mess=msg.split(",");// divido il messaggio in base alle virgole in modo da separare i vari attributi
		switch(mess[0]) {//faccio un switch case in base al comando che sara il primo campo a essere scirtto nel messaggio 
		case "vis":// in caso sia visualizza mi interessa solo il tipo di comando
			comando="vis";
			ind=-1;
			nPersone=0;
			nomePrenotazione=null;
			break;
		case "pre"://in caso di prenotazione mi interessa il comando il numero di persone e il nome della prenotazione
			comando="pre";
			ind=-1;
			nPersone=Integer.parseInt(mess[1]);
			nomePrenotazione=mess[2];
			break;
		case "lib"://in caso di liberare il tavolo mi interessa l indice
			comando="lib";
			ind=Integer.parseInt(mess[1]);
			nPersone=0;
			nomePrenotazione=null;
			break;
		case "add"://in caso di aggiunta mi interessa il comandoe il numero di persone
			comando="add";
			ind=-1;
			nPersone=Integer.parseInt(mess[1]);
			nomePrenotazione=null;
			break;
		case "clo":// in caso di chiusura mi serve solo il comando
			comando="clo";
			ind=-1;
			nPersone=0;
			nomePrenotazione=null;
			break;
		default:
			comando=null;
			ind=-1;
			nPersone=0;
			nomePrenotazione=null;
			
		}
		
	}
	
	public String getComando() {
		return comando;
	}

	public void setComando(String comando) {
		this.comando = comando;
	}

	public int getInd() {
		return ind;
	}

	public void setInd(int ind) {
		this.ind = ind;
	}

	public int getnPersone() {
		return nPersone;
	}

	public void setnPersone(int nPersone) {
		this.nPersone = nPersone;
	}

	public String getNomePrenotazione() {
		return nomePrenotazione;
	}

	public void setNomePrenotazione(String nomePrenotazione) {
		this.nomePrenotazione = nomePrenotazione;
	}
	
	
}
	 
