package me.smessie.MultiLanguage.main;

import java.io.Closeable;
import java.sql.*;

public class MySQL implements Closeable {

    private Connection connection;


    public MySQL(String host, int port, String userName, String password, String db) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?autoReconnect=true&user=" + userName + "&password=" + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable() throws SQLException {
        if (Settings.createMysqlTable) {
            String table = Settings.table;
            String sqlCreate = "CREATE TABLE IF NOT EXISTS " + table + " (uuid varchar(36) NOT NULL ,language text NOT NULL,IP text DEFAULT NULL, PRIMARY KEY (uuid));";

            Statement stmt = connection.createStatement();
            stmt.execute(sqlCreate);
        }

    }

    public void setLanguageMysql(String uuid, String language, String ip) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO " + Settings.table + " (uuid, language, IP) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE language = VALUES(language), IP = VALUES(IP)");
        statement.setString(1, uuid);
        statement.setString(2, language);
        statement.setString(3, ip);
        statement.executeUpdate();
    }

    public void setLanguageMysql(String uuid, String language) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO " + Settings.table + " (uuid, language) VALUES (?, ?) ON DUPLICATE KEY UPDATE language = VALUES(language)");
        statement.setString(1, uuid);
        statement.setString(2, language);
        statement.executeUpdate();
    }

    public String getLanguage(String uuid) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT language FROM " + Settings.table + " WHERE uuid= ?;");
        statement.setString(1, uuid);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            String language = result.getString("language");
            result.close();
            statement.close();
            return language;
        } else {
            return null;
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
