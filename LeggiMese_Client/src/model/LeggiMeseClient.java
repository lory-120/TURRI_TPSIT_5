package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import presentation.Tastiera;

public class LeggiMeseClient extends Thread {

	private static final String FIN_MSG = "FIN_MSG";
	private String IPAddress;
	private int port;
	
	
	public LeggiMeseClient(String name, String IPAddress, int port) {
		setName(name);
		this.IPAddress = IPAddress;
		this.port = port;
	}
	
	@Override
	public synchronized void run() {
		
		try (
			Socket socket = new Socket(IPAddress, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
		) {
			
			String msgRecv = "";
			String msgSend = "";
			while(true) {
				msgRecv = "(" + getName() + ") " + in.readLine();
				
				if(msgRecv.equals(FIN_MSG)) break;
				
				System.out.println(msgRecv);
				msgSend = Tastiera.leggiString();
				out.println(msgSend);
			}
			
			this.interrupt();
			
		} catch(IOException e) {
			System.err.println("(" + getName() + ") " + "Errore nella connessione col server: " + e.getMessage());
		}
		
	}
	
}
