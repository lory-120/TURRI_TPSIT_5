package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket server;
	private int port;
	
	public Server(int port) {
		this.port = port;
		try {
			server = new ServerSocket(port);
			System.out.println("Servizio avviato in porta " + port + ".");
		} catch(IOException e) {
			System.out.println("Avvio del servizio non riuscito: " + e.getMessage());
		}
	}
	
	public void avvia() {
		while(true) {
			try {
				Socket clientSocket = server.accept();
				System.out.println("Connessione stabilita con " + clientSocket.getInetAddress());
				ThreadComunicazioneClient threadComunicazione = new ThreadComunicazioneClient(clientSocket);
				threadComunicazione.start();
			} catch(IOException e) {
				System.out.println("Errore nell'avvio di connessione: " + e.getMessage());
			}
		}
	}
	
}
