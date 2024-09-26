package prueba;

import prueba.Servicios.Services;
import prueba.conexion.ConexionSQLite;
import prueba.objetos.Pelicula;
import prueba.objetos.Personaje;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
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
                        List<Pelicula> peliculas = Services.obtenerPeliculas();
                        if (peliculas.isEmpty()) {
                            System.out.println("No hay películas");
                        } else {
                            String cabecera = String.format("%-10s %-20s %-10s %-10s %-15s", "ID", "Titulo", "Duracion", "Año", "Productora");
                            String row = "-".repeat(cabecera.length()) + "\n";
                            System.out.printf("%s\n%s", cabecera, row);
                            peliculas.forEach((v) -> System.out.println(v.formatInfo()));
                            System.out.println(row);
                        }
                    }
                    case 2 -> {
                        System.out.println("PERSONAJES");
                        List<Personaje> personajes = Services.obtenerPersonajes();
                        if (personajes.isEmpty()) {
                            System.out.println("No hay personajes");
                        } else {
                            String cabecera = String.format("%-4s %-20s %-50s %-15s %-20s %-10s", "ID", "Nombre", "Poderes", "Compañia", "Origen", "Es heroe");
                            String row = "-".repeat(cabecera.length()) + "\n";
                            System.out.printf("%s\n%s", cabecera, row);
                            personajes.forEach((v) -> System.out.println(v.formatoInfo()));
                            System.out.println(row);
                        }
                    }
                    case 3 -> {
                        System.out.println("Introduce la ID del personaje que quieres inspeccionar: ");
                        int id = teclado.nextInt();
                        Personaje personaje = Services.obtenerPersonajeID(id);
                        System.out.println("PERSONAJE");
                        if (personaje == null) {
                            System.out.println("No hay personaje con esa ID");
                        } else {
                            String cabecera = String.format("%-4s %-20s %-50s %-15s %-20s %-10s", "ID", "Nombre", "Poderes", "Compañia", "Origen", "Es heroe");
                            String row = "-".repeat(cabecera.length()) + "\n";
                            System.out.printf("%s\n%s", cabecera, row);
                            System.out.println(personaje.formatoInfo());
                            System.out.println(row);
                        }
                    }
                    case 4 -> {
                        System.out.println("ACTUACIONES");
                        List<Map<String, Object>> actuaciones = Services.obtenerPersonajesPelicula();
                        if (actuaciones.isEmpty()) {
                            System.out.println("No hay actuaciones");
                        } else {
                            String cabecera = String.format("%-30s %-10s %-30s %-30s", "Titulo", "Año", "Nombre", "Actor");
                            String row = "-".repeat(cabecera.length()) + "\n";
                            System.out.printf("%s\n%s", cabecera, row);
                            actuaciones.forEach((v) -> System.out.printf("%-30s %-10s %-30s %-30s\n", v.get("titulo"), v.get("año"), v.get("nombre"), v.get("actor")));
                            System.out.println(row);
                        }
                    }
                    case 5 -> {
                        System.out.println("PELÍCULAS");
                        List<Pelicula> peliculas = Services.obtenerPeliculasSinProductora();
                        if (peliculas.isEmpty()) {
                            System.out.println("No hay películas");
                        } else {
                            String cabecera = String.format("%-10s %-20s %-10s %-10s %-15s", "ID", "Titulo", "Duracion", "Año", "Productora");
                            String row = "-".repeat(cabecera.length()) + "\n";
                            System.out.printf("%s\n%s", cabecera, row);
                            peliculas.forEach((v) -> System.out.println(v.formatInfo()));
                            System.out.println(row);
                        }
                    }
                    case 6 -> {
                        System.out.println("ACTORES POR PELICULA");
                        List<Map<String, Object>> actores = Services.obtenerPersonajesPorPelicula();
                        if (actores.isEmpty()) {
                            System.out.println("No hay actuaciones");
                        } else {
                            String cabecera = String.format("%-30s %-10s", "Titulo", "Nº Actores");
                            String row = "-".repeat(cabecera.length()) + "\n";
                            System.out.printf("%s\n%s", cabecera, row);
                            actores.forEach((v) -> System.out.printf("%-30s %-10s\n", v.get("titulo"), v.get("actores")));
                            System.out.println(row);
                        }
                    }
                    case 7 -> {
                        System.out.println("PELÍCULAS");
                        List<Pelicula> peliculas = Services.obtenerPeliculaMasVieja();
                        if (peliculas.isEmpty()) {
                            System.out.println("No hay películas");
                        } else {
                            String cabecera = String.format("%-10s %-20s %-10s %-10s %-15s", "ID", "Titulo", "Duracion", "Año", "Productora");
                            String row = "-".repeat(cabecera.length()) + "\n";
                            System.out.printf("%s\n%s", cabecera, row);
                            peliculas.forEach((v) -> System.out.println(v.formatInfo()));
                            System.out.println(row);
                        }
                    }
                    case 8 -> {
                        System.out.println("Introduce el nombre de un actor del que quieras saber la filmografía: ");
                        teclado = new Scanner(System.in);
                        String actor = teclado.nextLine();
                        List<Pelicula> peliculas = Services.obtenerPeliculasPorActor(actor);
                        System.out.println("PELÍCULAS");
                        if (peliculas.isEmpty()) {
                            System.out.println("No hay películas");
                        } else {
                            String cabecera = String.format("%-10s %-20s %-10s %-10s %-15s", "ID", "Titulo", "Duracion", "Año", "Productora");
                            String row = "-".repeat(cabecera.length()) + "\n";
                            System.out.printf("%s\n%s", cabecera, row);
                            peliculas.forEach((v) -> System.out.println(v.formatInfo()));
                            System.out.println(row);
                        }
                    }
                    case 9 -> {
                        List<Pelicula> peliculas = Services.obtenerPeliculasSinPersonajes();
                        System.out.println("PELÍCULAS");
                        if (peliculas.isEmpty()) {
                            System.out.println("No hay películas");
                        } else {
                            String cabecera = String.format("%-10s %-20s %-10s %-10s %-15s", "ID", "Titulo", "Duracion", "Año", "Productora");
                            String row = "-".repeat(cabecera.length()) + "\n";
                            System.out.printf("%s\n%s", cabecera, row);
                            peliculas.forEach((v) -> System.out.println(v.formatInfo()));
                            System.out.println(row);
                        }
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



    public static Map<String, Pelicula> rsToPelicula(ResultSet rs) {
        if (rs == null) {
            System.out.println("No hay películas");
        }
        Pelicula pelicula;
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


}