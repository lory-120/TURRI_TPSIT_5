package main;

import java.io.IOException;

import model.ClientCalcUDP;
import presentation.Tastiera;

public class MainClientCalcUDP {
	
	private static final int PORT = 8888;

	public static void main(String[] args) {
		
		System.out.println("Avvio del client sulla porta " + PORT + "...");
		ClientCalcUDP client = new ClientCalcUDP(PORT);
		
		Double[] numeri = new Double[2];
		char operando;
		
		numeri[0] = Tastiera.leggiDouble("Primo operando: ");
		numeri[1] = Tastiera.leggiDouble("Secondo operando: ");
		
		operando = Tastiera.leggiChar("Operatore (+, -, *, /): ");
		
		String risposta = "";
		
		System.out.println("Comunicazione col server...");
		
		try {
			client.invia(numeri, operando);
			risposta = client.ricevi();
		} catch (IOException e) {
			System.out.println("Errore nella comunicazione: " + e.getMessage());
		}
		
		System.out.println("RISPOSTA DAL SERVER:");
		System.out.println(risposta);
		
		
		
	}

}
