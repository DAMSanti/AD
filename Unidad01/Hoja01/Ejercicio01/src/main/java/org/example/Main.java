package org.example;


import org.example.conexion.ConexionSQLite;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        if (ConexionSQLite.get_conexion() != null) {
            System.out.println("Conexión establecida");
            eliminarTablas();
            crearTablas();
            addMovies();
            addCharacters();
            addActs();
            ConexionSQLite.close_conexion();
        } else {
            System.out.println("ERROR al realizar la conexión");
        }
    }

    public static void eliminarTablas() {
        String sqlElimina = "DROP TABLE IF EXISTS characters";
        String sqlElimina2 = "DROP TABLE IF EXISTS movies";
        String sqlElimina3 = "DROP TABLE IF EXISTS acts";
        try {
            Statement st = ConexionSQLite.get_conexion().createStatement();
            st.executeUpdate(sqlElimina);
            st.executeUpdate(sqlElimina2);
            st.executeUpdate(sqlElimina3);
            System.out.println("Tablas eliminadas");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void crearTablas() {
        String sqlCharacters = "CREATE TABLE characters (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, powers TEXT, company TEXT, origin TEXT, isHeroe INTEGER CHECK (isHeroe IN (0, 1)))";
        String sqlMovies = "CREATE TABLE movies (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, duration INTEGER, year INTEGER, productora TEXT)";
        String sqlActs = "CREATE TABLE acts (id INTEGER PRIMARY KEY AUTOINCREMENT, character_id INTEGER NOT NULL, movie_id INTEGER NOT NULL, minutes_in_movie INTEGER, main_character BOOLEAN NOT NULL, actor TEXT, FOREIGN KEY (character_ID) REFERENCES characters(id), FOREIGN KEY (movie_id) REFERENCES movies(id))";
        try {
            Statement st = ConexionSQLite.get_conexion().createStatement();
            st.executeUpdate(sqlCharacters);
            st.executeUpdate(sqlMovies);
            st.executeUpdate(sqlActs);
            System.out.println("Tablas creadas");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addMovies() {
        String sqlMovies = "INSERT INTO movies (title, duration, year, productora) VALUES ('The Avengers', 143, 2012, 'Marvel Studios'), ('Wonder Woman', 141, 2017, 'Warner Bros'), ('Deadpool', 108, 2016, '20th Century Fox'), ('Aquaman', 143, 2018, 'Warner Bros'), ('The Dark Knight', 152, 2008, 'Warner Bros')";
        try {
            Statement st = ConexionSQLite.get_conexion().createStatement();
            System.out.println("Se han añadido " + st.executeUpdate(sqlMovies) + " películas");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addCharacters() {
        String sqlCharacters = "INSERT INTO characters (name, powers, company, origin, isHeroe) VALUES ('Spider-Man', 'Agilidad, trepar paredes, sentido arácnido', 'Marvel', 'Nueva York', 1), " +
                "('Batman', 'Alta inteligencia, habilidades marciales', 'DC', 'Gotham City', 1), ('Wonder Woman', 'Fuerza, agilidad, inmortalidad', 'DC', 'Themyscira', 1)," +
                "('Iron Man', 'Armadura avanzada, vuelo, armas láser', 'Marvel', 'Malibu', 1), ('Black Widow', 'Agilidad, combate cuerpo a cuerpo', 'Marvel', 'Rusia', 1)," +
                "('Superman', 'Fuerza, vuelo, visión de rayos láser', 'DC', 'Krypton', 1), ('Captain Marvel', 'Fuerza, vuelo, energía cósmica', 'Marvel', 'Planeta Hala', 1), " +
                "('Deadpool', 'Regeneración, inmortalidad', 'Marvel', 'Canadá', 0), ('Aquaman', 'Control del agua, comunicación con criaturas marinas', 'DC', 'Atlantis', 1), " +
                "('Harley Quinn', 'Experta en combate cuerpo a cuerpo, inteligencia', 'DC', 'Gotham City', 0)";
        try {
            Statement st = ConexionSQLite.get_conexion().createStatement();
            System.out.println("Se han añadido " + st.executeUpdate(sqlCharacters) + " personajes");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addActs() {
        String sqlActs = "INSERT INTO acts (character_id, movie_id, minutes_in_movie, main_character, actor) VALUES (1, 1, 90, 1, 'Tom Holland'), (2, 2, 100, 1, 'Gal Gadot'), (8, 3, 85, 1, 'Ryan Reynolds'), (9, 4, 110, 1, 'Jason Momoa'), (5, 1, 45, 0, 'Margot Robbie')";
        try {
            Statement st = ConexionSQLite.get_conexion().createStatement();
            System.out.println("Se han añadido " + st.executeUpdate(sqlActs) + " actos");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}