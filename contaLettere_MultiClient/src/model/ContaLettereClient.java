package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ContaLettereClient extends Thread {

	private String IPAddress;
	private int port;
	private String parola;
	
	
	public ContaLettereClient(String name, String IPAddress, int port, String parola) {
		setName(name);
		this.IPAddress = IPAddress;
		this.port = port;
		this.parola = parola;
	}
	
	@Override
	public synchronized void run() {
		try (
			Socket socket = new Socket(IPAddress, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
		) {
			
			out.println(parola);
			String risposta = in.readLine();
			System.out.println("(Client " + getName() + ") Risposta: " + risposta);
			
		} catch(IOException e) {
			System.err.println("Errore nella connessione col server. (");
		}
	}
	
}
