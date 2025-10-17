package main;

import java.io.IOException;

import model.ClientUDP;
import presentation.Tastiera;

public class MainClientUDP {

	public static void main(String[] args) {

		int port = 8888;
		ClientUDP client = new ClientUDP(port);
		
		try {
			client.invia(Tastiera.leggiString("Inserisci il messaggio da inviare al server: "));
			String msgRecv = client.ricevi();
			System.out.println("Ricevuto dal server: " + msgRecv);
		} catch(IOException e) {
			System.err.println("Errore nella comunicazione col server: " + e.getMessage());
		}
		
	}

}
