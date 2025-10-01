package main;

import java.net.Socket;
import java.time.LocalDate;

import model.*;

public class MainSimulazioneSever {

	public static void main(String args[]) {
		
		Server server = new Server();
		Socket mySocket = server.attendi();
		server.comunica(mySocket, LocalDate.now().toString());
		
		
	}
	
}
