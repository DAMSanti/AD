package prueba.servicios;

import prueba.conexion.Conexion;
import prueba.objetos.Canciones;
import prueba.objetos.Grupos;
import prueba.objetos.Votos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Servicios {
    public static List<Grupos> listarGrupos() {
        List<Grupos> grupos = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        if (Conexion.get_conexion() != null) {
            String sql = "SELECT codgrupo, nombre, localidad, estilo, esgrupo, annoGrab, fechaEstreno, compania FROM grupos";
            try {
                st = Conexion.get_conexion().prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    Grupos grupo = new Grupos(rs.getInt("codgrupo"), rs.getString("nombre"), rs.getString("localidad"), rs.getString("estilo"), rs.getBoolean("esgrupo"), rs.getInt("annoGrab"), rs.getDate("fechaEstreno"), rs.getString("compania"));
                    grupos.add(grupo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
                Conexion.close_conexion();
            }
        }
        return grupos;
    }

    public static Map<String, List<Canciones>> listarCanciones() {
        Map<String, List<Canciones>> canciones = new TreeMap<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        if (Conexion.get_conexion() != null) {
            String sql = "SELECT grupos.nombre, numCancion, grupo, duracion, titulo, esgrupo, annoGrab, fechaEstreno, compania FROM grupos INNER JOIN canciones ON codgrupo = grupo";
            try {
                st = Conexion.get_conexion().prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    Canciones cancion = new Canciones(rs.getInt("numCancion"), rs.getInt("grupo"), rs.getTime("duracion"), rs.getString("titulo"), 0);
                    if (canciones.containsKey(rs.getString("nombre"))) {
                        canciones.get(rs.getString("nombre")).add(cancion);
                    } else {
                        List<Canciones> lista = new ArrayList<>();
                        lista.add(cancion);
                        canciones.put(rs.getString("nombre"), lista);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
                Conexion.close_conexion();
            }
        }
        return canciones;
    }

    public static Map<String, Integer> numeroCancionesPorGrupo() {
        Map<String, Integer> canciones = new TreeMap<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        if (Conexion.get_conexion() != null) {
            String sql = "SELECT grupos.nombre, COUNT(numCancion) FROM grupos LEFT JOIN canciones ON codgrupo = grupo GROUP BY grupos.nombre";
            try {
                st = Conexion.get_conexion().prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    canciones.put(rs.getString("nombre"), rs.getInt("COUNT(numCancion)"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
                Conexion.close_conexion();
            }
        }
        return canciones;
    }

    public static List<Canciones> mostrarCancionesGrupo(String grupo) {
        List<Canciones> canciones = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        if (Conexion.get_conexion() != null) {
            String sql = "SELECT numCancion, grupo, duracion, titulo, total_votos FROM canciones INNER JOIN grupos ON codgrupo = grupo WHERE nombre = ?";
            try {
                st = Conexion.get_conexion().prepareStatement(sql);
                st.setString(1, grupo);
                rs = st.executeQuery();
                while (rs.next()) {
                    Canciones cancion = new Canciones(rs.getInt("numCancion"), rs.getInt("grupo"), rs.getTime("duracion"), rs.getString("titulo"), rs.getInt("total_votos"));
                    canciones.add(cancion);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
                Conexion.close_conexion();
            }
        }
        return canciones;
    }

    public static List<Canciones> cancionesMasVotadas() {
        List<Canciones> canciones = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        if (Conexion.get_conexion() != null) {
            String sql = "SELECT numCancion, grupo, duracion, titulo, total_votos FROM canciones ORDER BY total_votos DESC LIMIT 5";
            try {
                st = Conexion.get_conexion().prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    Canciones cancion = new Canciones(rs.getInt("numCancion"), rs.getInt("grupo"), rs.getTime("duracion"), rs.getString("titulo"), rs.getInt("total_votos"));
                    canciones.add(cancion);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
                Conexion.close_conexion();
            }
        }
        return canciones;
    }

    public static List<Grupos> gruposSinCancion() {
        List<Grupos> grupos = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        if (Conexion.get_conexion() != null) {
            String sql = "SELECT grupos.codgrupo, grupos.nombre, grupos.localidad, grupos.estilo, grupos.esgrupo, grupos.annoGrab, grupos.fechaEstreno, grupos.compania, COUNT(numCancion)" +
                    " FROM grupos LEFT JOIN canciones ON codgrupo = grupo GROUP BY grupos.codgrupo HAVING COUNT(numCancion) = 0;";
            try {
                st = Conexion.get_conexion().prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    Grupos grupo = new Grupos(rs.getInt("codgrupo"), rs.getString("nombre"), rs.getString("localidad"), rs.getString("estilo"), rs.getBoolean("esgrupo"), rs.getInt("annoGrab"), rs.getDate("fechaEstreno"), rs.getString("compania"));
                    grupos.add(grupo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
                Conexion.close_conexion();
            }
        }
        return grupos;
    }

    public static List<Votos> ultimosVotos() {
        List<Votos> votos = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        if (Conexion.get_conexion() != null) {
            String sql = "SELECT usuario, fecha, cancion, grupos.nombre, canciones.titulo FROM votos INNER JOIN canciones ON cancion=numCancion INNER JOIN grupos ON canciones.grupo = grupos.codgrupo ORDER BY fecha DESC LIMIT 5";
            try {
                st = Conexion.get_conexion().prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    Votos voto = new Votos(rs.getString("usuario"), rs.getDate("fecha"), rs.getString("titulo"), rs.getString("nombre"));
                    votos.add(voto);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
                Conexion.close_conexion();
            }
        }
        return votos;
    }

    public static boolean eliminarGrupo(String grupo) {
        boolean correcto = false;
        PreparedStatement st = null;
        if (Conexion.get_conexion() != null) {
            String sql = "DELETE FROM votos WHERE cancion IN (SELECT numCancion FROM canciones INNER JOIN grupos ON grupo=codgrupo WHERE nombre = ?)";
            try {
                st = Conexion.get_conexion().prepareStatement(sql);
                st.setString(1, grupo);
                st.executeUpdate();
                sql = "DELETE FROM canciones WHERE grupo = (SELECT codgrupo FROM grupos WHERE nombre = ?)";
                st = Conexion.get_conexion().prepareStatement(sql);
                st.setString(1, grupo);
                st.executeUpdate();
                correcto = true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarStatement(st);
                Conexion.close_conexion();
            }
        }
        return correcto;
    }

    public static Grupos listaGrupo(String nombre) {
        Grupos grupo = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        if (Conexion.get_conexion() != null) {
            String sql = "SELECT codgrupo, nombre, localidad, estilo, esgrupo, annoGrab, fechaEstreno, compania FROM grupos WHERE nombre = ?";
            try {
                st = Conexion.get_conexion().prepareStatement(sql);
                st.setString(1, nombre);
                rs = st.executeQuery();
                if (rs.next()) {
                    grupo = new Grupos(rs.getInt("codgrupo"), rs.getString("nombre"), rs.getString("localidad"), rs.getString("estilo"), rs.getBoolean("esgrupo"), rs.getInt("annoGrab"), rs.getDate("fechaEstreno"), rs.getString("compania"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
                Conexion.close_conexion();
            }
        }
        return grupo;
    }

    public static boolean modificarGrupo(Grupos grupo) {
        boolean correcto = false;
        PreparedStatement st = null;
        if (Conexion.get_conexion() != null) {
            String sql = "UPDATE grupos SET nombre = ?, localidad = ?, estilo = ?, esgrupo = ?, annoGrab = ?, fechaEstreno = ?, compania = ? WHERE codgrupo = ?";
            try {
                st = Conexion.get_conexion().prepareStatement(sql);
                st.setString(1, grupo.getNombre());
                st.setString(2, grupo.getLocalidad());
                st.setString(3, grupo.getEstilo());
                st.setBoolean(4, grupo.getEsgrupo());
                st.setInt(5, grupo.getAnnograb());
                st.setDate(6, grupo.getFechaEstreno());
                st.setString(7, grupo.getCompañia());
                st.setInt(8, grupo.getCodgrupo());
                st.executeUpdate();
                correcto = true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                cerrarStatement(st);
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
