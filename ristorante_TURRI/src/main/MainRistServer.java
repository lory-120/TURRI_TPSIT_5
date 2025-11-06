package main;

import server.*;

public class MainRistServer {

	public static void main(String[] args) {
		int port=8888;//porta su cui si avvia il server
		Server s=new Server(port);//avvio il server sulla porta
		s.attendi();//aspetto che si connetta un client
	}
		
}
