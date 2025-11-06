package model;

import tombola.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

	private Socket clientSocket;
	private SchedaTombola schedaClient;
	
	public ClientHandler(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		this.schedaClient = new SchedaTombola();
		
		inviaScheda();
	}
	
	
	@Override
	public void run() {
		
	}
	
	
	
	
	private void inviaScheda() {
		try(ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
			out.writeObject(schedaClient);
			out.flush();
		} catch(IOException e) {
			System.err.println("Errore nell'invio della scheda: " + e.getMessage());
		}
		
	}
	
}
