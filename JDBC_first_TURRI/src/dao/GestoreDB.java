package dao;

import java.sql.*;

import com.mysql.cj.jdbc.MysqlDataSource;

public class GestoreDB {
	
	private MysqlDataSource ds = new MysqlDataSource();
	
	public GestoreDB(String server, String database, String user, String password) {
		ds.setServerName(server);
		ds.setDatabaseName(database);
		ds.setUser(user);
		ds.setPassword(password);
	}
	
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
}