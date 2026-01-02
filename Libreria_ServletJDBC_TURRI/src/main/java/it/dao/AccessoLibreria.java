package it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.MysqlDataSource;

import it.model.Libro;

public class AccessoLibreria {

	private MysqlDataSource ds = new MysqlDataSource();
	
	public AccessoLibreria(String serverName, String db, String user, String psw) {
		ds.setServerName(serverName);
		ds.setDatabaseName(db);
		ds.setUser(user);
		ds.setPassword(psw);
	}
	
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	/*
	 * 1- ottenere l'elenco dei libri stampati, con ISBN, titolo, autore, editore, in
	 * un determinato periodo di tempo determinato dall'utente
	 * 
	 * 2- eliminare un libro dal DB con l'ISBN
	 * 
	 * 3- inserire un libro nel DB
	 * */
	
	
	/*
	SELECT l.*
	FROM libri l
	INNER JOIN autori a ON l.id_autore = a.id_autore
	INNER JOIN editori e ON l.id_editore = e.id_editore
	WHERE anno BETWEEN ? AND ?;
	*/
	public ArrayList<Libro> getLibriInPeriodo(int dataInizio, int dataFine) {
		ArrayList<Libro> libri = new ArrayList<Libro>();
		String query = "SELECT l.*\r\n"
				+ "	FROM libri l\r\n"
				+ "	INNER JOIN autori a ON l.id_autore = a.id_autore\r\n"
				+ "	INNER JOIN editori e ON l.id_editore = e.id_editore\r\n"
				+ "	WHERE anno BETWEEN ? AND ?;";
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setInt(1, dataInizio);
			ps.setInt(2, dataFine);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) { //itera per tutte le righe della ResultSet
				Libro l = new Libro();
				l.setIsbn(rs.getString("isbn"));
				l.setTitolo(rs.getString("titolo"));
				l.setLingua(rs.getString("lingua"));
				l.setAnno(rs.getInt("anno"));
				l.setCosto(rs.getDouble("costo"));
				l.setIdAutore(rs.getString("id_autore"));
				l.setIdEditore(rs.getString("id_editore"));
				libri.add(l);
			}
		} catch (SQLException e) {
			System.err.println("Errore con la query SQL: " + e.getMessage());
		}
		
		return libri;
	}
	
	/*
	DELETE FROM libri WHERE isbn = "0000000000000";
	*/
	//restituisce true se l'ha eliminato, false altrimenti
	public boolean eliminaConISBN(String isbn) {
		String query = "DELETE FROM libri WHERE isbn = ?";
		int result = 0; //le righe generate dall'esecuzione della query SQL
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, isbn);
			result = ps.executeUpdate(); //se la query è andata a buon fine, restituirà qualcosa > 0
		} catch (SQLException e) {
			System.err.println("Errore con la query SQL: " + e.getMessage());
		}
		
		return (result > 0) ? true : false;
	}
	
	/*
	INSERT INTO libri (ISBN, titolo, lingua, anno, costo, id_autore, id_editore) VALUES
	("1234567891234", "TEST", "test", 2025, 23.99, "00001", "00001");
	 */
	public boolean inserisciLibro(Libro l) {
		String query = "INSERT INTO libri (ISBN, titolo, lingua, anno, costo, id_autore, id_editore) VALUES\r\n"
				+ "	(?, ?, ?, ?, ?, ?, ?);";
		int result = 0;
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, l.getIsbn());
			ps.setString(2, l.getTitolo());
			ps.setString(3, l.getLingua());
			ps.setInt(4, l.getAnno());
			ps.setDouble(5, l.getCosto());
			ps.setString(6, l.getIdAutore());
			ps.setString(7, l.getIdEditore());
			result = ps.executeUpdate(); //se la query è andata a buon fine, restituirà qualcosa > 0
		} catch (SQLException e) {
			System.err.println("Errore con la query SQL: " + e.getMessage());
		}
		
		return (result > 0) ? true : false;
	}

	/*
	SELECT nome, cognome
	FROM autori
	WHERE id_autore = ?;
	*/
	public String getAutoreByID(String idAutore) { //per avere nome e cognome di un autore dal suo ID
		String query = "SELECT nome, cognome\r\n"
				+ "	FROM autori\r\n"
				+ "	WHERE id_autore = ?;";
		String autore = null;
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, idAutore);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
                autore = rs.getString("nome") + " " + rs.getString("cognome");
            }
		} catch (SQLException e) {
			System.err.println("Errore con la query SQL: " + e.getMessage());
		}
		
		return autore;
	}

	/*
	SELECT ragione_sociale
	FROM editori
	WHERE id_editore = ?; 
	*/
	public String getEditoreByID(String idEditore) { //per avere il nome della ragione sociale dal suo ID
		String query = "SELECT ragione_sociale\r\n"
				+ "	FROM editori\r\n"
				+ "	WHERE id_editore = ?;";
		String editore = null;
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, idEditore);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				editore = rs.getString("ragione_sociale");
            }
		} catch (SQLException e) {
			System.err.println("Errore con la query SQL: " + e.getMessage());
		}
		
		return editore;
	}
	
}
