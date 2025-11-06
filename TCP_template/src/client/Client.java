package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	private Socket socket;
	private BufferedReader in; 
	private PrintWriter out;
	
	public Client(Socket socket, BufferedReader in, PrintWriter out) {
		this.socket=socket;
		this.in=in;
		this.out=out;
	}
	
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
	
	
	
	public void begin() throws IllegalStateException, IOException {
		ThreadReceiving threadRx=new ThreadReceiving(in);
		ThreadSending threadTx=new ThreadSending(out);
		threadRx.start();
		threadTx.start();
	}
	
}
