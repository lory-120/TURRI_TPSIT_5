package main;

import java.io.IOException;

import server.ServerUDP;

public class MainServerUDP {

	public static void main(String[] args) {
		int port=8888;
		ServerUDP server=new ServerUDP(port);
		while(true) {
			try {
				server.comunica();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}