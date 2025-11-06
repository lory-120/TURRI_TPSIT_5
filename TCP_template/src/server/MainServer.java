package server;

import java.io.IOException;
import java.net.ServerSocket;

public class MainServer {
	public static void main(String[] args) {
		
		int port=8888;
		ServerSocket s=null;
		
		try {
			s = new ServerSocket(port);
			Server server = new Server(s);
			System.out.println("Server booted");
			
			server.connect();
			System.out.println("Connection enstablished on port: "+port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}

