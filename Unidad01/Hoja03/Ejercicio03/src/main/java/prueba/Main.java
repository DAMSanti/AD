package prueba;

import prueba.conexion.Conexion;
import prueba.servicios.Servicios;

import java.util.Scanner;

public class Main {
    private static String usuario = null;

    public static void main(String[] args) {
        if (Conexion.get_conexion() != null) {
            System.out.println("Conexión establecida");
            if (logIn()) {
                System.out.println(usuario);
                System.out.println("Registro de Votos");
                System.out.print("Introduce numero de canción a votar: ");
                int cancion = new Scanner(System.in).nextInt();
                if (Servicios.vota(cancion, usuario)) {
                    System.out.println("Voto registrado");
                } else {
                    System.out.println("No se ha podido registrar el voto");
                }
            } else {
                System.out.println("Log in incorrecto");
            }
            Conexion.close_conexion();
        } else {
            System.out.println("No se pudo establecer la conexión");
        }
    }


    public static boolean logIn() {
        boolean conectado = false;
        System.out.println("Log in");
        System.out.print("User: ");
        String user = new Scanner(System.in).nextLine();
        System.out.print("Password: ");
        String password = new Scanner(System.in).nextLine();
        usuario = Servicios.logIn(user, password);
        if (user != null) {
            System.out.println("Bienvenido " + user);
            conectado = true;
        } else {
            System.out.println("Log in incorrecto");
        }
        return conectado;
    }
}