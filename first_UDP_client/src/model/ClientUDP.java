package model;

import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientUDP {

	private int port;
	private DatagramSocket clientSocket;
	private InetAddress serverAddress;
	
	public ClientUDP(int port) {
		try {
			clientSocket = new DatagramSocket();
			System.out.println("Client UDP pronto alla porta " + port);
		} catch(SocketException e) {
			System.err.println("Errore di apertura connessione: " + e.getMessage());
		}
		
		this.port = port;
		
		try {
			this.serverAddress = InetAddress.getByName("localhost");
		} catch(UnknownHostException e) {
			System.err.println("Impossibile ottenere l'IP del server: " + e.getMessage());
		}
	}
	
	public void invia(String msgDaInviare) throws IOException {
		byte[] buffer = msgDaInviare.getBytes();
		DatagramPacket pacchettoDaInviare = new DatagramPacket(buffer, buffer.length, serverAddress, port);
		clientSocket.send(pacchettoDaInviare);
	}
	
	public String ricevi() throws IOException {
		byte[] buffer = new byte[1024];
		DatagramPacket pacchettoRicevuto = new DatagramPacket(buffer, buffer.length);
		clientSocket.receive(pacchettoRicevuto);
		return new String(pacchettoRicevuto.getData(), 0, pacchettoRicevuto.getLength());
	}
	
}
