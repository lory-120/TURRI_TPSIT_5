package main;

import model.LeggiMeseClient;

public class LeggiMeseClientMain {

	private static final int PORT = 7000;

	public static void main(String args[]) {
		
		System.out.println("Avvio di più client per il server alla porta " + PORT + "...");

		String nomeClient = "lore";
		new LeggiMeseClient(nomeClient, "127.0.0.1", PORT).start();
		
	}
	
}
