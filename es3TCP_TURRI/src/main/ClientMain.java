package main;

import model.ClientTCP;
import presentation.Tastiera;

public class ClientMain {
	
    public static void main(String[] args) {
    	
        ClientTCP client = new ClientTCP();
        boolean continua = true;

        while (continua) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Aggiungi tavolo");
            System.out.println("2. Prenota tavolo");
            System.out.println("3. Visualizza stato tavoli");
            System.out.println("4. Libera tavolo");
            System.out.println("5. Esci");

            int scelta = Tastiera.leggiInt("Scegli un'opzione: ");

            String risposta = "";
            switch (scelta) {
                case 1:
                    int posti = Tastiera.leggiInt("Inserisci numero posti: ");
                    risposta = client.inviaRichiesta("aggiungi;" + posti);
                    break;

                case 2:
                    int persone = Tastiera.leggiInt("Numero persone: ");
                    String nome = Tastiera.leggiString("Nome prenotazione: ");
                    risposta = client.inviaRichiesta("prenota;" + persone + ";" + nome);
                    break;

                case 3:
                    risposta = client.inviaRichiesta("stato;");

                    System.out.println("\n Stato dei tavoli:");
                    String[] righe = risposta.split("\n");
                    for (String riga : righe) {
                        System.out.println("-" + riga);
                    }
                    System.out.println("=== Fine stato ===\n");
                    continue;

                case 4:
                    int numero = Tastiera.leggiInt("Numero tavolo da liberare: ");
                    risposta = client.inviaRichiesta("libera;" + numero);
                    break;

                case 5:
                    continua = false;
                    continue;

                default:
                    risposta = "Scelta non valida.";
            }

            System.out.println("Risposta server: \n" + risposta);
        }
        
    }
}