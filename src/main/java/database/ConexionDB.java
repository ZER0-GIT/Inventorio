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

    public static Connection conectar() {
        if (connection != null) {
            return connection;
        }

        try {
            Properties props = ConfigDB.loadProperties();

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✔ Conectado a la base de datos");
        } catch (SQLException e) {
            System.out.println("✘ Error SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✘ Error general: " + e.getMessage());
        }

        return connection;
    }
}
