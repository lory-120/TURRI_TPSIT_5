package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;
	
	public Server(ServerSocket serverSocket) { 
		this.serverSocket=serverSocket;	
	}
	
	public void connect() throws IOException {
		Socket clientSocket=serverSocket.accept();
		System.out.println("Server connected with client: "+clientSocket.getInetAddress());
		ClientHandler handler=new ClientHandler(clientSocket);
		handler.start();
	}
}
