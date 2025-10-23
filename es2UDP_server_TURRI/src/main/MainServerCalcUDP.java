package main;

import java.io.IOException;

import model.ServerCalcUDP;

public class MainServerCalcUDP {

	private static final int PORT = 8888;
	
	public static void main(String[] args) {

		System.out.println("Avvio server in porta " + PORT + "...");
		
		ServerCalcUDP server = new ServerCalcUDP(PORT);
		try {
			server.comunica();
		} catch (IOException e) {
			System.out.println("Errore nel server: " + e.getMessage());
		}
		
		System.out.println("Connessione chiusa. Chiusura programma...");
		
	}

}
