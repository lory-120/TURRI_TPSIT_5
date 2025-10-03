package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerByte {

	private ServerSocket server;
	private int port;
	
	public ServerByte(int port) {
		this.port = port;
		try {
			server = new ServerSocket(port);
			System.out.println("Servizio avviato in porta " + port + ".");
		} catch(IOException e) {
			System.out.println("Avvio del servizio non riuscito: " + e.getMessage());
		}
	}
	
	
	public void attendi() {
		while(true) { //attesa infinita
			try {
				Socket clientSocket = server.accept();
				System.out.println("Server connesso con client: " + clientSocket.getInetAddress());
			
				try {
					String messaggio = ricevi(clientSocket);
					invia(clientSocket, messaggio);
				} catch(IOException e) {
					System.out.println("Errore nell'invio del messaggio al client: " + e.getMessage());
				}
			} catch(IOException e) {
				System.out.println("Impossibile connettersi al client: " + e.getMessage());
			}
		}
	}
	
	private String ricevi(Socket clientSocket) throws IOException {
		InputStream input = clientSocket.getInputStream();
		byte[] buffer = new byte[1024];
		
		int bufferSize = input.read(buffer); //riceve quello che comunica il client, e lo metti in questo buffer
		if(bufferSize == -1) {
			throw new IOException("Connessione col client interrotta.");
		}
		
		String messaggioRicevuto = new String(buffer, 0, bufferSize);
		return messaggioRicevuto;
	}
	
	private void invia(Socket clientSocket, String messaggio) throws IOException {
		OutputStream output = clientSocket.getOutputStream();
		String msgMaiuscolo = messaggio.toUpperCase();
		output.write(msgMaiuscolo.getBytes());
		System.out.println("Messaggio inviato al client: " + msgMaiuscolo);
	}
	
}
