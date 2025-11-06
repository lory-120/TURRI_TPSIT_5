/*Progettare un protocollo applicativo UDP che richieda a un server di effettuare le comuni operazioni aritmetiche 
 * (somma, sottrazione, moltiplicazione, divisione e potenza) tra coppie di valori numerici. 
 * Scrivere in linguaggio Java un server UDP che implementi il protocollo progettato: 
 * verificarne il corretto funzionamento scrivendo uno specifico client.*/
package server;

import java.io.IOException;
import java.net.*;

public class ServerUDP {
	private DatagramSocket serverSocketUDP;
	
	public ServerUDP(int port) {
		try {
			this.serverSocketUDP=new DatagramSocket(port);
			System.out.println("Servizio avviato su porta: "+port);
		} catch (SocketException e) {
			e.printStackTrace();
		}		
	}
	
	public void comunica() throws IOException {
		DatagramPacket pacchettoRicevuto=ricevi();
		
		InetAddress clientAddress=pacchettoRicevuto.getAddress();
		int clientPort=pacchettoRicevuto.getPort();
		String messaggio=new String(pacchettoRicevuto.getData(),0,pacchettoRicevuto.getLength());
		String[] numeri=messaggio.split(";");
		
		if (numeri.length == 3) {
		    //try {
		        Double num1 = Double.parseDouble(numeri[0]);
		        Double num2 = Double.parseDouble(numeri[1]);
		        String operatore = numeri[2];

		        System.out.println("Numeri ricevuti e parsati con successo:");
		        
		        Double risultato = null;

	            // Esegue l'operazione corretta in base all'operatore
	            switch (operatore) {
	                case "+":
	                    risultato = num1 + num2;
	                    break;
	                case "-":
	                    risultato = num1 - num2;
	                    break;
	                case "*":
	                    risultato = num1 * num2;
	                    break;
	                case "/":
	                    if (num2 == 0) {
	                        System.out.println("ERRORE: Divisione per zero.");
	                    }
	                    risultato = num1 / num2;
	                    break;
	                case "^":
	                    risultato = Math.pow(num1, num2);
	                    break;
	                default:
	                    System.out.println("ERRORE: Operatore '" + operatore + "' non valido.");
	            }
		        String messaggioRisultato = risultato.toString();
	            invia(clientAddress, clientPort, messaggioRisultato);
		    /*} catch (NumberFormatException e) {
		        System.err.println("Errore: i dati ricevuti non sono numeri validi.");
		    }*/
		}
		else
			System.err.println("Errore: i dati ricevuti non sono numeri validi.");
	}		
	
	public DatagramPacket ricevi() throws IOException {
		byte[] arrayRicevuto=new byte[1024];
		DatagramPacket pacchettoRicevuto=new DatagramPacket(arrayRicevuto, arrayRicevuto.length);
		
		serverSocketUDP.receive(pacchettoRicevuto);
		System.out.println("Pacchetto ricevuto");
		return pacchettoRicevuto;
	}
	
	public void invia(InetAddress clientAddress, int port, String messaggioRisultato) throws IOException {
		byte[] datiDaInviare=messaggioRisultato.getBytes();
		DatagramPacket pacchettoDaInviare=new DatagramPacket(datiDaInviare, datiDaInviare.length, clientAddress, port);
		serverSocketUDP.send(pacchettoDaInviare);
		
	}
	
	public void chiudi() {		
		serverSocketUDP.close();		
	}
}
