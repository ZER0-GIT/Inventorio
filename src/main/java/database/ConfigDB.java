package database;

import java.io.InputStream;
import java.util.Properties;

public class ConfigDB {

    public static Properties loadProperties() {
        Properties props = new Properties();

        try (InputStream input = ConfigDB.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {

            if (input == null) {
                throw new RuntimeException("No se encontró config.properties");
            }

            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error cargando configuración: " + e.getMessage(), e);
        }

        return props;
    }
}
