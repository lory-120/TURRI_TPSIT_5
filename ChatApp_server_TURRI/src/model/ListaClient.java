package z_chatServer;

import java.net.Socket;
import java.util.HashMap;


public class ListaClient {
	private static HashMap<String, Socket> clientConnessi;
	
	public ListaClient() {
		clientConnessi=new HashMap<>();
	}
	
	public void aggiungiClient(String nickname, Socket clientSocket) {
		 if (nickname == null || nickname.isBlank()) {
			 throw new NicknameException("Nickname vuoto. Riprova!");
	     }
	     if (clientSocket == null || clientSocket.isClosed()) {
	    	 throw new IllegalArgumentException("Socket non valido o chiuso.");
	     }
		synchronized (clientConnessi) {
            if (clientConnessi.containsKey(nickname)) {
                throw new NicknameException("Nickname già in uso: " + nickname);
            }
            clientConnessi.put(nickname, clientSocket);
        }
	}
	// (opzionale) utilità comode
    public boolean esiste(String nickname) {
        synchronized (clientConnessi) {
            return clientConnessi.containsKey(nickname);
        }
    }

    public synchronized Socket get(String nickname) {
    	Socket socket=null;
    	socket=clientConnessi.get(nickname);
        if(socket==null) {
        	throw new NicknameException("Client non trovato");
        }
        return socket;
    }

    public void rimuoviClient(String nickname) {
        synchronized (clientConnessi) {
            clientConnessi.remove(nickname);
        }
    }
    public String trovaNicknamePerSocket(Socket socket) {
        if (socket == null) throw new IllegalArgumentException("Socket nullo.");
        synchronized (clientConnessi) {
            for (var entry : clientConnessi.entrySet()) {
                if (socket.equals(entry.getValue())) {
                    return entry.getKey();
                }
            }
        }
        throw new IllegalStateException("Nessun nickname associato a questo socket.");
    }
    public String getListaNicknames() {
    	synchronized (clientConnessi) {
            String result = "";
            for (String nick : clientConnessi.keySet()) {
                result += " |*| ";
                result += nick;
            }
            return result; // es: "alice |*| bob |*| carlo"
        }
    }
}
