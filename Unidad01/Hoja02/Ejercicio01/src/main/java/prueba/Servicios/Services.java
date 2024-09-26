package prueba.Servicios;

import prueba.conexion.ConexionSQLite;
import prueba.objetos.Pelicula;
import prueba.objetos.Personaje;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Services {
    public static List<Pelicula> obtenerPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        PreparedStatement st=null;
        ResultSet rs = null;
        int n= 0;
        if (ConexionSQLite.get_conexion()!=null){
            String sql = "SELECT * FROM movies";
            try{
                st = ConexionSQLite.get_conexion().prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()){
                    Pelicula pelicula = new Pelicula(rs.getInt("id"), rs.getString("title"), rs.getInt("duration"), rs.getInt("year"), rs.getString("productora"));
                    peliculas.add(pelicula);
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
            }
        }
        return peliculas;
    }

    public static List<Personaje> obtenerPersonajes() {
        List<Personaje> personajes = new ArrayList<>();
        PreparedStatement st=null;
        ResultSet rs = null;
        int n= 0;
        if (ConexionSQLite.get_conexion()!=null){
            String sql = "SELECT * FROM Characters";
            try{
                st = ConexionSQLite.get_conexion().prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()){
                    Personaje personaje = new Personaje(rs.getInt("id"), rs.getString("name"), rs.getString("powers"), rs.getString("company"), rs.getString("origin"), rs.getInt("isHeroe"));
                    personajes.add(personaje);
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
            }
        }
        return personajes;
    }

    public static Personaje obtenerPersonajeID(int id) {
        Personaje personaje = null;
        PreparedStatement st=null;
        ResultSet rs = null;
        int n= 0;
        if (ConexionSQLite.get_conexion()!=null){
            String sql = "SELECT * FROM Characters WHERE id = ?";
            try{
                st = ConexionSQLite.get_conexion().prepareStatement(sql);
                st.setInt(1, id);
                rs = st.executeQuery();
                rs.next();
                personaje = new Personaje(rs.getInt("id"), rs.getString("name"), rs.getString("powers"), rs.getString("company"), rs.getString("origin"), rs.getInt("isHeroe"));
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
            }
        }
        return personaje;
    }

    public static List<Map<String, Object>> obtenerPersonajesPelicula() {
        List<Map<String, Object>> personajes = new ArrayList<>();
        PreparedStatement st=null;
        ResultSet rs = null;
        int n= 0;
        if (ConexionSQLite.get_conexion()!=null){
            String sql = "SELECT movies.title, movies.year, characters.name, acts.actor FROM acts LEFT JOIN characters ON acts.character_id=characters.id LEFT JOIN movies ON acts.movie_id=movies.id;";
            try{
                st = ConexionSQLite.get_conexion().prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()){
                    Map<String, Object> personaje = new HashMap<>();
                    personaje.put("titulo", rs.getString("title"));
                    personaje.put("año", rs.getInt("year"));
                    personaje.put("nombre", rs.getString("name"));
                    personaje.put("actor", rs.getString("actor"));
                    personajes.add(personaje);
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
            }
        }
        return personajes;
    }

    public static List<Pelicula> obtenerPeliculasSinProductora() {
        List<Pelicula> peliculas = new ArrayList<>();
        PreparedStatement st=null;
        ResultSet rs = null;
        int n= 0;
        if (ConexionSQLite.get_conexion()!=null){
            String sql = "SELECT * FROM Movies WHERE productora IS NULL";
            try{
                st = ConexionSQLite.get_conexion().prepareStatement(sql);
                rs = st.executeQuery();
                while(rs.next()) {
                    Pelicula pelicula = new Pelicula(rs.getInt("id"), rs.getString("title"), rs.getInt("duration"), rs.getInt("year"), rs.getString("productora"));
                    peliculas.add(pelicula);
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
            }
        }
        return peliculas;
    }

    public static List<Map<String, Object>> obtenerPersonajesPorPelicula() {
        List<Map<String, Object>> actores = new ArrayList<>();
        PreparedStatement st=null;
        ResultSet rs = null;
        int n= 0;
        if (ConexionSQLite.get_conexion()!=null){
            String sql = "SELECT movies.title, COUNT(acts.actor) FROM acts LEFT JOIN movies ON acts.movie_id=movies.id GROUP BY movies.title";
            try{
                st = ConexionSQLite.get_conexion().prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()){
                    Map<String, Object> personaje = new HashMap<>();
                    personaje.put("titulo", rs.getString("title"));
                    personaje.put("actores", rs.getInt("COUNT(acts.actor)"));
                    actores.add(personaje);
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
            }
        }
        return actores;
    }

    public static List<Pelicula> obtenerPeliculaMasVieja() {
        List<Pelicula> peliculas = new ArrayList<>();
        PreparedStatement st=null;
        ResultSet rs = null;
        int n= 0;
        if (ConexionSQLite.get_conexion()!=null){
            String sql = "SELECT * FROM movies WHERE year = (SELECT MIN(year) FROM movies)";
            try{
                st = ConexionSQLite.get_conexion().prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()){
                    Pelicula pelicula = new Pelicula(rs.getInt("id"), rs.getString("title"), rs.getInt("duration"), rs.getInt("year"), rs.getString("productora"));
                    peliculas.add(pelicula);
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
            }
        }
        return peliculas;
    }

    public static List<Pelicula> obtenerPeliculasPorActor(String actor) {
        List<Pelicula> peliculas = new ArrayList<>();
        PreparedStatement st=null;
        ResultSet rs = null;
        int n= 0;
        if (ConexionSQLite.get_conexion()!=null){
            String sql = "SELECT movies.* FROM Movies INNER JOIN acts ON movies.id=acts.movie_id WHERE acts.actor = ?";
            try{
                st = ConexionSQLite.get_conexion().prepareStatement(sql);
                st.setString(1, actor);
                rs = st.executeQuery();
                while (rs.next()){
                    Pelicula pelicula = new Pelicula(rs.getInt("id"), rs.getString("title"), rs.getInt("duration"), rs.getInt("year"), rs.getString("productora"));
                    peliculas.add(pelicula);
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
            }
        }
        return peliculas;
    }

    public static List<Pelicula> obtenerPeliculasSinPersonajes() {
        List<Pelicula> peliculas = new ArrayList<>();
        PreparedStatement st=null;
        ResultSet rs = null;
        int n= 0;
        if (ConexionSQLite.get_conexion()!=null){
            String sql = "SELECT movies.*, COUNT(actor) AS actor_count FROM movies LEFT JOIN Acts ON movies.id=acts.movie_id GROUP BY title HAVING actor_count = 0;";
            try{
                st = ConexionSQLite.get_conexion().prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()){
                    Pelicula pelicula = new Pelicula(rs.getInt("id"), rs.getString("title"), rs.getInt("duration"), rs.getInt("year"), rs.getString("productora"));
                    peliculas.add(pelicula);
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                cerrarResultSet(rs);
                cerrarStatement(st);
            }
        }
        return peliculas;
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
