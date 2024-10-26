package prueba.services;

import prueba.conexion.ConexionMySQL;
import prueba.conexion.ConexionSQLite;
import prueba.modelos.Emitido;
import prueba.modelos.Solicitud;
import prueba.modelos.Usuario;
import prueba.modelos.Video;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Services {
    public static List<Video> listarVideos() {
        List<Video> videos = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT num_video, titulo, interprete, duracion, anno, num_emisiones, disponible FROM videos;";
        if (ConexionMySQL.get_conexion() != null ) {
            try {
                st = ConexionMySQL.get_conexion().createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Video vid = new Video();
                    vid.setNum_video(rs.getInt(1));
                    vid.setTitulo(rs.getString(2));
                    vid.setInterprete(rs.getString(3));
                    vid.setDuracion(rs.getTime(4));
                    vid.setAnno(rs.getInt(5));
                    vid.setNum_emisiones(rs.getInt(6));
                    vid.setDisponible(rs.getInt(7) == 1 ? true : false);
                    videos.add(vid);
                }
            } catch (Exception e) {
                System.out.println("Ha habido un error obteniendo los datos de los videos.");
            }  finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
            }
        }
        return videos;
    }

    public static List<Emitido> listarEmitidos() {
        List<Emitido> emitidos = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT num_emision, video, fecha, hora, num_peticiones FROM emitidos;";
        if (ConexionMySQL.get_conexion() != null ) {
            try {
                st = ConexionMySQL.get_conexion().createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Emitido emit = new Emitido();
                    emit.setNum_emision(rs.getInt(1));
                    emit.setVideo(rs.getInt(2));
                    emit.setFecha(rs.getDate(3));
                    emit.setHora(rs.getTime(4));
                    emit.setNum_peticiones(rs.getInt(5));
                    emitidos.add(emit);
                }
            } catch (Exception e) {
                System.out.println("Ha habido un error obteniendo los datos de los videos.");
            }  finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
            }
        }
        return emitidos;
    }

    public static int transpasaVideos(List<Video> videos) {
        PreparedStatement st = null;
        int n = -1;
        String sql = "INSERT INTO videos (num_video, titulo, interprete, duracion, anno, num_emisiones, disponible) VALUES (?, ?, ?, ?, ?, ?, ?)";
        if (ConexionSQLite.get_conexion() != null) {
            for (Video vid : videos) {
                try {
                    st = ConexionSQLite.get_conexion().prepareStatement(sql);
                    st.setInt(1, vid.getNum_video());
                    st.setString(2, vid.getTitulo());
                    st.setString(3, vid.getInterprete());
                    st.setTime(4, vid.getDuracion());
                    st.setInt(5, vid.getAnno());
                    st.setInt(6, vid.getNum_emisiones());
                    st.setInt(7, vid.isDisponible() == true ? 1 : 0);
                    n = st.executeUpdate();
                }catch (Exception e) {
                    System.out.println("No se ha podido realizar la migración de la tabla videos.");
                }  finally {
                    cerrarStatement(st);
                }
            }
        }
        return n;
    }

    public static int transpasaEmitidos(List<Emitido> emitidos) {
        PreparedStatement st = null;
        int n = -1;
        String sql = "INSERT INTO emitidos (num_emision, video, fecha, hora, num_peticiones) VALUES (?, ?, ?, ?, ?)";
        if (ConexionSQLite.get_conexion() != null) {
            for (Emitido emit : emitidos) {
                try {
                    st = ConexionSQLite.get_conexion().prepareStatement(sql);
                    st.setInt(1, emit.getNum_emision());
                    st.setInt(2, emit.getVideo());
                    st.setDate(3, emit.getFecha());
                    st.setTime(4, emit.getHora());
                    st.setInt(5, emit.getNum_peticiones());
                    n = st.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cerrarStatement(st);
                }
            }
        }
        return n;
    }

    public static Usuario usuarioPorID(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Usuario usu = new Usuario();
        String sql = "SELECT num_usu, usuario, contra, nombre, apellidos, fechanac, num_peticiones FROM usuarios WHERE num_usu = ?";
        if (ConexionMySQL.get_conexion() != null) {
            try {
                st = ConexionMySQL.get_conexion().prepareStatement(sql);
                st.setInt(1, id);
                rs = st.executeQuery();
                if (rs.next()) {
                    usu.setNum_usu(id);
                    usu.setUsuario(rs.getString(2));
                    usu.setContra(rs.getString(3));
                    usu.setNombre(rs.getString(4));
                    usu.setApellidos(rs.getString(5));
                    usu.setFechanac(rs.getDate(6));
                    usu.setNum_peticiones(rs.getInt(7));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cerrarStatement(st);
                cerrarResultSet(rs);
            }
        }
        return usu;
    }

    public static int solicitudesPorUsuario(Usuario usu) {
        PreparedStatement st = null;
        ResultSet rs = null;
        int solicitudes = 0;
        String sql = "SELECT COUNT(*) FROM solicitudes WHERE num_usu = ?";
        if (ConexionMySQL.get_conexion() != null) {
            try {
                st = ConexionMySQL.get_conexion().prepareStatement(sql);
                st.setInt(1, usu.getNum_usu());
                rs = st.executeQuery();
                if (rs.next()) {
                    solicitudes = rs.getInt(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cerrarStatement(st);
                cerrarResultSet(rs);
            }
        }
        return solicitudes;
    }

    public static boolean actualizaPeticiones(Usuario usu) {
        PreparedStatement st = null;
        boolean correcto = false;
        String sql = "UPDATE usuarios SET num_peticiones = num_peticiones + ? WHERE num_usu = ?";
        if (ConexionMySQL.get_conexion() != null) {
            try {
                st = ConexionMySQL.get_conexion().prepareStatement(sql);
                st.setInt(1, solicitudesPorUsuario(usu));
                st.setInt(2, usu.getNum_usu());
                st.executeUpdate();
                correcto = true;
            } catch ( Exception e) {
                e.printStackTrace();
            } finally {
                cerrarStatement(st);
            }
        }
        return correcto;
    }

    public static Video videosPorTitulo(String titulo) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Video vid = new Video();
        String sql = "SELECT num_video, titulo, interprete, duracion, anno, num_emisiones, disponible FROM videos WHERE titulo = ? ";
        if (ConexionMySQL.get_conexion() != null) {
            try {
                st = ConexionMySQL.get_conexion().prepareStatement(sql);
                st.setString(1, titulo);
                rs = st.executeQuery();
                if (rs.next()) {
                    vid.setNum_video(rs.getInt(1));
                    vid.setTitulo(titulo);
                    vid.setInterprete(rs.getString(3));
                    vid.setDuracion(rs.getTime(4));
                    vid.setAnno(rs.getInt(5));
                    vid.setNum_emisiones(rs.getInt(6));
                    vid.setDisponible(rs.getInt(7) == 1 ? true : false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cerrarStatement(st);
                cerrarResultSet(rs);
            }
        }
        return vid;
    }

    public static List<Solicitud> listarSolicitudesVideo(Video vid) {
        List<Solicitud> solicitudes = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT num, solicitudes.num_usu, video, fecha, hora, via, nombre, apellidos FROM solicitudes INNER JOIN usuarios ON solicitudes.num_usu = usuarios.num_usu WHERE video = ?";
        if (ConexionMySQL.get_conexion() != null) {
            try {
                st = ConexionMySQL.get_conexion().prepareStatement(sql);
                st.setInt(1, vid.getNum_video());
                rs = st.executeQuery();
                while (rs.next()) {
                    Solicitud solicitud = new Solicitud();
                    solicitud.setNum(rs.getInt(1));
                    solicitud.setNum_usu(rs.getInt(2));
                    solicitud.setVideo(rs.getInt(3));
                    solicitud.setFecha(rs.getDate(4));
                    solicitud.setHora(rs.getTime(5));
                    solicitud.setVia(rs.getString(6));
                    solicitud.setNombre(rs.getString(7));
                    solicitud.setApellidos(rs.getString(8));
                    solicitudes.add(solicitud);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cerrarStatement(st);
                cerrarResultSet(rs);
            }
        }
        return solicitudes;
    }

    public static List<Solicitud> listarSolicitudesUsuario(Usuario usu) {
        List<Solicitud> solicitudes = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT num, solicitudes.num_usu, video, fecha, hora, via, nombre, apellidos, titulo FROM solicitudes INNER JOIN usuarios ON usuarios.num_usu = solicitudes.num_usu INNER JOIN videos ON video=num_video WHERE solicitudes.num_usu = ?";
        if (ConexionMySQL.get_conexion() != null) {
            try {
                st = ConexionMySQL.get_conexion().prepareStatement(sql);
                st.setInt(1, usu.getNum_usu());
                rs = st.executeQuery();
                while (rs.next()) {
                    Solicitud solicitud = new Solicitud();
                    solicitud.setNum(rs.getInt(1));
                    solicitud.setNum_usu(rs.getInt(2));
                    solicitud.setVideo(rs.getInt(3));
                    solicitud.setFecha(rs.getDate(4));
                    solicitud.setHora(rs.getTime(5));
                    solicitud.setVia(rs.getString(6));
                    solicitud.setNombre(rs.getString(7));
                    solicitud.setApellidos(rs.getString(8));
                    solicitud.setTitulo(rs.getString(9));
                    solicitudes.add(solicitud);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cerrarStatement(st);
                cerrarResultSet(rs);
            }
        }
        return solicitudes;
    }

    public static List<Solicitud> listarVideosDeSolicitud(List<Solicitud> solicitudes) {
        List<Solicitud> solicit = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT titulo, fecha, hora FROM videos INNER JOIN solicitudes WHERE num_video = ?";
        if (ConexionMySQL.get_conexion() != null) {
            try {
                for (Solicitud sol : solicitudes) {
                    st = ConexionMySQL.get_conexion().prepareStatement(sql);
                    st.setInt(1, sol.getVideo());
                    rs = st.executeQuery();
                    while (rs.next()) {
                        Video vid = new Video();
                        vid.setTitulo(rs.getString(1));

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cerrarStatement(st);
                cerrarResultSet(rs);
            }
        }
        return solicit;
    }

    public static boolean eliminarSolicitud(Solicitud sol) {
        boolean correcto = false;
        PreparedStatement st = null;
        String sql = "DELETE FROM solicitudes WHERE num = ?";
        if (ConexionMySQL.get_conexion() != null) {
            try {
                st = ConexionMySQL.get_conexion().prepareStatement(sql);
                st.setInt(1, sol.getNum());
                st.executeUpdate();
                correcto = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cerrarStatement(st);

            }
        }
        return correcto;
    }

    public static List<Solicitud> solicitudesPorNumero(int n) {
        List<Solicitud> solicitudes = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT video FROM solicitudes GROUP BY video HAVING COUNT(*) > ?;";
        if (ConexionMySQL.get_conexion() != null) {
            try {
                st = ConexionMySQL.get_conexion().prepareStatement(sql);
                st.setInt(1, n);
                rs = st.executeQuery();
                while (rs.next()) {
                    Solicitud sol = new Solicitud();
                    sol.setVideo(rs.getInt(1));
                    solicitudes.add(sol);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return solicitudes;
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
