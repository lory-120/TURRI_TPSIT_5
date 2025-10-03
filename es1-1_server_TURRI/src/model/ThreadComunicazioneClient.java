package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadComunicazioneClient extends Thread {
	private Socket socket;
	
	public ThreadComunicazioneClient(Socket clientSocket) {
		this.socket = clientSocket;
	}
	
	@Override
	public void run() {
		BufferedReader input = null;
		PrintWriter output = null;
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream());
		} catch(IOException e) {
			System.out.println("Errore nell'inizializzare la comunicazione in thread " + this.getName());
		}
		
		try {
			String msgRecv = input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
