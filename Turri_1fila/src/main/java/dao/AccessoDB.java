package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.MysqlDataSource;

import model.Docente;
import model.Servizio;

public class AccessoDB {

private MysqlDataSource ds = new MysqlDataSource();
	
	public AccessoDB(String serverName, String db, String user, String psw) {
		ds.setServerName(serverName);
		ds.setDatabaseName(db);
		ds.setUser(user);
		ds.setPassword(psw);
	}
	
	private Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	
	/*SELECT d.cod_fiscale, d.cognome, d.nome, s.dt_inizio
	FROM docenti d
	INNER JOIN servizi s ON d.cod_fiscale = s.cod_fiscale
	WHERE s.dt_fine IS NULL;*/
	public ArrayList<Docente> getDocentiInServizio(String cod_meccanografico) { //con dt_fine NON avvvalorata, NULL
		String query = "SELECT d.cod_fiscale, d.cognome, d.nome, s.dt_inizio\r\n"
				+ "FROM docenti d\r\n"
				+ "INNER JOIN servizi s ON d.cod_fiscale = s.cod_fiscale\r\n"
				+ "WHERE s.cod_meccanografico = ? AND s.dt_fine IS NULL";
		ArrayList<Docente> docentiInServizio = new ArrayList<>();
		
		try(PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setString(1, cod_meccanografico);
			
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					Docente d = new Docente();
					d.setCod_fiscale(rs.getString("d.cod_fiscale"));
					d.setCognome(rs.getString("d.cognome"));
					d.setNome(rs.getString("d.nome"));
					d.setDt_inizio(rs.getDate("s.dt_inizio"));
					docentiInServizio.add(d);
				}
			}
			
		} catch (SQLException e) {
			System.err.println("Errore nell'esecuzione della query SQL: " + e.getMessage());
			e.printStackTrace();
		}
		
		return docentiInServizio;
		
	}
	
	/*SELECT cod_fiscale, dt_inizio, dt_fine
	FROM servizi
	WHERE cod_fiscale = ? AND dt_fine < ? AND dt_inizio > ?;*/
	public ArrayList<Servizio> getServiziPrestatiDocente(String cod_fiscale, Date dt_inizio, Date dt_fine) {
		String query = "SELECT cod_fiscale, dt_inizio, dt_fine\r\n"
				+ "	FROM servizi\r\n"
				+ "	WHERE cod_fiscale = ? AND dt_fine < ? AND dt_inizio > ?";
		ArrayList<Servizio> serviziPrestati = new ArrayList<>();
		
		try(PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setString(1, cod_fiscale);
			ps.setDate(2, dt_inizio);
			ps.setDate(3, dt_fine);
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					Servizio s = new Servizio();
					s.setCod_fiscale(rs.getString("cod_fiscale"));
					s.setDt_inizio(rs.getDate("dt_inizio"));
					s.setDt_fine(rs.getDate("dt_fine"));
					serviziPrestati.add(s);
				}
			}
		} catch(SQLException e) {
			System.err.println("Errore nell'esecuzione della query SQL: " + e.getMessage());
			e.printStackTrace();
		}
		
		return serviziPrestati;
	}
	
	/*INSERT INTO servizi (cod_fiscale, cod_meccanografico, dt_inizio, dt_fine, ruolo, num_ore_settimanali, note) VALUES
	(?, ?, ?, ?, ?, ?, ?);*/
	public boolean inserisciServizio(Servizio s) {
		String query = "INSERT INTO servizi (cod_fiscale, cod_meccanografico, dt_inizio, dt_fine, ruolo, num_ore_settimanali, note) VALUES\r\n"
				+ "(?, ?, ?, ?, ?, ?, ?)";
		
		int result = 0;
		
		try(PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setString(1, s.getCod_fiscale());
			ps.setString(2, s.getCod_meccanografico());
			ps.setDate(3, s.getDt_inizio());
			ps.setDate(4, s.getDt_fine());
			ps.setString(5, s.getRuolo());
			ps.setInt(6, s.getNum_ore_settimanali());
			ps.setString(7, s.getNote());

			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Errore nell'esecuzione della query SQL: " + e.getMessage());
			e.printStackTrace();
		}
		
		return (result > 0) ? true : false;
	}
	
	/*UPDATE servizi
	SET dt_fine = ?
	WHERE cod_fiscale = ? AND cod_meccanografico = ?*/
	public boolean setDtFine(Date dt_fine, String cod_fiscale, String cod_meccanografico) {
		String query = "UPDATE servizi\r\n"
				+ "SET dt_fine = ?\r\n"
				+ "WHERE cod_fiscale = ? AND cod_meccanografico = ?";
		
		int result = 0;
		
		try(PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setDate(1, dt_fine);
			ps.setString(2, cod_fiscale);
			ps.setString(3, cod_meccanografico);

			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Errore nell'esecuzione della query SQL: " + e.getMessage());
			e.printStackTrace();
		}
		
		return (result > 0) ? true : false;
		
	}
	
	/*SELECT COUNT(*) AS n_docenti
	FROM servizi
	WHERE cod_meccanografico = ? AND dt_inizio > ? AND dt_fine < ?*/
	public int countDocentiServizioInRange(String cod_meccanografico, Date dt_inizio, Date dt_fine) {
		String query = "SELECT COUNT(*) AS n_docenti\r\n"
				+ "	FROM servizi\r\n"
				+ "	WHERE cod_meccanografico = ? AND dt_inizio > ? AND dt_fine < ?";
		
		int result = -1;
		
		try(PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setString(1, cod_meccanografico);
			ps.setDate(2, dt_inizio);
			ps.setDate(3, dt_fine);

			try(ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					result = rs.getInt("n_docenti");
				}
			}
		} catch (SQLException e) {
			System.err.println("Errore nell'esecuzione della query SQL: " + e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
}
