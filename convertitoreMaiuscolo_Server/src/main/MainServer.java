package main;

import model.ServerByte;

public class MainServer {

	public static void main(String args[]) {
		
		int port = 8888;
		ServerByte s = new ServerByte(port);
		s.attendi();
		
	}
	
}
