package org.example;

import org.example.objetos.*;
import org.example.services.Servicios;
import java.util.Scanner;


public class Main {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
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
                case 1 -> addMovies();
                case 2 -> addActs();
                case 3 -> addCharacters();
                case 4 -> modificarOrigen();
                case 5 -> modificarAno();
                case 6 -> eliminarActuacion();
                case 7 -> eliminarPelicula();
                case 8 -> modificarProductora();
                case 9 -> {
                    System.out.println("Saliendo...");
                    salir = true;
                }
                default -> System.out.println("Opción no válida");
            }
        }
    }

    public static void addMovies() {
        System.out.println("Introduce el título de la película: ");
        String titulo = teclado.nextLine();
        System.out.println("Introduce la duración de la película: ");
        int duracion = teclado.nextInt();
        teclado.nextLine();
        Movies movie = new Movies(titulo, duracion, 0, "");
        if (Servicios.insertarPelicula(movie)!= 1) {
            System.out.println("No se ha podido añadir la película");
        } else {
            System.out.println("Película añadida");
        }
    }

    public static void addActs() {
        System.out.println("Introduce la id de la pelicula: ");
        int id_pelicula = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Introduce la id del personaje: ");
        int id_personaje = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Introduce el actor: ");
        String actor = teclado.nextLine();
        System.out.println("¿Es personaje principal? (1/0): ");
        int principal = teclado.nextInt();
        teclado.nextLine();
        Acts act = new Acts(id_personaje, id_pelicula, 0, principal, actor);
        if (Servicios.insertarActuaciones(act) != 1) {
            System.out.println("No se ha podido añadir la actuación");
        } else {
            System.out.println("Actuación añadida");
        }
    }

    public static void addCharacters() {
        System.out.println("Introduce el nombre del personaje: ");
        String nombrePersonaje = teclado.nextLine();
        System.out.println("Introduce los poderes del personaje: ");
        String poderesPersonaje = teclado.nextLine();
        System.out.println("Franquicia del superheroe: ");
        String franquicia = teclado.nextLine();
        System.out.println("Origen del personaje: ");
        String origenPersonaje = teclado.nextLine();
        System.out.println("¿Es un héroe? (1/0): ");
        int heroe = teclado.nextInt();
        teclado.nextLine();
        Characters character = new Characters(nombrePersonaje, poderesPersonaje, franquicia, origenPersonaje, heroe);
        if (Servicios.insertarPersonaje(character) != 1) {
            System.out.println("No se ha podido añadir el personaje");
        } else {
            System.out.println("Personaje añadido");
        }
    }

    public static void modificarOrigen() {
        System.out.println("Introduce el nombre del personaje: ");
        String nombrePersonaje = teclado.nextLine();
        System.out.println("Introduce el nuevo origen del personaje: ");
        String origen = teclado.nextLine();
        Characters character = new Characters(nombrePersonaje, "", "", origen, 0);
        if (Servicios.modificarOrigen(nombrePersonaje, character) != 1) {
            System.out.println("No se ha podido modificar el origen del personaje");
        } else {
            System.out.println("Origen modificado");
        }
    }

    public static void modificarAno() {
        System.out.println("Introduce la id de la pelicula: ");
        int idPelicula = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Introduce el nuevo año de estreno: ");
        int fechaEstreno = teclado.nextInt();
        teclado.nextLine();
        Movies movie = new Movies("", 0, fechaEstreno, "");
        if (Servicios.modificarEstreno(idPelicula, movie) != 1) {
            System.out.println("No se ha podido modificar la película");
        } else {
            System.out.println("Pelicula modificada");
        }
    }

    public static void eliminarActuacion() {
        System.out.println("Introduce la id del personaje: ");
        int idPersonaje = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Introduce la id de la pelicula: ");
        int idPelicula = teclado.nextInt();
        teclado.nextLine();
        if (Servicios.eliminarActuacion(idPersonaje, idPelicula) != 1) {
            System.out.println("No se ha podido eliminar la actuación");
        } else {
            System.out.println("Actuación eliminada");
        }
    }

    public static void eliminarPelicula() {
        System.out.println("Introduce el titulo de la pelicula: ");
        String tituloPelicula = teclado.nextLine();
        if (Servicios.eliminarPelicula(tituloPelicula) != 1) {
            System.out.println("No se ha podido eliminar la película");
        } else {
            System.out.println("Película eliminada");
        }
    }

    public static void modificarProductora() {
        System.out.println("Introduce el nombre de la productora: ");
        String nombreProductora = teclado.nextLine();
        System.out.println("Introduce el nuevo nombre de la productora: ");
        String nuevaProductora = teclado.nextLine();
        if (Servicios.modificarProductora(nombreProductora, nuevaProductora) != 1) {
            System.out.println("No se ha podido modificar la productora");
        } else {
            System.out.println("Productora modificada");
        }
    }
}