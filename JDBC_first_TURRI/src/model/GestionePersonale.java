package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.GestoreDB;

public class GestionePersonale {

	private Connection c;
	private Statement s = null;
	private ResultSet result = null;
	
	public GestionePersonale(String server, String database, String user, String password) throws SQLException {
		this.c = new GestoreDB(server, database, user, password).getConnection();
		s = c.createStatement();
	}
	
	//aggiungi personale
	public boolean addPersonale(Personale p) throws IllegalStateException, SQLException {
		String IDDipartimento = null;
		try {
			IDDipartimento = cercaIDDipartimento(p.getDipartimento());
			String data = p.getDataNascita().toString();
			String queryInsert = "INSERT INTO personale(matricola, id_dipartimento, nominativo, data_nascita, qualifica, stipendio)"
					+ "VALUES ('" + p.getMatricola() + "',"
					+ "'" + IDDipartimento + "',"
					+ "'" + p.getNominativo() + "',"
					+ "'" + p.getDataNascita() + "',"
					+ "'" + p.getQualifica() + "',"
					+ "'" + p.getStipendio() + "')";
			if(s.executeUpdate(queryInsert) == 0) {
				return false;
			}
		} catch(IllegalStateException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		}
		
		return true;
	}
	public boolean addPersonalePrep(Personale p) throws IllegalStateException, SQLException {
		String IDDipartimento = null;
		try {
			IDDipartimento = cercaIDDipartimento(p.getDipartimento());
			String data = p.getDataNascita().toString();
			String queryInsert = "INSERT INTO personale(matricola, id_dipartimento, nominativo, data_nascita, qualifica, stipendio)"
					+ "VALUES (?, ?, ?, ?, ?, ?);";
			
			PreparedStatement ps = c.prepareStatement(queryInsert);
			ps.setString(1, p.getMatricola());
			ps.setString(2, IDDipartimento);
			ps.setString(3, p.getNominativo());
			ps.setDate(4, Date.valueOf(p.getDataNascita()));
			ps.setString(5, p.getQualifica());
			ps.setDouble(6, p.getStipendio());
			
			if(s.executeUpdate(queryInsert) == 0) {
				return false;
			}
		} catch(IllegalStateException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		}
		
		return true;
	}
	
	public String cercaIDDipartimento(String dipSrc) throws SQLException {
		s = c.createStatement();
		String query = "SELECT id_dipartimento FROM Dipartimenti WHERE nome_dipartimento = '" + dipSrc + "';";
		result = s.executeQuery(query);
		String id = result.getString(1); //restituisce la colonna 1 del risultato
		if(id == null) {
			throw new IllegalStateException("Nessun ID trovato. Riprova.");
		} else {
			return id;
		}
	}
	
	
	//stampa membri personale
	public String getAllMembri() throws SQLException {
		String query = "SELECT * FROM personale WHERE 1;";
		
		PreparedStatement ps = c.prepareStatement(query);
		if(s.executeUpdate(query) == 0) {
			throw new SQLException("Errore nella query MySQL.");
		}
		

	}
	
	//modifica un membro del personale
	//elimina un membro del personale
	
}
