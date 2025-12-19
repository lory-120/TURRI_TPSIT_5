package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.GestoreDB;
import model.GestionePersonale;
import model.Personale;

public class MainAzienda {

    public static void main(String[] args) {

    	 try {
    		// 1. Inizializzazione della classe di gestione
    		GestionePersonale gp = new GestionePersonale("localhost", "Azienda", "root", "12345678");

    		System.out.println("--- TEST INSERIMENTO ---");
    		// 2. Creazione di un nuovo Java Bean Personale
    		Personale nuovoDip = new Personale();

    		nuovoDip.setMatricola("04458");
    		nuovoDip.setDipartimento("GAMMA");
    		nuovoDip.setNominativo( "Mario Rossi");
    		nuovoDip.setDataNascita(LocalDate.of(1990, 5, 15));
    		nuovoDip.setStipendio( 3500.50 );
    		try {
	    		gp.aggiungiPersonale(nuovoDip);
	    		System.out.println("Inserimenti riuscito per la matricola 04458!");
	    		} catch (SQLException e) {
	    		    System.out.println(e.getMessage());
	    		}
	
	    		System.out.println("\n--- TEST AGGIORNAMENTO ---");
	    		// 3. Modifica dello stipendio e della qualifica sul Bean
	    		nuovoDip.setStipendio(3800.00);
	    		nuovoDip.setQualifica("02");
	    		try {
	    			gp.updatePersonale(nuovoDip);
		    		System.out.println("Aggiornamento riuscito per la matricola 04458!");
	    		} catch(SQLException e) {
	    		   System.out.println(e.getMessage());
	    		}
		
		
	    		System.out.println("\n--- TEST LETTURA (ELENCO COMPLETO) ---");
	    		// 4. Recupero della lista di tutti i dipendenti
	    		ArrayList<Personale> lista = gp.elencaPersonale();
	
	    		if (lista.isEmpty()) {
	    			System.out.println("Nessun personale trovato nel database.");
	    		} else {
		    		for (Personale p : lista) {
		    		// Sfrutta il metodo toString() che abbiamo creato nel Bean
		    			System.out.println(p);
		    		}
	    		}
	
	    		/* System.out.println("\n--- TEST ELIMINAZIONE ---");
	    		if (gp.eliminaPersonale("M123")) {
	    		System.out.println("Cancellazione effettuata con successo.");
	    		}
	    		*/

    		} catch (Exception e) {
    			System.err.println("Si è verificato un errore durante i test:");
    			e.printStackTrace();
    		}
    	
    }
}