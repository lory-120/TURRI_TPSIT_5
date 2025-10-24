package z_chatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//CUORE della nostra chat
public class ClientHandler extends Thread{
	private ListaClient lista;
	private Socket clientSocket;
	public ClientHandler(ListaClient lista, Socket clientSocket) {
		this.lista=lista;
		this.clientSocket=clientSocket;
	}
	
	
	@Override
	public void run() {
		try {
			comunica(clientSocket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 //COMANDI :
    // AGGIUNGI_CLIENT 0, <nickname> 1
	// LISTA_CLIENT
    // INVIA_MEX 0, <nicknameClient> 1 <mex> 2
	private void comunica(Socket clientSocket) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        try {
            
            String messaggioClient = "";
            while(!messaggioClient.equalsIgnoreCase("esci")){
                messaggioClient = in.readLine().toUpperCase();
                if(messaggioClient== null) break;
                System.out.println("messaggio ricevuto : " + messaggioClient);
                String[] comando = messaggioClient.split(",");
                if(comando[0].equals("AGGIUNGI_CLIENT")){
                	if (comando.length != 2) {
                        out.println("ERRORE: comando AGGIUNGI_CLIENT incompleto. Uso corretto: AGGIUNGI_CLIENT,nickname");
                        continue; // passa al ciclo successivo senza lanciare eccezioni
                    }
                    String nickname = comando[1];
                    try {
                    	lista.aggiungiClient(nickname, clientSocket);
                    	out.println("client  aggiunto correttamente");
                    }catch (NicknameException e) {
                    	 out.println(e.getMessage());
                    }
                   
                }else if(comando[0].equals("LISTA_CLIENT")){
                	if(lista.getListaNicknames().equals("")) out.println("Nessun client connesso");
                   out.println("Risposta : "+lista.getListaNicknames());
                }else if(comando[0].equals("INVIA_MEX")){
                	if (comando.length != 3) {
                        out.println("ERRORE: comando INVIA_MEX incompleto. Uso corretto: INVIA_MEX,nickname,messaggio");
                        continue; // passa al ciclo successivo senza lanciare eccezioni
                    }
                	String nicknameChat=comando[1]; //ATTENZIONE ad arrayOutOfBound
                	String mex=comando[2];
                	try {
                		//fare una linkedlist 
                		Socket altroClient=lista.get(nicknameChat);
                    	PrintWriter outAltroClient=new PrintWriter(altroClient.getOutputStream(), true);
                    	outAltroClient.println("Inviato da "+lista.trovaNicknamePerSocket(clientSocket)+" "+mex);
                    	out.println("Risposta : avviata chat!");
                	} catch(NicknameException e) {
                		out.println(e.getMessage());
                	}
                	
                }else if(comando[0].equals("ESCI")){
                	//togliere client
//                	lista.rimuoviClient(messaggioClient);
                	out.println("Arrivederci!");
                } else {
                	out.println("errore comando non riconosciuto");
                }
            }

            out.println("connessione terminata");

            
        } catch (IOException e) {
        	//togliere client
            throw new RuntimeException(e);
        }
    }
	
}
