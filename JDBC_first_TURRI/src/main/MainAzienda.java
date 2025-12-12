package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import dao.GestoreDB;

public class MainAzienda {

    public static void main(String[] args) {

        // 1. Impostazione dei Parametri:
        String server = "localhost";
        String database = "azienda";
        String utente = "root";
        String password = "";

        try {
            // 2. Apertura della Connessione:
            GestoreDB gestore = new GestoreDB(server, database, utente, password);
            Connection conn = gestore.getConnection();

            // 3. Creazione dell'Istruzione (Statement):
            Statement stat = conn.createStatement();

            // 4. Esecuzione della Query:
            String query = "SELECT * FROM personale";
            ResultSet rs = stat.executeQuery(query);

            // 5. Lettura ed elaborazione del Risultato:
            while (rs.next()) {
                System.out.println(
                    rs.getString("matricola") + " - " +
                    rs.getString("nominativo") + " - " +
                    rs.getString("qualifica")
                );
            }

            // 6. Chiusura connessione:
            rs.close();
            stat.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}