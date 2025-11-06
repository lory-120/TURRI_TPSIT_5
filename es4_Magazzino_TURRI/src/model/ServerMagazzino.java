package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMagazzino {
	private ServerSocket serverSocket;
	private MagazzinoHandler gestoreMagazzino;
	public ServerMagazzino(ServerSocket serverSocket, String filePath) { 
		this.serverSocket=serverSocket;	
		gestoreMagazzino=new MagazzinoHandler(filePath);
	}
	
	public void avvia() throws IOException {
		while(true) {
			try {
				Socket clientSocket=serverSocket.accept();
				ThreadComunicazioneClient threadClient=new ThreadComunicazioneClient(clientSocket, gestoreMagazzino);
				threadClient.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
