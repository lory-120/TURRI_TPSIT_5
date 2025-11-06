package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

	private Socket clientSocket;

	
	public ClientHandler(Socket clientSocket) {
		this.clientSocket=clientSocket;
	}
	
	public void run() {
		try {
			comm(clientSocket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void comm(Socket clientSocket) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
	//istruzioni
	}

}
