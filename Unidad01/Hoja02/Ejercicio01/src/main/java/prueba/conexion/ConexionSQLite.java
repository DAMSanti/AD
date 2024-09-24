package prueba.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionSQLite {
    private static final Logger logger = Logger.getLogger(ConexionSQLite.class.getName());
    private static Connection conexion = null;

    public static Connection get_conexion() {
        if (conexion == null) {
            //String userHome = System.getProperty("user.home");
            String url = "jdbc:sqlite:heroes.db";
            try {
                conexion = DriverManager.getConnection(url);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Connection failed", e);
            }
        }
        return conexion;
    }

    public static void close_conexion() {
        try {
            conexion.close();
            conexion = null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Connection failed", e);
        }
    }
}
