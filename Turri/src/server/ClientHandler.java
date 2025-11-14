package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import protocol.GestioneCani;

public class ClientHandler extends Thread {

	private Socket clientSocket;
	private GestioneCani gestore;
	private boolean end; //segna la fine della comunicazione
	
	public ClientHandler(Socket clientSocket, GestioneCani gestore) {
		this.clientSocket = clientSocket;
		this.gestore = gestore;
		this.end = false;
	}
	
	@Override
	public void run() {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
			
			while(true) {
				String request = in.readLine();
				String response = handleRequest(request);
				out.println(response);
				
				if(end) {
					break;
				}
			}
				
		} catch (IOException e) {
			System.err.println("Errore nella comunicazione col client: " + e.getMessage());
		}
	}
	
	private String handleRequest(String request) {
		String[] fullRequest = request.split(",");
		
		String response = "";
		boolean error = false;
		
		switch(fullRequest[0]) {
		case "SCORE":
			try {
				gestore.registraVoto(fullRequest[1], Integer.parseInt(fullRequest[2]));
			} catch(NullPointerException e) {
				response = e.getMessage();
				error = true;
			} catch(IllegalArgumentException ee) {
				response = ee.getMessage();
				error = true;
			}
			if(!error) {
				response = "Voto registrato al cane " + fullRequest[1] + ".";
			}
			break;

		case "AVERAGE":
			double avg = 0;
			try {
				avg = gestore.getMediaCane(fullRequest[1]);
			} catch(NullPointerException e) {
				response = e.getMessage();
				error = true;
			}
			if(!error) {
				response = "La media del cane " + fullRequest[1] + " è " + avg + ".";
			}
			break;
			
		case "INFO":
			try {
				response = gestore.getInfoCane(fullRequest[1]);
			} catch(NullPointerException e) {
				response = e.getMessage();
				error = true;
			}
			break;
			
		case "QUIT":
			end = true;
			response = "Arrivederci!";
			break;
			
		default: response = "Richiesta sconosciuta o ambigua. Riprova.";
		}
		
		return response;
	}
	
}
