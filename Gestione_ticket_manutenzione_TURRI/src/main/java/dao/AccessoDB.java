package dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

import model.Ticket;

public class AccessoDB {

	/*private int ID;
	private Urgenza urgenza;
	private String richiesta;
	private boolean isDone;*/
	private MysqlDataSource ds = new MysqlDataSource();
	
	private String address = "localhost";
	private String db = "tickets_manutenzione";
	private String user = "root";
	private String pass = "";
	
	public AccessoDB() {
		ds.setServerName(address);
		ds.setDatabaseName(db);
		ds.setUser(user);
		ds.setPassword(pass);
	}
	
	private Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	
	protected boolean aggiungiTicketDB(Ticket t) {
		String qeury = ""
	}
	
}
