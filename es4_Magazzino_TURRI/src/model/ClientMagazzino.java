package model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMagazzino {
	private Socket socket;
	private BufferedReader in; 
	private PrintWriter out;
	
	public ClientMagazzino(String host, int port) {
		try {
			socket=new Socket(host, port);
			System.out.println("Client connesso al server: "+host);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}  catch (IOException e) {
			System.out.println("Impossibile stabilire la connessione!");
			e.printStackTrace();
		}
	}

	//get e set
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}
	public void invia(String messaggioDaInviare) {
        out.println(messaggioDaInviare);
    }
	
	public String ricevi() throws IllegalStateException, IOException {
		String risposta = in.readLine();
		System.out.println(risposta);
		if (risposta == null) {
           throw new IllegalStateException("Risposta non ricevuta!");
        }
		return risposta;
    }
	
}
