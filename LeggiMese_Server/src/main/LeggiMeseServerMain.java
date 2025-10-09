package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import model.LeggiMeseServer;

public class LeggiMeseServerMain {

	private static final int PORT = 7000;
 	
	public static void main(String[] args) {

		System.out.println("Avviando il server...");
		
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			
			System.out.println("Server avviato. Porta: " + PORT);

			while(true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Nuova connessione da: " + clientSocket.getInetAddress());
				new LeggiMeseServer(clientSocket).start();
			}
			
		} catch(IOException e) {
			System.err.println("Impossibile avviare il server: " + e.getMessage());
		}
		
	}

}
