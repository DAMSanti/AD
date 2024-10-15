package org.example.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conection {
    private static final Logger logger = Logger.getLogger(Conection.class.getName());
    private static Connection conexion = null;

    public static Connection get_conexion() {
        Properties properties = new Properties();
        Connection con = null;
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        properties.setProperty("useSSL", "false");
        properties.setProperty("allowPublicKeyRetrieval", "true");
        properties.setProperty("serverTimezone", "UTC");
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/preguntatest", properties);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Connection failed", e);
        }

        if (conexion == null) {
            String url = "jdbc:mysql://localhost:3306/preguntatest";
            String user = "root";
            String password = "root";
            try {
                conexion = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Connection failed", e);
            }
        }
        return conexion;
    }

    public static void close_conexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                conexion = null;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Connection failed", e);
        }
    }
}