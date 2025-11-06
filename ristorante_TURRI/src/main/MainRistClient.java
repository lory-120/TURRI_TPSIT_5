package main;

import client.*;
import presentation.*;

public class MainRistClient {

	public static void main(String[] args) {
		int port=8888;
		String host="localhost";
		Client c=new Client(host, port);
		MenuClient m=new MenuClient(c);
		m.eseguiMenu();
		
	}

}
