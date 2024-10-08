package prueba.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private static final Logger logger = Logger.getLogger(Conexion.class.getName());
    private static Connection conexion = null;

    public static Connection get_conexion() {
        Properties properties = new Properties();
        Connection con = null;
        properties.setProperty("user", "root");
        properties.setProperty("password", "mysql");
        properties.setProperty("useSSL", "false");
        properties.setProperty("allowPublicKeyRetrieval", "true");
        properties.setProperty("serverTimezone", "UTC");
        properties.setProperty("allowMultiQueries", "true");
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/", properties);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Connection failed", e);
        }

        if (conexion == null) {
            String url = "jdbc:mysql://localhost:3307/";
            String user = "root";
            String password = "mysql";
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
