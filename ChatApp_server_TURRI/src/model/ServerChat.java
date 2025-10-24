package z_chatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerChat {
	private ServerSocket serverSocket;
	private ListaClient listaClient;
	public ServerChat(ServerSocket serverSocket) { 
		this.serverSocket=serverSocket;	
		this.listaClient=new ListaClient();
	}
	
	public void avvia() throws IOException {
		while(true) {
			Socket clientSocket=serverSocket.accept();
			System.out.println("Server connesso con client: "+clientSocket.getInetAddress());
			ClientHandler threadChat=new ClientHandler(listaClient, clientSocket);
			threadChat.start();
			
			
		}
	}
}
