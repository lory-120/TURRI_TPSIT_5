package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadComunicazioneClient extends Thread {
	private Socket clientSocket;
	private MagazzinoHandler gestoreMagazzino;
    public ThreadComunicazioneClient(Socket socket, MagazzinoHandler gestoreMagazzino) {
        this.clientSocket = socket;
        this.gestoreMagazzino=gestoreMagazzino;
    }

    @Override
    public void run() {
    	try {
			comunica(clientSocket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //COMANDI:
    //ORDINA 0, codice 1, quantità2
    //GETQ 0, codice 1
    private void comunica(Socket clientSocket) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		String messaggioClient = "";
        boolean esci=false;
		
        while(!esci) {
        	messaggioClient = in.readLine().toUpperCase();
            System.out.println("messaggio ricevuto : " + messaggioClient);
            
            String[] comando = messaggioClient.split(",");
            
        	switch(comando[0]) {
        	case "ORDINA" -> {
        		try {
        			boolean success=gestoreMagazzino.ordinaProdotto(Integer.parseInt(comando[1]), Integer.parseInt(comando[2]));
             		if(success) {
             			out.println("Ordine completato!");
             		} else {
             			out.println("Ordine non riuscito!");
             		}
             	}catch (NumberFormatException e) {
             		out.println("Valori non validi!");
             	}
             }
             case "GETQ" -> {
             		try {
             			int quantita=gestoreMagazzino.getQuantita(Integer.parseInt(comando[1]));
             			out.println("Quantità disponibile: "+quantita);
             		}catch (NumberFormatException e) {
             			out.println("Valori non validi!");
             		} catch (IllegalStateException e) {
             			out.println(e.getMessage());
             		}
             		
             	}
             	case "ESCI" -> {
             		out.println("Arrivederci!");
             		esci=true;
             		System.out.println("Chiusa connessione con "+clientSocket.getInetAddress());
             		clientSocket.close();
             	}
             	default -> out.println("errore comando non riconosciuto");
             }
        }
        }
        
    
    
}
