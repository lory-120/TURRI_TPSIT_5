package utilities;

public class ProtocolloInv {// classe che uso per standardizzare l invio di messaggi
	private String comando;//tipo di comando
	private int ind;//indice che mi serve per liberare il tavolo
	private int nPersone;//numero di persone che serve per la prenotazioneo per creeare un tavolo
	private String nomePrenotazione;//mi serve creare una prenotazione

	public ProtocolloInv(String comando)throws WrongCommandException{//creo il messaggio  usando questro costruttore con solo il comando per la chiusura e la visualizzazione
		if(!comando.equals("vis")&&!comando.equals("clo")) {//controllo che sia uno di quwsi due comandi
			throw new WrongCommandException("Costruttore comando vis");// caso non lo siano mando l'errore
		}else {
			this.comando=comando;
		}
	}
	public ProtocolloInv(String comando,int persone,String nomePrenotazione)throws WrongCommandException{//creo il messaggio  usando questro costruttore con solo il comando per la prenotazione
		if(!comando.equals("pre")) {// in caso il comando non sia prenota
			throw new WrongCommandException("Costruttore comando vis");// invio un errore
		}else{
			nPersone=persone;
			this.nomePrenotazione=nomePrenotazione;
			this.comando=comando;
		}
	}
	public ProtocolloInv(String comando,int n)throws WrongCommandException{//creo il messaggio  usando questro costruttore con solo il comando per l aggiunta  e la liberazione
		if(!comando.equals("lib")&&!comando.equals("add")) {//se non è uno dei due comandi allora invio l'errore
			throw new WrongCommandException("Costruttore comando vis");
		}else {
			this.comando=comando;
			if(comando.equals("lib")){// distinguo tra i due comandi
				this.ind=n;
			}else {
				nPersone=n;
			}
			
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
	@Override
	public String toString() {
		switch(comando) {
		case "vis":
			return comando;
			
		case "pre":
			return comando+","+nPersone+","+nomePrenotazione;
			
		case "lib":
			return comando+","+ind;
			
		case "add":
			return comando+","+nPersone;
			
		case "clo":
			return comando;
			
		default:
			return "errore";
			
		}
	}
	
}
	 
