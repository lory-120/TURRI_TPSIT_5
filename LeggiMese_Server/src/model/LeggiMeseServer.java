package model;

import java.io.IOException;
import java.net.*;

public class LeggiMeseServer {
    private ServerSocket serverSocket;
    private int port;
    
    //creo server socket con costruttore
    public LeggiMeseServer(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Servizio avviato. Porta: " + port);
        } catch (IOException e) {
            System.out.println("Impossibile avviare il servizio.");
        }    
    }
    
    public void avvia() {
        if (serverSocket == null) return;

        System.out.println("Server in ascolto...");
        
        while(true) { 
            Socket clientSocket = null;
            try {
            	
                clientSocket = serverSocket.accept();
                System.out.println("Nuova connessione da: " + clientSocket.getInetAddress());
                new ClientCommunicationThread(clientSocket).start();
                
            } catch (IOException e) {
                System.err.println("Errore nell'accettazione della connessione: " + e.getMessage());
            }
        }
    }
}