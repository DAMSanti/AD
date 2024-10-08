import prueba.conexion.Conexion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

public static void main(String[] args) {
    Connection connection = Conexion.get_conexion();
    if (connection != null) {
        String userHome = System.getProperty("user.home");
        String url = userHome + "/DB/datos_coches.sql";
        executeScript(connection, url);
    }
}

private static void executeScript(Connection connection, String filePath) {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath));
         Statement statement = connection.createStatement()) {
        StringBuilder sql = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sql.append(line);
            if (line.trim().endsWith(";")) {
                statement.execute(sql.toString());
                sql.setLength(0);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

/*
EJERCICIO 1
Realiza un programa que lee el fichero script datos_coches.sql y:
        • Utilizando la propiedad permitir ejecución de scripts en la conexión, ejecuta
el script para añadir las tablas a la BD y cargar datos en las tablas
*/
