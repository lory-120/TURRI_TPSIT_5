package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import presentation.Tastiera;

public class Client {

	private Socket socket;
	
	public Client(Socket socket) {
		this.socket = socket;
	}
	
	public void communicate() {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
				
				while(true) {
					String requestToServer = Tastiera.leggiString("Invia al server: ");
					out.println(requestToServer);
					System.out.println(in.readLine());
					if(requestToServer.contains("QUIT")) {
						break;
					}
				}
			
			} catch (IOException e) {
				System.err.println("Errore nella comunicazione col client: " + e.getMessage());
			}
	}
	
	
	/*
	public void sendToServer(String msg) {
		try(PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
			out.println(msg);
			
		} catch (IOException e) {
			System.err.println("Errore nell'invio al server: " + e.getMessage());
		}
	}
	
	public String recvFromServer() {
		String msgRecv = "";
		
		try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			msgRecv = in.readLine();
		} catch (IOException e) {
			System.err.println("Errore nella ricezione dal server: " + e.getMessage());
		}
		
		return msgRecv;
	}
	*/
	
}
