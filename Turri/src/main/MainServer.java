package main;

import java.io.IOException;
import java.net.ServerSocket;

import protocol.GestioneCani;
import server.Server;

public class MainServer {
	
	public static void main(String args[]) {
		
		final int PORT = 8888;
		GestioneCani gestore = new GestioneCani();
		
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(PORT);
			Server server = new Server(serverSocket, gestore);
			server.connect();
		} catch (IOException e) {
			System.err.println("Errore di comunicazione: " + e.getMessage());
		}
		
	}
	
}
