package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerUDP {

	private DatagramSocket serverSocketUDP;
	
	public ServerUDP(int port) {
		try {
			this.serverSocketUDP = new DatagramSocket(port);
		} catch(SocketException e) {
			System.err.println("Errore nell'avvio server UDP: " + e.getMessage());
		}
		System.out.println("Server avviato. Porta: " + port);
	}
	
	public void comunica() throws IOException {
		DatagramPacket pacchettoRicevuto = ricevi();
		
		InetAddress clientAddress = pacchettoRicevuto.getAddress();
		int clientPort = pacchettoRicevuto.getPort();
		String msg = new String(pacchettoRicevuto.getData(), 0, pacchettoRicevuto.getLength());
		
		invia(clientAddress, clientPort, msg);
	}
	
	public DatagramPacket ricevi() throws IOException {
		byte[] arrayRicevuto = new byte[1024];
		DatagramPacket pacchettoRicevuto = new DatagramPacket(arrayRicevuto, arrayRicevuto.length);
		
		serverSocketUDP.receive(pacchettoRicevuto);
		System.out.println("Pacchetto ricevuto.");
		return pacchettoRicevuto;
	}
	
	public void invia(InetAddress clientAddress, int port, String msg) {
		
	}
}
