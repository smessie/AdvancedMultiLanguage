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
		PreparedStatement statement = connection.prepareStatement("select language from " + Settings.table + " where uuid='" + uuid + "';");
        ResultSet result = statement.executeQuery();
       
        if(result.next()){
        	Statement stmt = connection.createStatement();
    		String sqlCreate = "UPDATE `" + Settings.table + "` SET `language` = '" + language + "' WHERE `uuid` = '" + uuid + "'";
    		stmt.execute(sqlCreate);
    		stmt.close();
        } else {
        	Statement stmt = connection.createStatement();
        	String sqlCreate = "insert into " + Settings.table + " (uuid, language, IP) values ('" + uuid + "', '" + language + "', '" + ip + "');";
        	stmt.execute(sqlCreate);
        	stmt.close();
        }
	}
	
	public static void setLanguageMysql(String uuid, String language) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select language from " + Settings.table + " where uuid='" + uuid + "';");
        ResultSet result = statement.executeQuery();
       
        if(result.next()){
        	Statement stmt = connection.createStatement();
    		String sqlCreate = "UPDATE `" + Settings.table + "` SET `language` = '" + language + "' WHERE `uuid` = '" + uuid + "'";
    		stmt.execute(sqlCreate);
    		stmt.close();
        } else {
        	Statement stmt = connection.createStatement();
        	String sqlCreate = "insert into " + Settings.table + " (uuid, language) values ('" + uuid + "', '" + language + "');";
        	stmt.execute(sqlCreate);
        	stmt.close();
        }
	}
	
	public static String getLanguage(String uuid) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select language from " + Settings.table + " where uuid='" + uuid + "';");
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
