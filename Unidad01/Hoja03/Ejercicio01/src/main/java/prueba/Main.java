package prueba;

import prueba.conexion.Conexion;
import prueba.objetos.Usuario;
import prueba.servicios.Servicios;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (Conexion.get_conexion() != null) {
            System.out.println("Conexión establecida");
            recogeDatos();
            Conexion.close_conexion();
        } else {
            System.out.println("No se pudo establecer la conexión");
        }
    }

    public static void recogeDatos() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el nombre de usuario: ");
        String user = teclado.nextLine();
        System.out.print("Introduce la contraseña: ");
        String contraseña = teclado.nextLine();
        System.out.print("Introduce el nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Introduce los apellidos: ");
        String apellidos = teclado.nextLine();
        System.out.print("Introduce la fecha de nacimiento (yyyy-mm-dd): ");
        String fechaNacimiento = teclado.nextLine();
        Usuario usuario = new Usuario(user, contraseña, nombre, apellidos, Date.valueOf(fechaNacimiento));
        Servicios.addUser(usuario);

    }
}


/*
EJERCICIO 1
Realiza un programa que permite añadir un usuario a la tabla usuarios con los datos
recogidos por teclado.
No es necesario validar los datos introducidos.
Si que hay que comprobar si al hacer la inserción se produce excepción por clave
duplicada debido a que el user ya exista. El código de error MySQL por clave duplicada
es 1062. El método de SQLEXception que devuelve el código de error que ha generado
la excepción es getErrorCode().

 */