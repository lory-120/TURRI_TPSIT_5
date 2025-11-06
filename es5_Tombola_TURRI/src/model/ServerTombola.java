package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import tombola.EstrattoreNumeriTombola;

public class ServerTombola {

	private ServerSocket serverSocket;
	private ArrayList<ClientHandler> clientInGioco;
	private boolean partitaAvviata;
	
	public ServerTombola(ServerSocket serverSocket) {
		this.partitaAvviata = false;
		this.serverSocket = serverSocket;
		this.clientInGioco = new ArrayList<ClientHandler>(); 
	}
	
	public void avvia() {
		while(true) {
			try {
				Socket clientSocket = serverSocket.accept();
				ClientHandler threadClient = new ClientHandler(clientSocket);
				clientInGioco.add(threadClient);
				if(partitaAvviata) {
					break;
				}
			} catch (IOException e) {
				System.err.println("Errore nell'accettazione della connessione col client: " + e.getMessage());
			}
		}
	}
	
	
	public void avviaPartita() {
		partitaAvviata = true;
		
		for(ClientHandler c : clientInGioco) {
			c.start();
		}
		
		EstrattoreNumeriTombola estrattore = new EstrattoreNumeriTombola();
		int numero;
		
		while(true) {
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				System.err.println("Errore nello svolgimento della partita.");
			}
			
			numero = estrattore.estraiNumero();
			
		}
		
	}
	
}
