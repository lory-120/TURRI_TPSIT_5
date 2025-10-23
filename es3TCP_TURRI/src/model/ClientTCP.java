package model;

import java.io.*;
import java.net.*;

public class ClientTCP {
    private static final int PORTA = 8888;

    public String inviaRichiesta(String richiesta) {
        try (Socket socket = new Socket("localhost", PORTA);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println(richiesta);
            
            StringBuilder risposta = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                risposta.append(line).append("\n");
            }
            return risposta.toString().trim();
        } catch (IOException e) {
            e.printStackTrace();
            return "Errore di comunicazione col server.";
        }
    }
}