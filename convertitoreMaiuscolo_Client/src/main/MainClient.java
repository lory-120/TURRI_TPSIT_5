package main;
import presentation.*;

import java.io.IOException;

import model.ClientByte;

public class MainClient {

	public static void main(String args[]) {
		
		int port = 8888;
		String host = "localhost";
		ClientByte c = new ClientByte(host, port);
		
		String msgOut = Tastiera.leggiString("Immetti messaggio da inviare: ");
		try {
			c.invia(msgOut);
			System.out.println("Inviato al server: '" + msgOut + "'");
		} catch(IOException e) {
			System.out.println("Errore di sonnessione: " + e.getMessage());
		}
		
		try {
			String msgReceive = c.ricevi();
			System.out.println("Messaggio ricevuto dal server: '" + msgReceive + "'");
		} catch(IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				c.getInput().close();
				c.getOutput().close();
				c.getSocket().close();
			} catch(IOException e) {
				System.out.println("Impossibile chiudere: connessione già persa.");
			}
		}
	}
	
}
