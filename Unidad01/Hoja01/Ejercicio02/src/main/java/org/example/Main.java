package org.example;

import org.example.conexion.ConexionSQLite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("MENU DE PELICULAS");
            System.out.println("\t1 Añadir Película\n" +
                    "\t2 Añadir actuación\n" +
                    "\t3 Añadir personaje\n" +
                    "\t4 Modificar origen de personaje\n" +
                    "\t5 Modificar año de película\n" +
                    "\t6 Eliminar actuación\n" +
                    "\t7 Eliminar película\n" +
                    "\t8 Modificar productora\n" +
                    "\t9 Salir");
            System.out.print("\nIntroduce una opción: ");
            switch (teclado.nextInt()) {
                case 1 -> {addMovies();}
                case 2 -> {addActs();}
                case 3 -> {addCharacters();}
                case 4 -> {modificarOrigen();}
                case 5 -> {modificarAño();}
                case 6 -> {eliminarActuacion();}
                case 7 -> {eliminarPelicula();}
                case 8 -> {modificarProductora();}
                case 9 -> {
                    System.out.println("Saliendo...");
                    salir = true;
                }
                default -> System.out.println("Opción no válida");
            };
        }
    }

    public static void addMovies() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el título de la película: ");
        String titulo = teclado.nextLine();
        teclado = new Scanner(System.in);
        System.out.println("Introduce la duración de la película: ");
        int duracion = teclado.nextInt();
        String sql = "INSERT INTO movies (title, duration) VALUES (?, ?)";
        try (Connection conn = ConexionSQLite.get_conexion();
             PreparedStatement pstatement = conn.prepareStatement(sql)) {
            pstatement.setString(1, titulo);
            pstatement.setInt(2, duracion);
            if (pstatement.executeUpdate() < 1) {
                throw new SQLException("No se ha podido añadir la película");
            } else {
                System.out.println("Película añadida");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addActs() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce la id de la pelicula: ");
        int id_pelicula = teclado.nextInt();
        teclado = new Scanner(System.in);
        System.out.println("Introduce la id del personaje: ");
        int id_personaje = teclado.nextInt();
        teclado = new Scanner(System.in);
        System.out.println("Introduce el actor: ");
        String actor = teclado.nextLine();
        teclado = new Scanner(System.in);
        System.out.println("¿Es personaje principal? (1/0): ");
        int principal = teclado.nextInt();
        teclado = new Scanner(System.in);
        String sql = "INSERT INTO acts (character_id, movie_id,  main_character, actor) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionSQLite.get_conexion();
             PreparedStatement pstatement = conn.prepareStatement(sql)) {
            pstatement.setInt(1, id_personaje);
            pstatement.setInt(2, id_pelicula);
            pstatement.setInt(3, principal);
            pstatement.setString(4, actor);
            if (pstatement.executeUpdate() < 1) {
                throw new SQLException("No se ha podido añadir la actuación, comprueba la id de pelicula o la id de personaje");
            } else {
                System.out.println("Actuación añadida");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addCharacters() {
        System.out.println("Añadir personaje");
    }

    public static void modificarOrigen() {
        System.out.println("Modificar origen de personaje");
    }

    public static void modificarAño() {
        System.out.println("Modificar año de película");
    }

    public static void eliminarActuacion() {
        System.out.println("Eliminar actuación");
    }

    public static void eliminarPelicula() {
        System.out.println("Eliminar película");
    }

    public static void modificarProductora() {
        System.out.println("Modificar productora");
    }
}