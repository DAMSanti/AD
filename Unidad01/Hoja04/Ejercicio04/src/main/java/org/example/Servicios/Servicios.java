package org.example.Servicios;

import org.example.Conexion.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Servicios {
  /*  public static void main(String[] args) {
        if (Conexion.get_conexion() != null) {
            datos10UltimosVotos();
        } else {
            System.out.println("No se pudo establecer la conexión");
        }
    }
*/
    public static void datos10UltimosVotos() {
        Scanner teclado = new Scanner(System.in);
        ResultSet rs = null;
        Statement st = null;
        Connection con = Conexion.get_conexion();
        if (con != null) {
            String sql = "SELECT usuario, fecha, cancion FROM votos ORDER BY fecha DESC LIMIT 10";
            try {
                con.setAutoCommit(false);
                st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    String oldUser = rs.getString("usuario");
                    String cancion = rs.getString("cancion");
                    System.out.println("Usuario: " + oldUser);
                    System.out.println("Fecha: " + rs.getString("fecha"));
                    System.out.println("Canción: " + cancion);
                    System.out.println("1.- Modificar Usuario");
                    System.out.println("2.- Eliminar");
                    System.out.print("Opción: ");
                    int opcion = teclado.nextInt();
                    switch (opcion) {
                        case 1 -> {
                            System.out.print("Nuevo usuario: ");
                            String newUser = teclado.next();
                            rs.updateString("usuario", newUser);
                            rs.updateRow();
                            actualizarVotosUsuario(con, oldUser, -1);
                            actualizarVotosUsuario(con, newUser, 1);
                            }
                        case 2 -> {
                            rs.deleteRow();
                            actualizarVotosUsuario(con, oldUser, -1);
                            actualizarVotosCancion(con, cancion, -1);
                        }
                        default -> {
                            System.out.println("Opción no válida");
                            con.rollback();
                            return;
                        }
                    }
                }
                con.commit();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
                Conexion.close_conexion();
            }
        }
    }

    private static void actualizarVotosUsuario(Connection con, String usuario, int incremento) throws SQLException {
        try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            String sql = "SELECT * FROM usuarios WHERE user = '" + usuario + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int votos = rs.getInt("numVotos") + incremento;
                rs.updateInt("numVotos", votos);
                rs.updateRow();
            }
        }
    }

    private static void actualizarVotosCancion(Connection con, String cancion, int incremento) throws SQLException {
        try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            String sql = "SELECT * FROM canciones WHERE numCancion = '" + cancion + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int votos = rs.getInt("total_votos") + incremento;
                rs.updateInt("total_votos", votos);
                rs.updateRow();
            }
        }
    }

    public static void cerrarResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void cerrarStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}