package prueba.servicios;

import prueba.conexion.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Servicios {
    public static String logIn(String user, String password) {
        String nombre = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        if (Conexion.get_conexion() != null) {
            String sql = "SELECT user FROM usuarios WHERE user = ? AND contraseña = ?";
            try {
                st = Conexion.get_conexion().prepareStatement(sql);
                st.setString(1, user);
                st.setString(2, password);
                rs = st.executeQuery();
                if (rs.next()) {
                    nombre = rs.getString("user");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
                Conexion.close_conexion();
            }
        }
        return nombre;
    }

    public static boolean vota(int cancion, String user) {
        PreparedStatement st1 = null;
        PreparedStatement st2 = null;
        PreparedStatement st3 = null;
        PreparedStatement st4 = null;
        boolean correcto = false;
        if (Conexion.get_conexion() != null) {
            String sql1 = "INSERT INTO votos (usuario, fecha, cancion) VALUES (?, CURDATE(), ?)";
            String sql2 = "UPDATE usuarios SET numvotos = numvotos + 1 WHERE user = ?";
            String sql3 = "UPDATE canciones SET total_votos = total_votos + 1 WHERE numCancion = ?";
            String sql4 = "SELECT titulo, grupos.nombre, (SELECT COUNT(*) FROM canciones WHERE total_votos > (SELECT total_votos FROM canciones WHERE numCancion = ?)) AS count_votos FROM canciones INNER JOIN grupos ON canciones.grupo = grupos.codgrupo WHERE numCancion = ?";
            try {
                st1 = Conexion.get_conexion().prepareStatement(sql1);
                st1.setString(1, user);
                st1.setInt(2, cancion);
                if (st1.executeUpdate() > 0) {
                    correcto = true;
                }
                st2 = Conexion.get_conexion().prepareStatement(sql2);
                st2.setString(1, user);
                st2.executeUpdate();
                if (st2.executeUpdate() > 0) {
                    correcto = true;
                }
                st3 = Conexion.get_conexion().prepareStatement(sql3);
                st3.setInt(1, cancion);
                st3.executeUpdate();
                if (st3.executeUpdate() > 0) {
                    correcto = true;
                }
                st4 = Conexion.get_conexion().prepareStatement(sql4);
                st4.setInt(1, cancion);
                st4.setInt(2, cancion);
                ResultSet rs = st4.executeQuery();
                if (rs.next()) {
                    System.out.println("Titulo: " + rs.getString("titulo"));
                    System.out.println("Grupo: " + rs.getString("nombre"));
                    System.out.println("Posicion: " + rs.getInt("count_votos"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarStatement(st1);
                cerrarStatement(st2);
                cerrarStatement(st3);
                Conexion.close_conexion();
            }
        }
        return correcto;
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
