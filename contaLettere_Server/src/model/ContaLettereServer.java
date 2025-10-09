package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ContaLettereServer extends Thread {

	private Socket clientSocket;
	
	public ContaLettereServer(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
		try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
			System.out.println("Client connesso.");
			
			String parola = in.readLine();
			
			int nVocali = nVocali(parola);
			int nConsonanti = nConsonanti(parola);
			
			out.print("Vocali: " + nVocali + " Consonanti: " + nConsonanti);
			
		} catch(IOException e) {
			System.err.println("Impossibile stabilire una connessione.");
		} catch(Exception e) {
			System.err.println("Errore generale: " + e.getMessage());
		} finally {
			try {
				clientSocket.close();
				System.out.println("Connessione con client chiusa correttamente.");
			} catch (IOException e) {
				System.err.println("Errore nlla chiusura della connessione col client.");
			}
		}
	}
	
	private int nVocali(String parola) {
		String vocali = "aeiouAEIOU";
		int count = 0;
		for(char c : parola.toCharArray()) {
			if(vocali.indexOf(c) != -1) count++;
		}
		return count;
	}
	
	private int nConsonanti(String parola) {
		String vocali = "aeiouAEIOU";
		int count = 0;
		for(char c : parola.toCharArray()) {
			if(vocali.indexOf(c) == -1) count++;
		}
		return count;
	}
	
}
