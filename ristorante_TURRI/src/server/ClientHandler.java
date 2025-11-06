package server;

import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import utilities.*;

public class ClientHandler extends Thread{
	private GestioneRistorante gs;//gestione la tengo èer modificare i dati del ristorante
	private InputStream in;//input stream da dove prendo i dati la  dichiaro qua alemno non si interrompe
	private OutputStream out;//input stream da dove invio i dati la  dichiaro qua alemno non si interrompe
	private Socket clientSocket	;//il socket
	public ClientHandler(Socket s,GestioneRistorante gs) {//costuttore prendo il socket e prendo gli stream
		this.gs=gs;
		try {
			this.in=s.getInputStream();		
			this.out=s.getOutputStream();
			clientSocket=s;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		ProtocolloRic mess=null;
		do {
			
			try {
				mess=new ProtocolloRic(ricevi());//prende un messaggio dal metodo ricevi che prende dati dal client collegato al socket tramite lo stream e lo passo al costruttore del protocollo
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ErroreComunicazioneClientException e) {
				e.printStackTrace();
			}
			try {
			switch(mess.getComando()) {//in base al comando inserito nel messagio scelgo cosa fare
			case "vis":
				invia(gs.visualizza());// richiamo il metodo della gestione chhe mi rende una stringa che invio al client quando mi manda un messaggio con comando visualizza
				break;
			case "pre":// in caso il comando sia prenota
				if(!gs.prenotaTavolo(mess.getnPersone(),mess.getNomePrenotazione())) {//prenoto il tavolo e invio la conferma al client
					invia("non è stato possibile prenotare");
				}else {
					invia("il tavolo è stato prenotato");//se viene trovato il tavolo da prenotare lo comunico al client
				}
				break;
			case "lib"://comando libera 
				if(!gs.liberaTavolo(mess.getInd())) {//chiamo il metodo per liberare il tavolo
					invia("non è stato trovato il tavolo");
				}else {
					invia("il tavolo è stato liberato");//in caso non venga trovato il tavolo con quel indice ritorna un messaggio negativo
				}
				break;
			case "add"://comando aggiungi
				if(gs.aggiungiTavolo(mess.getnPersone())){//chiamo il relativo metodo 
					invia("il tavolo è stato creato");// in caso riesca invio il messaggio  al client
				}else {
					invia("non è stato possibile creatre il tavolo");//se non è riuscito invio un messaggio di risposta negativa
				}
				break;
			case "clo"://messaggio di chiusura della connessione
				invia("grazie per aver usato il nostro servizio");
				clientSocket.close();//chiudo la connessione con client
				break;
			default:
				
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}while(!mess.getComando().equals("clo"));//continuo ad aspettare il messaggio finchè non mi chiede di chiudere la connessione
		
	}
	private String ricevi() throws IOException, ErroreComunicazioneClientException {//funzione ricezione 
		
		byte [] buffer=new byte[1024];//creo un buffer
		
		int dimensioneBuffer=in.read(buffer);//leggo dall input stream e metto dentro al buffer e restituisce la dimensione del buffer ricevuto(è un metodo bloccante cioè finche non arriva un messaggio continua ad aspettare e non mandare avantil 'esecuzione)
		if(dimensioneBuffer==-1) {
			throw new ErroreComunicazioneClientException();
		}
		String msg=new String(buffer,0,dimensioneBuffer);//faccio il parsing del buffer
		return msg;		
	}
	private void invia(String messaggio) throws IOException {//funzione invio messaggi tramite l'imput stream
		
		out.write(messaggio.getBytes());//traduco in byte la stinga da inviare e la mandao nell output stream che arriverà al client
		System.out.println("Inviato al client: "+clientSocket.getInetAddress()+" messaggio: "+messaggio);//stampa di controllo invio
	}
		
}
