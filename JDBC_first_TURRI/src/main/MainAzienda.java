package main;

import dao.GestoreDB;

public class MainAzienda {

	public static void main(String[] args) {
		
		//1.Impostazione dei Parametri:
		String server = "localhost";
		String database = "azienda";
		String utente = "root";
		String password = "errata";
		
	    //2.Apertura della Connessione:
		GestoreDB gestore = new GestoreDB(server, database, utente, password);
		
	    //3.Creazione dell'Istruzione (Statement):L'oggetto Statement è creato dalla Connection e viene usato
		//per inviare istruzioni SQL al database.
	    //4.Esecuzione della Query
	    //5.Lettura ed elaborazione del Risultato
	    //6.Chiusura connessione
		
		
		
	}

}
