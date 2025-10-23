package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerCalcUDP {

private DatagramSocket serverSocketUDP;
	
	public ServerCalcUDP(int port) {
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
	
	public void invia(InetAddress clientAddress, int port, String msg) throws IOException {

		String msgSendResult = Double.toString(elabora(msg));
		String msgSend = (msgSendResult == "-1") ? "Input errati." : "Il risultato è: " + msgSendResult;
		
		byte[] datiDaInviare = msgSend.getBytes();
		DatagramPacket pacchettoDaInviare = new DatagramPacket(datiDaInviare, datiDaInviare.length, clientAddress, port);
		
		serverSocketUDP.send(pacchettoDaInviare);
	}
	private double elabora(String msg) {
		String[] elementi = msg.split(";");
		double n1 = Double.parseDouble(elementi[0]);
		double n2 = Double.parseDouble(elementi[1]);
		
		switch(elementi[2]) {
		case "+":
			return n1 + n2;
		case "-":
			return n1 - n2;
		case "*":
			return n1 * n2;
		case "/":
			return n1 / n2;
		default:
			return -1;
		}
	}
	
	public void chiudi() {
		serverSocketUDP.close();
	}
	
}
