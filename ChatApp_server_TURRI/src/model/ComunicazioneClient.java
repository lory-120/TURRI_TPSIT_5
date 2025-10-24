package z_chatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ComunicazioneClient extends Thread {
	private Socket altroClient, clientSocket;
	private PrintWriter outClient, outAltroClient;
	private BufferedReader inClient, inAltroClient;
	
	public ComunicazioneClient(Socket altroClient,Socket clientSocket) throws IOException {
		this.altroClient=altroClient;
		this.clientSocket=clientSocket;
		outClient= new PrintWriter(clientSocket.getOutputStream(), true);
		outAltroClient=new PrintWriter(altroClient.getOutputStream(), true);
		inClient=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		inAltroClient=new BufferedReader(new InputStreamReader(altroClient.getInputStream()));
	}
	
	
	@Override
	public void run() {
	
	}
}
