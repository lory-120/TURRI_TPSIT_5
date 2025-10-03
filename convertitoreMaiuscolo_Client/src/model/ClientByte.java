package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientByte {

	private Socket socket;
	private OutputStream output;
	private InputStream input;
	
	public ClientByte(String host, int port) {
		try {
			socket = new Socket(host, port);
			output = socket.getOutputStream();
			input = socket.getInputStream();
			System.out.println("Client connesso a server: " + host);
		} catch(IOException e) {
			System.out.println("Impossibile connettersi al server: " + e.getMessage());
		}
	}

	//getter e setter
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public OutputStream getOutput() {
		return output;
	}
	public void setOutput(OutputStream output) {
		this.output = output;
	}
	public InputStream getInput() {
		return input;
	}
	public void setInput(InputStream input) {
		this.input = input;
	}
	
	
	//metodi della funzione
	public void invia(String msg) throws IOException {
		byte[] bytes = msg.getBytes();
		output.write(bytes);
		output.flush();
	}
	
	public String ricevi() throws IOException {
		 byte[] buffer = new byte[1024];
		 int bufferSize = input.read(buffer);
		 if(bufferSize == -1) {
			 throw new IOException("Errore nella ricezione del messaggio dal server.");
		 }
		 return new String(buffer, 0, bufferSize);
	}
	
}
