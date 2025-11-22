package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {

    private static Connection connection;

    public static Connection getConnection() throws Exception {
        Properties props = ConfigDB.loadProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String pass = props.getProperty("db.password");

        return DriverManager.getConnection(url, user, pass);
    }
}
