package prueba.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conection {
    private static final Logger logger = Logger.getLogger(Connection.class.getName());
    private static Connection conexion = null;

    public static Connection get_conexion() {
        Properties properties = new Properties();
        Connection con = null;
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "postgres");
        properties.setProperty("useSSL", "false");
        properties.setProperty("allowPublicKeyRetrieval", "true");
        properties.setProperty("serverTimezone", "UTC");
        properties.setProperty("allowMultiQueries", "true");
        try {
            con = DriverManager.getConnection("jdbc:postgresql://10.0.22.27:5432/heroes5", properties);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Connection failed", e);
        }

        if (conexion == null) {
            String url = "jdbc:postgresql://10.0.22.27:5432/heroes5";
            String user = "postgres";
            String password = "postgres";
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
