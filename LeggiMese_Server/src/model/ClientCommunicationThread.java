package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientCommunicationThread extends Thread {

	private static final String FIN_MSG = "FIN_MSG";
	private Socket clientSocket;
	
	public ClientCommunicationThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
		try (
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		) {
			int nMese = -1;
			String risposta;

			out.println("Inserisci un numero (1-12): ");
			do {
			    
			    risposta = in.readLine();

			    try {
			        nMese = Integer.parseInt(risposta);
			    } catch (NumberFormatException e) {
			        nMese = -1; 
			    }
			    
			    if (nMese == -1 || nMese < 1 || nMese > 12) {
		            out.println("Il numero deve essere valido. Riprova: ");
		            nMese = -1;
		        }
			    
			} while (nMese == -1); 
			
			out.println("Il mese è: " + getMese(nMese));
			
			out.println(FIN_MSG);
			
		} catch(IOException e) {
			System.err.println("Errore nella connessione col client: " + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				System.err.println("Errore nella chiusura della connessione col client: " + e.getMessage());
			}
		}
	}

	private String getMese(int nMese) {
	    switch(nMese) {
	        case 1: return "Gennaio";
	        case 2: return "Febbraio";
	        case 3: return "Marzo";
	        case 4: return "Aprile";
	        case 5: return "Maggio";
	        case 6: return "Giugno";
	        case 7: return "Luglio";
	        case 8: return "Agosto";
	        case 9: return "Settembre";
	        case 10: return "Ottobre";
	        case 11: return "Novembre";
	        case 12: return "Dicembre";
	        default: throw new IllegalArgumentException("E' stato passato un mese inesistente.");
	    }
	}
	
}
