package org.example.services;

import org.example.conexion.ConexionSQLite;
import org.example.objetos.Acts;
import org.example.objetos.Characters;
import org.example.objetos.Movies;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Servicios {

    public static int insertarPelicula(Movies movie) {
        String sql = "INSERT INTO movies(title, duration, year, productora) VALUES(?,?,?,?)";
        int n = -1;
        try {
            Connection conexion = ConexionSQLite.get_conexion();
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, movie.getTitle());
            pstmt.setInt(2, movie.getDuration());
            pstmt.setInt(3, movie.getYear());
            pstmt.setString(4, movie.getProductora());
            n=pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionSQLite.close_conexion();
        }
        return n;
    }

    public static int insertarActuaciones(Acts act) {
        String sql = "INSERT INTO acts (character_id, movie_id,  main_character, actor) VALUES (?, ?, ?, ?)";
        int n = -1;
        try {
            Connection conn = ConexionSQLite.get_conexion();
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setInt(1, act.getCharacter_id());
            pstatement.setInt(2, act.getMovie_id());
            pstatement.setInt(3, act.isMain_character());
            pstatement.setString(4, act.getActor());
            n=pstatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionSQLite.close_conexion();
        }
        return n;
    }

    public static int insertarPersonaje(Characters character) {
        String sql = "INSERT INTO characters (name, powers, company, origin, isHeroe) VALUES  (?, ?, ?, ?, ?)";
        int n = -1;
        try {
            Connection conn = ConexionSQLite.get_conexion();
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setString(1, character.getName());
            pstatement.setString(2, character.getPowers());
            pstatement.setString(3, character.getCompany());
            pstatement.setString(4, character.getOrigin());
            pstatement.setInt(5, character.getIsHeroe());
            n=pstatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionSQLite.close_conexion();
        }
        return n;
    }

    public static int modificarOrigen(String nombrePersonaje, Characters character) {
        String sql = "UPDATE characters SET origin = ? WHERE name = ?";
        int n = -1;
        try {
            Connection conn = ConexionSQLite.get_conexion();
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setString(1, character.getOrigin());
            pstatement.setString(2, nombrePersonaje);
            n=pstatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionSQLite.close_conexion();
        }
        return n;
    }

    public static int modificarEstreno(int idPeli, Movies movie) {
        String sql = "UPDATE movies SET year = ? WHERE id = ?";
        int n = -1;
        try {
            Connection conn = ConexionSQLite.get_conexion();
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setInt(1, movie.getYear());
            pstatement.setInt(2, idPeli);
            n=pstatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionSQLite.close_conexion();
        }
        return n;
    }

    public static int eliminarActuación(int idPersonaje, int idPelicula) {
        String sql = "DELETE FROM acts WHERE character_id = ? AND movie_id = ?";
        int n = -1;
        try {
            Connection conn = ConexionSQLite.get_conexion();
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setInt(1, idPersonaje);
            pstatement.setInt(2, idPelicula);
            n=pstatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionSQLite.close_conexion();
        }
        return n;
    }

    public static int eliminarPelicula(String tituloPelicula) {
        String sql = "DELETE FROM movies WHERE title = ?";
        int n = -1;
        try {
            Connection conn = ConexionSQLite.get_conexion();
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setString(1, tituloPelicula);
            n=pstatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionSQLite.close_conexion();
        }
        return n;
    }

    public static int modificarProductora(String nombreProductora, String nuevaProductora) {
        String sql = "UPDATE movies SET productora = ? WHERE productora = ?";
        int n = -1;
        try {
            Connection conn = ConexionSQLite.get_conexion();
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setString(1, nuevaProductora);
            pstatement.setString(2, nombreProductora);
            n=pstatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexionSQLite.close_conexion();
        }
        return n;
    }
}
