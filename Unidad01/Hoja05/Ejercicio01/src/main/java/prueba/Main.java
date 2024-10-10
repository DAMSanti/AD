import prueba.conexion.Conexion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public static void main(String[] args) throws SQLException {
    Connection connection = Conexion.get_conexion();
    if (connection != null) {
        String userHome = System.getProperty("user.home");
        String url = userHome + "/DB/datos_coches.sql";
        executeScript(connection, url);
        //ejecutarScript(connection, url);
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

private static void ejecutarScript(Connection connection, String filePath) throws SQLException {
    try (Statement statement = connection.createStatement()) {
        connection.setAutoCommit(true);
        String script = "";
        script += Files.readAllLines(Paths.get(filePath));
        // String script = Files.readString(Paths.get(filePath));
        System.out.println(script);
        statement.execute(script);
        connection.commit();
    } catch (Exception e) {
        e.printStackTrace();
        connection.rollback();
    }

}

/*
EJERCICIO 1
Realiza un programa que lee el fichero script datos_coches.sql y:
        • Utilizando la propiedad permitir ejecución de scripts en la conexión, ejecuta
el script para añadir las tablas a la BD y cargar datos en las tablas
*/
