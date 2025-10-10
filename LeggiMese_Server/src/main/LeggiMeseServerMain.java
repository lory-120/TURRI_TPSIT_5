package main;

import model.LeggiMeseServer;

public class LeggiMeseServerMain {

	private static final int PORT = 7000;
 	
	public static void main(String[] args) {

		System.out.println("Avviando il server...");
		LeggiMeseServer server = new LeggiMeseServer(PORT);
		server.avvia();
		
	}

}
