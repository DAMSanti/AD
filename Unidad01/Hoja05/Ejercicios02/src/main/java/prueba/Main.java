package prueba;

import prueba.conexion.Conexion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection connection = Conexion.get_conexion();
        if (connection != null) {
            String userHome = System.getProperty("user.home");
            String url = userHome + "/DB/heroes_postgres.sql";
            //executeScript(connection, url);
            try {
                ejecutarScript(connection, url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            connection.setAutoCommit(false);
            String script = "";
            for (String line : Files.readAllLines(Paths.get(filePath))) {
                script += line;
            }
            System.out.println(script);
            statement.execute(script);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            Conexion.close_conexion();
        }
    }
}