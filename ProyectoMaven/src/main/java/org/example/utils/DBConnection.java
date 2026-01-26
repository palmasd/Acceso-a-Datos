package org.example.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties props = new Properties();

                InputStream input = DBConnection.class
                        .getClassLoader()
                        .getResourceAsStream("application.properties");

                if (input == null) {
                    throw new RuntimeException("No se encontró application.properties");
                }

                props.load(input);

                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");
                String driver = props.getProperty("db.driver");

                Class.forName(driver);

                connection = DriverManager.getConnection(url, user, password);

                System.out.println("Conexión MySQL OK");

            } catch (Exception e) {
                throw new RuntimeException("Error al crear la conexión", e);
            }
        }
        return connection;
    }
}
