package prueba;

import prueba.conexion.ConexionSQLite;
import prueba.objetos.Pelicula;
import prueba.objetos.Personaje;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (ConexionSQLite.get_conexion() != null) {
            System.out.println("Conexión establecida");
            Scanner teclado = new Scanner(System.in);
            boolean salir = false;
            while (!salir) {
                System.out.println("INFORMACIÓN CINE\n" +
                        "\t1- Obtener peliculas\n"
                        + "\t2- Obtener personajes\n"
                        + "\t3- Obtener personaje\n"
                        + "\t4- Obtener personajes de pelicula\n"
                        + "\t5- Obtener peliculas sin productora\n"
                        + "\t6- Obtener numero de personajes por pelicula\n"
                        + "\t7- Obtener pelicula más antigua\n"
                        + "\t8- Obtener películas por actor\n"
                        + "\t9- Obtener películas sin personajes cargados\n"
                        + "\t0- Salir");
                int opcion = teclado.nextInt();
                switch (opcion) {
                    case 1 -> {
                        System.out.println("PELÍCULAS");
                        Map<String, Pelicula> peliculas = rsToPelicula(obtenerPeliculas());
                        peliculas.forEach((k, v) -> System.out.println(v.formatInfo()));
                    }
                    case 2 -> {

                    }
                    case 3 -> {

                    }
                    case 4 -> {

                    }
                    case 5 -> {

                    }
                    case 6 -> {

                    }
                    case 7 -> {

                    }
                    case 8 -> {

                    }
                    case 9 -> {

                    }
                    case 0 -> salir = true;
                    default -> System.out.println("Opción no válida");
                }
            }
            ConexionSQLite.close_conexion();
        } else {
            System.out.println("ERROR al realizar la conexión");
        }
    }

    public static ResultSet obtenerPeliculas() {
        PreparedStatement st=null;
        ResultSet rs = null;
        int n= 0;
        if (ConexionSQLite.get_conexion()!=null){
                String sql = "SELECT * FROM movies";
                try{
                    st = ConexionSQLite.get_conexion().prepareStatement(sql);
                    rs = st.executeQuery();
                } catch (SQLException e){
                    e.printStackTrace();
                } finally {
                    cerrarStatement(st);
                }
            }
        return rs;
    }

    public static Map<String, Pelicula> rsToPelicula(ResultSet rs) {
        Pelicula pelicula = null;
        Map<String, Pelicula> peliculas = new HashMap<>();
        try {
            while (rs.next()) {
                pelicula = new Pelicula(rs.getInt("id"), rs.getString("title"), rs.getInt("duration"), rs.getInt("year"), rs.getString("productora"));
                peliculas.put(rs.getString("title"), pelicula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peliculas;
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