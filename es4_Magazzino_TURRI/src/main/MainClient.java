package main;

import model.*;
import presentation.*;
import java.io.IOException;

public class MainClient {

	public static void main(String[] args) {
		int port=8888;
		String host="localhost";
		ClientMagazzino c=new ClientMagazzino(host, port);
		try {
			//COMANDI:
		    //ORDINA 0, codice 1, quantità2
		    //GETQ 0, codice 1
			boolean chiudi=false;
			while(!chiudi) {
				String messaggioDaInviare = Tastiera.leggiString("Immetti comando da inviare al server: ");
				c.invia(messaggioDaInviare);
				String mexRicevuto=c.ricevi();
				if(mexRicevuto.equals("Arrivederci!")) {
					chiudi=true;
				}
				System.out.println("Messaggio ricevuto dal server: "+mexRicevuto);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				c.getIn().close();
				c.getOut().close();
				c.getSocket().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	}

}
