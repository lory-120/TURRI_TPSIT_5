package server;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import protocol.Cane;
import protocol.GestioneCani;

public class Server {
	
	ServerSocket serverSocket;
	GestioneCani gestore;
	
	public Server(ServerSocket serverSocket, GestioneCani gestore) {
		this.serverSocket = serverSocket;
		this.gestore = gestore;
		initialize();
	}
	
	public void connect() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept();
			System.out.println("Server connesso con nuovo client: " + clientSocket.getInetAddress());
			ClientHandler handler = new ClientHandler(clientSocket, gestore);
			handler.start();
		}
	}
	
	
	private void initialize() {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("cani_iscritti.csv")))) {
			while(true) {
				String rawData = in.readLine();
				if(rawData != null) {
					String[] caneArgs = rawData.split(";");
					gestore.aggiungiCane(new Cane(caneArgs[0], caneArgs[1], caneArgs[2], caneArgs[3]));
				} else {
					System.out.println("Lettura file completata.");
					return;
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("File non trovato.");
		} catch (EOFException ee) {
			System.out.println("Lettura file completata.");
		} catch (IOException eee) {
			System.err.println("Errore nella lettura del file.");
		}
		System.out.println("Lettura file completata.");
	}
	
}
