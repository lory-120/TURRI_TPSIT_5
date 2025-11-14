package main;

import java.io.IOException;
import java.net.Socket;

import client.Client;

public class MainClient {

	public static void main(String args[]) {
		
		final int PORT = 8888;
		String serverAddress = "127.0.0.1";
		
		Socket clientSocket;
		try {
			clientSocket = new Socket(serverAddress, PORT);
			Client client = new Client(clientSocket);
			client.communicate();
		} catch (IOException e) {
			System.err.println("Errore di comunicazione: " + e.getMessage());
		}
		
	}
	
}
