package main;

import java.io.IOException;

import model.ServerUDP;

public class MainServerUDP {

	public static void main(String args[]) {
		
		int port = 8888;
		ServerUDP server = new ServerUDP(port);
		
		while(true) {
			try {
				server.comunica();
			} catch (IOException e) {
				System.err.println("Errore nella comunicazione col client: " + e.getMessage());
			}
		}
		
	}
	
}
