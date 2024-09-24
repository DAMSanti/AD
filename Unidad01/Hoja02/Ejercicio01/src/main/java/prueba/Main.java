package prueba;

import prueba.conexion.ConexionSQLite;

public class Main {
    public static void main(String[] args) {
        if (ConexionSQLite.get_conexion() != null) {
            System.out.println("Conexión establecida");
            ConexionSQLite.close_conexion();
        } else {
            System.out.println("ERROR al realizar la conexión");
        }
    }
}