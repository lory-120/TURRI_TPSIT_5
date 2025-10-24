package z_chatServer;

import java.io.IOException;
import java.net.ServerSocket;


public class MainChatServer {

	public static void main(String[] args) {
		int port=8888;
		ServerSocket s=null;
		try {
			s = new ServerSocket(port);
			ServerChat server=new ServerChat(s);
			System.out.println("AVVIATA CONNESSIONE SU PORTA: "+port);
			server.avvia();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
