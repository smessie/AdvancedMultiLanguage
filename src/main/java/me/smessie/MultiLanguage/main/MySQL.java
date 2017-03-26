package me.smessie.MultiLanguage.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
	
private static Connection connection;
	
	
	public MySQL(String host, int port, String userName, String password, String db) {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?autoReconnect=true&user=" + userName + "&password=" + password);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public static void createTable() throws SQLException {
		if(Settings.createMysqlTable) {
			String table = Settings.table;
		    String sqlCreate = "CREATE TABLE IF NOT EXISTS " + table + " (uuid varchar(36) NOT NULL ,language text NOT NULL,IP text DEFAULT NULL, PRIMARY KEY (uuid));";
	
		    Statement stmt = connection.createStatement();
		    stmt.execute(sqlCreate);
		}
	    
	}
	
	public static void setLanguageMysql(String uuid, String language, String ip) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO " + Settings.table + " (uuid, language, IP) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE language = VALUES(language), IP = VALUES(IP)");
		statement.setString(1, uuid);
		statement.setString(2, language);
		statement.setString(3, ip);
        statement.executeUpdate();
	}
	
	public static void setLanguageMysql(String uuid, String language) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO " + Settings.table + " (uuid, language) VALUES (?, ?) ON DUPLICATE KEY UPDATE language = VALUES(language)");
		statement.setString(1, uuid);
		statement.setString(2, language);
		statement.executeUpdate();
	}
	
	public static String getLanguage(String uuid) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select language from " + Settings.table + " where uuid= ?;");
		statement.setString(1, uuid);
        ResultSet result = statement.executeQuery();
       
        if(result.next()){
        	String language = result.getString("language");
        	result.close();
        	statement.close();
        	return language;
        } else {
        	return null;
        }
	}
	public static void disable() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
