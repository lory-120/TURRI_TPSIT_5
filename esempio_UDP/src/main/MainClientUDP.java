package main;

import java.io.IOException;

import client.*;
import presentation.*;

public class MainClientUDP {

	public static void main(String[] args) {
		String messaggio=Tastiera.leggiString("Immetti primo numero da inviare: ");
		messaggio+=";";
		messaggio+=Tastiera.leggiString("Immetti secondo numero da inviare: ");
		messaggio+=";"+Tastiera.leggiInt("Immetti  per +, per -, per *, per /, o  per ^: ");
		int port=8888;
		
		ClientUDP client=new ClientUDP(port, messaggio);
		try {
			client.invia(messaggio);
			String msgRicevuto=client.ricevi();
			System.out.println("Ricevuto dal server: "+msgRicevuto);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
