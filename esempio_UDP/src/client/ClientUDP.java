package client;

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
	String messaggio/* = "125.5;10.2"*/;
	byte[] datiDaInviare = messaggio.getBytes();
	
	public ClientUDP(int port, String messaggio) {
		try {
			clientSocket=new DatagramSocket();
			System.out.println("Client UDP pronto: "+port );
		} catch (SocketException e) {
			e.printStackTrace();
		}
		this.port=port;
		this.messaggio=messaggio;
		try {
			this.serverAddress=InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void invia(String msgDaInviare) throws IOException {
		byte[] bufferByte=msgDaInviare.getBytes();
		DatagramPacket pacchettoInvio=new DatagramPacket(bufferByte, bufferByte.length, serverAddress, port);
		clientSocket.send(pacchettoInvio);
	}
	
	public String ricevi() throws IOException{
		byte[] bufferByte=new byte[1024];
		DatagramPacket pacchettoRicevuto=new DatagramPacket(bufferByte, bufferByte.length);
		clientSocket.receive(pacchettoRicevuto);
		return new String(pacchettoRicevuto.getData(), 0, pacchettoRicevuto.getLength());
	}
}
