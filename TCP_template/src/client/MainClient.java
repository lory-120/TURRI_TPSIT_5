package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainClient {

	public static void main(String[] args) {
		
		int port = 8888;
		String address = "127.0.0.1";
		Socket s = null;
		
		try {
			s = new Socket(address, port);
			Client client = new Client(s, new BufferedReader(new InputStreamReader(s.getInputStream())), new PrintWriter(s.getOutputStream(), true));
			client.begin();
			System.out.println("Client booted successfully in port " + port);
		} catch (IOException e) {
			System.err.println("Errore con la connessione al server: " + e.getMessage());
		}
		
	}

}
