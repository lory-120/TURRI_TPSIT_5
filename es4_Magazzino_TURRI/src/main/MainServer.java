package main;

import model.*;
import java.io.IOException;
import java.net.ServerSocket;

public class MainServer {
	public static void main(String[] args) {
		int port=8888;
		ServerSocket s=null;
		try {
			s = new ServerSocket(port);
			ServerMagazzino server=new ServerMagazzino(s, "prodotti.csv");
			System.out.println("AVVIATA CONNESSIONE SU PORTA: "+port);
			server.avvia();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
