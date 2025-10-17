package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

public class ClientCalcUDP {

	private int port;
	private DatagramSocket clientSocket;
	private InetAddress serverAddress;
	
	public ClientCalcUDP(int port) {
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
	
	public void invia(Double[] msgDaInviare, char operando) throws IOException {
		// serializzo il messaggio da inviare
		String[] msg = Arrays.stream(msgDaInviare)
		                               .filter(d -> d != null)
		                               .map(String::valueOf)
		                               .toArray(String[]::new);
		
		String msgSerializzato = String.join(";", msg);
		msgSerializzato += ";" + operando;
		
		byte[] buffer = msgSerializzato.getBytes();
		
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
