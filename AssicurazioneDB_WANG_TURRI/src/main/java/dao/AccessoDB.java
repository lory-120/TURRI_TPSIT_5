package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.MysqlDataSource;

import model.Cliente;
import model.Pagamento;
import model.Polizza;
import utilities.ClienteNonTrovatoException;
import utilities.PolizzaInesistenteException;

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
	
	
	
	public ArrayList<Polizza> viewPolizzeAttive(String idCliente) throws ClienteNonTrovatoException {
		String query = "SELECT *\r\n"
				+ "	FROM `polizze`\r\n"
				+ "	WHERE dt_estinzione IS NULL AND id_cliente = ?";
		if(!checkIfClienteExists(idCliente)) {
			throw new ClienteNonTrovatoException("Il cliente con ID '" + idCliente + "' non è stato trovato.");
		}
		
		ArrayList<Polizza> polizze = new ArrayList<>();
		
		try (PreparedStatement ps = getConnection().prepareStatement(query)) {
			ps.setString(1, idCliente);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Polizza p = new Polizza();
				
				p.setId_polizza(rs.getString("id_polizza"));
				p.setId_cliente(rs.getString("id_cliente"));
				p.setDt_inizio(rs.getDate("dt_inizio"));
				p.setDt_fine(rs.getDate("dt_estinzione"));
				p.setPeriodicita_AST(rs.getString("periodicita").charAt(0));
				p.setPremio_annuo(rs.getInt("premio_annuo"));
				p.setDescrizione(rs.getString("descrizione"));
				
				polizze.add(p);
			}
			
		} catch (SQLException e) {
			System.err.println("Errore nell'esecuzione della query SQL: " + e.getMessage());
		}
		
		return polizze;
	}
	
	public ArrayList<Pagamento> viewPagamentiInPeriodo(String id_cliente, Date dt_inizio, Date dt_fine) {
		String query = "SELECT pag.*\r\n"
				+ "FROM pagamenti pag\r\n"
				+ "INNER JOIN polizze pol ON pag.id_polizza = pol.id_polizza\r\n"
				+ "\r\n"
				+ "WHERE pol.id_cliente = ?\r\n"
				+ "AND pag.dt_pagamento BETWEEN ? AND ?";
		ArrayList<Pagamento> pagamentiFiltrati = new ArrayList<>();
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, id_cliente);
			ps.setDate(2, dt_inizio);
			ps.setDate(3, dt_fine);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Pagamento p = new Pagamento();
				
				p.setId_pagamento(rs.getString("id_pagamento"));
				p.setId_polizza(rs.getString("id_polizza"));
				p.setDt_scadenza(rs.getDate("dt_scadenza"));
				p.setDt_pagamento(rs.getDate("dt_pagamento"));
				p.setImporto(rs.getDouble("importo"));
				p.setNote(rs.getString("note"));
				
				pagamentiFiltrati.add(p);
			}
				
		} catch (SQLException e) {
			System.err.println("Errore nell'esecuzione della query SQL: " + e.getMessage());
		}
			
		return pagamentiFiltrati;
	}
	
	public boolean insertPagamento(Pagamento p) throws PolizzaInesistenteException {
		String query = "INSERT INTO `pagamenti` (`id_pagamento`, `id_polizza`, `dt_scadenza`, `dt_pagamento`, `importo`, `note`) VALUES\r\n"
				+ "(?, ?, ?, ?, ?, ?);";
		
		if(!checkIfPolizzaExists(p.getId_polizza())) {
			throw new PolizzaInesistenteException("La polizza '" + p.getId_polizza() + "' non è stata trovata.");
		}
		
		int result = 0;
		
		try(PreparedStatement ps = getConnection().prepareStatement(query)) {
			//i parametri da inserire nel pagamento:
			ps.setString(1, p.getId_pagamento());
			ps.setString(2, p.getId_polizza());
			ps.setDate(3, p.getDt_scadenza());
			ps.setDate(4, p.getDt_pagamento());
			ps.setDouble(5, p.getImporto());
			ps.setString(6, p.getNote());
			
			result = ps.executeUpdate();
		} catch(SQLException e) {
			System.err.println("Errore nell'esecuzione della query SQL: " + e.getMessage());
		}
		
		return (result > 0) ? true : false;
	}
	
	//metodo di supporto
	public boolean checkIfPolizzaExists(String id_polizza) {
		String query = "SELECT *\r\n"
				+ "FROM polizze\r\n"
				+ "WHERE id_polizza = ?;";
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, id_polizza);
			ResultSet rs = ps.executeQuery();
			
			return rs.next();
		} catch(SQLException e) {
			System.err.println("Errore nell'esecuzione della query SQL: " + e.getMessage());
			return false;
		}
	}
	public boolean checkIfClienteExists(String id_cliente) {
		String query = "SELECT *\r\n"
				+ "FROM clienti\r\n"
				+ "WHERE id_cliente = ?";
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, id_cliente);
			ResultSet rs = ps.executeQuery();
			
			return rs.next();
		} catch(SQLException e) {
			System.err.println("Errore nell'esecuzione della query SQL: " + e.getMessage());
		}
		return false;
	}
	
	public boolean estinguiPolizza(String id_polizza) throws PolizzaInesistenteException {
		String query = "UPDATE polizze\r\n"
				+ "SET dt_estinzione = ?\r\n"
				+ "WHERE id_polizza = ?";
		//imposta l'id polizza, data estinzione a oggi
		
		if(!checkIfPolizzaExists(id_polizza)) {
			throw new PolizzaInesistenteException("La polizza '" + id_polizza + "' non è stata trovata.");
		}
		
		int result = 0;
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now())); //prende la data di oggi dalla libreria time di java e la trasforma nella data in SQL
			ps.setString(2, id_polizza);
			
			result = ps.executeUpdate();
		} catch(SQLException e) {
			System.err.println("Errore nell'esecuzione della query SQL: " + e.getMessage());
		}
		
		return (result > 0) ? true : false;
	}
	
	public ArrayList<Cliente> viewClientiConDuePolizze() {
		String query = "SELECT p.id_cliente, c.nome, c.cognome, COUNT(*) AS n_polizze\r\n"
				+ "FROM clienti c JOIN polizze p ON c.id_cliente = p.id_cliente\r\n"
				+ "GROUP BY p.id_cliente\r\n"
				+ "HAVING n_polizze >= 2;";
		
		ArrayList<Cliente> clientiConDuePolizze = new ArrayList<>();
		
		try(PreparedStatement ps = getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery()) {
			
			while(rs.next()) {
				Cliente c = new Cliente();
				c.setId_cliente(rs.getString("id_cliente"));
				c.setCognome(rs.getString("cognome"));
				c.setNome(rs.getString("nome"));
				c.setN_polizze(rs.getInt("n_polizze"));
				clientiConDuePolizze.add(c);
			}
				
		} catch (SQLException e) {
			System.err.println("Errore nell'esecuzione della query SQL: " + e.getMessage());
		}
			
		return clientiConDuePolizze;
		
	}
	public double calcolaTotImporti(String id_cliente, Date dt_inizio, Date dt_fine) {
		String query = "SELECT ROUND(SUM(pag.importo),2) as importo_tot\r\n"
				+ "FROM pagamenti pag\r\n"
				+ "JOIN polizze pol ON pag.id_polizza = pol.id_polizza\r\n"
				+ "WHERE pol.id_cliente = ? AND pag.dt_pagamento BETWEEN \r\n"
				+ "? AND ?;";
		
		double importoTot = -1;
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.setString(1, id_cliente);
			ps.setDate(2, dt_inizio);
			ps.setDate(3, dt_fine);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				importoTot = rs.getDouble("importo_tot");
			}
			
		} catch(SQLException e) {
			System.err.println("Errore nell'esecuzione della query SQL: " + e.getMessage());
		}
		
		return importoTot;
		
	}
	
}
