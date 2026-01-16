package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.cj.jdbc.MysqlDataSource;

import model.Cliente;
import model.Pagamento;
import model.Polizza;

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
	
	
	
	public ArrayList<Polizza> getPolizzeAttive() {
		String query = "SELECT *\r\n"
				+ "	FROM `polizze`\r\n"
				+ "	WHERE dt_estinzione IS NULL;";
	}
	
	public ArrayList<Pagamento> viewPagamentiInPeriodo(String id_cliente, LocalDate dt_inizio, LocalDate dt_fine) {
		String query = "SELECT pag.*\r\n"
				+ "FROM pagamenti pag\r\n"
				+ "INNER JOIN polizze pol ON pag.id_polizza = pol.id_polizza\r\n"
				+ "\r\n"
				+ "WHERE pol.id_cliente = \"?\"\r\n"
				+ "AND pag.dt_pagamento BETWEEN \"?\" AND \"?\";";
		//id cliente, data inizio, data fine
	}
	
	public boolean insertPagamento() {
		String query = "INSERT INTO `pagamenti` (`id_pagamento`, `id_polizza`, `dt_scadenza`, `dt_pagamento`, `importo`, `note`) VALUES\r\n"
				+ "(?, ?, ?, ?, ?, ?);";
		//i parametri da inserire nel pagamento
	}
	public boolean checkIfPolizzaExists(String id_polizza) {
		
	}
	
	public boolean estinguiPolizza(String id_polizza) {
		String query = "UPDATE polizze\r\n"
				+ "SET dt_estinzione = ?\r\n"
				+ "WHERE id_polizza = ?";
		//imposta l'id polizza, data estinzione a oggi
	}
	
	public ArrayList<Cliente> viewClientiConDuePolizze() {
		String query = "SELECT p.id_cliente, c.nome, c.cognome, COUNT(*) AS n_polizze\r\n"
				+ "FROM clienti c JOIN polizze p ON c.id_cliente = p.id_cliente\r\n"
				+ "GROUP BY p.id_cliente\r\n"
				+ "HAVING n_polizze >= 2;";
	}
	public double calcolaTotImporti(String id_cliente, LocalDate dt_inizio, LocalDate dt_fine) {
		String query = "SELECT ROUND(SUM(pag.importo),2) as importo_tot\r\n"
				+ "FROM pagamenti pag\r\n"
				+ "JOIN polizze pol ON pag.id_polizza = pol.id_polizza\r\n"
				+ "WHERE pol.id_cliente = '354000' AND pag.dt_pagamento BETWEEN \r\n"
				+ "'2022-01-01' AND '2022-09-09';";
	}
	
}
