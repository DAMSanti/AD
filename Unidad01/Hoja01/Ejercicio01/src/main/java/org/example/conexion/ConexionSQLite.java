package org.example.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionSQLite {
    private static Connection conexion = null;
    public static Connection get_conexion() {
        if (conexion != null) {
            return conexion;
        } else {
            String userHome = System.getProperty("user.home");
            String url = "jdbc:sqlite:" + userHome + "/DB/heroes.db";
            try {
                conexion = DriverManager.getConnection(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return conexion;
        }
    }

    public static void close_conexion() {
        try {
            conexion.close();
            conexion = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
