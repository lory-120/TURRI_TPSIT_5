package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import utilities.*;

public class Client {
	
	private Socket socket;
	private OutputStream out;
	private InputStream in;
	
	public Client(String host, int port) {
		try {
			socket=new Socket(host, port);//creo un nuovo socket con il server avvio di conseguenza una connessione
			out=socket.getOutputStream();//prendo l inputstream
			in=socket.getInputStream();//prendo l'outpustream
			System.out.println("Client connesso a server: "+host);
			
		} catch (IOException e) {
			System.out.println("Impossibile connettersi al server");
			e.printStackTrace();
		}		
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public OutputStream getOut() {
		return out;
	}

	public void setOut(OutputStream out) {
		this.out = out;
	}

	public InputStream getIn() {
		return in;
	}

	public void setIn(InputStream in) {
		this.in = in;
	}
	
	public void invia(String messaggioDaInviare) throws IOException {// metodo per inviare il messaggio al server
		byte [] bytes = messaggioDaInviare.getBytes();//trasformo il messaggio in byte e poi lo invio
		out.write(bytes);
		out.flush();//libero il flusso e invio tutto
	}
	
	public String ricevi() throws IOException, ErroreComunicazioneClientException {// metodo per ricevere messaggi dal server
		byte[] buffer = in.readAllBytes();
		int dimensioneBuffer = buffer.length;// read come prima blocante
		if(dimensioneBuffer==-1) {
			throw new ErroreComunicazioneClientException();
		}
		return new String(buffer, 0, dimensioneBuffer);
	}
	
	
}
