package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
			private GestioneRistorante gestRist;
			private ServerSocket serverSocket;
			
			
			public Server(int port) {
				//inizializzazione server su porta
				try {
					serverSocket=new ServerSocket(port);
					System.out.println("Servizio avviato su porta: "+port);
				} catch (IOException e) {
					System.out.println("Impossibile avviare servizio");
					e.printStackTrace();
				}
				gestRist=new GestioneRistorante();
			} 
			
			public void attendi() {
				while(true) {//attendo all infinito
					try {
						Socket clientSocket=serverSocket.accept();//creo un anuova connessione spostandosi su un altra porta
						System.out.println("Servizio connesso con client:"+clientSocket.getInetAddress());
						ClientHandler c=new ClientHandler(clientSocket,gestRist);// avvio il thread per gestire il client con cui abbiamo creato la connessione
						c.start();
					} catch (IOException e) {
						e.printStackTrace();
					}			
				}
			}
}
