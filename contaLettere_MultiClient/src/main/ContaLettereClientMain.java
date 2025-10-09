package main;

import model.ContaLettereClient;
import presentation.Tastiera;

public class ContaLettereClientMain {
	
	private static final int PORT = 7000;

	public static void main(String args[]) {
		
		System.out.println("Avvio di più client per il server alla porta " + PORT + "...");

		String nomeClient = "lore";
		String parola = Tastiera.leggiString("(" + nomeClient + ") Inserisci la parola: ");
		new ContaLettereClient(nomeClient, "127.0.0.1", PORT, parola).start();
		
	}
	
}
