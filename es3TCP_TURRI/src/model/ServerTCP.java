package model;

import java.io.*;
import java.net.*;

public class ServerTCP {
    private static final int PORTA = 8888;
    private Tavolo[] tavoli = new Tavolo[100];
    private int numeroTavoli = 0;

    public void avvia() {
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Server TCP avviato sulla porta " + PORTA);

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> gestisciConnessione(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void gestisciConnessione(Socket socket) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String richiesta = in.readLine();
            String risposta = elaboraRichiesta(richiesta);
            out.println(risposta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String elaboraRichiesta(String richiesta) {
        String[] parti = richiesta.split(";");
        String comando = parti[0];

        switch (comando.toLowerCase()) {
            case "aggiungi":
                int posti = Integer.parseInt(parti[1]);
                Tavolo nuovo = new Tavolo(numeroTavoli + 1, posti);
                tavoli[numeroTavoli] = nuovo;
                numeroTavoli++;
                return "Tavolo aggiunto: " + nuovo;

            case "prenota":
                int persone = Integer.parseInt(parti[1]);
                String nome = parti[2];

                for (int i = 0; i < numeroTavoli; i++) {
                    Tavolo t = tavoli[i];
                    if (!t.occupato && t.posti == persone) {
                        t.occupato = true;
                        t.nomePrenotazione = nome;
                        return "Prenotazione effettuata: " + t;
                    }
                }

                Tavolo migliorTavolo = null;
                for (int i = 0; i < numeroTavoli; i++) {
                    Tavolo t = tavoli[i];
                    if (!t.occupato && t.posti > persone) {
                        if (migliorTavolo == null || t.posti < migliorTavolo.posti) {
                            migliorTavolo = t;
                        }
                    }
                }
                if (migliorTavolo != null) {
                    migliorTavolo.occupato = true;
                    migliorTavolo.nomePrenotazione = nome;
                    return "Prenotazione effettuata (posti maggiori): " + migliorTavolo;
                }

                return "Nessun tavolo disponibile per " + persone + " persone.";

            case "stato":
                StringBuilder sb = new StringBuilder("=== Stato dei Tavoli Aggiunti ===\n");
                for (int i = 0; i < numeroTavoli; i++) {
                    sb.append("Tavolo ").append(i + 1).append(": ").append(tavoli[i].toString()).append("\n");
                }
                if (numeroTavoli == 0) {
                    sb.append("Nessun tavolo aggiunto.\n");
                }
                return sb.toString();

            case "libera":
                int numero = Integer.parseInt(parti[1]);
                for (int i = 0; i < numeroTavoli; i++) {
                    Tavolo t = tavoli[i];
                    if (t.numero == numero) {
                        t.occupato = false;
                        t.nomePrenotazione = "";
                        return "Tavolo " + numero + " liberato.";
                    }
                }
                return "Tavolo non trovato.";

            default:
                return "Comando non riconosciuto.";
        }
    }
}