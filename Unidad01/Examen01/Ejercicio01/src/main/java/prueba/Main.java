package prueba;


import prueba.conexion.ConexionMySQL;
import prueba.modelos.Solicitud;
import prueba.modelos.Usuario;
import prueba.modelos.Video;
import prueba.services.Services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import static prueba.services.Services.listarSolicitudesUsuario;
import static prueba.services.Services.listarSolicitudesVideo;

public class Main {
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        if (ConexionMySQL.get_conexion() != null) {
            System.out.println("Conexión establecida");

            menuOpciones();

            ConexionMySQL.close_conexion();
        } else {
            System.out.println("No se pudo establecer la conexión");
        }
    }


    public static void menuOpciones() {
        System.out.println("GESTION DE VIDEOS PUBLICADOS Y SOLICITADOS\n\t1.- Importar datos a sqlite\n\t2.- Modificar total de peticione de un usuario\n\t3.- Listar usuarios solicitantes de video\n\t4.- Eliminar solicitudes de usuario\n\t5.- Videos con mas solicitudes que otro\n\t6.- Emitir Video\n\t0. SALIR");
        switch (teclado.nextLine()) {
            case "1" -> migrarDatos();
            case "2" -> modificarPeticiones();
            case "3" -> solicitudesVideo();
            case "4" -> usuariosSolicitantes();

            default -> System.out.println("Opción no válida");
        }
    }

    public static void migrarDatos() {
        if (Services.transpasaVideos(Services.listarVideos()) > 0 && Services.transpasaEmitidos(Services.listarEmitidos()) > 0) {
            System.out.println("Migración realizada con éxito");
        } else {
            System.out.println("Ha habido un problema migrando los datos.");
        }
    }

    public static void modificarPeticiones() {
        System.out.print("Introduzca el numero de usuario que quiere actualizar: ");
        Usuario usu = Services.usuarioPorID(teclado.nextInt());
        teclado.nextLine();
        if (usu != null) {
            System.out.println(usu.toString());
            Services.actualizaPeticiones(usu);
        }
        teclado.nextLine();

    }

    public static void solicitudesVideo() {
        System.out.print("Introduzca el titulo de un video: ");
        Video vid = Services.videosPorTitulo(teclado.nextLine());
        if (vid != null) {
            List<Solicitud> solicitudes = Services.listarSolicitudesVideo(vid);
            for (Solicitud soli : solicitudes) {
                System.out.println("Datos: " + soli.getNombre() + " " + soli.getApellidos() );
            }
        }
        teclado.nextLine();

    }

    public static void usuariosSolicitantes() {
        System.out.print("Introduzca el id de un usuario: ");
        Usuario usu = Services.usuarioPorID(teclado.nextInt());
        if (usu != null) {
            List<Solicitud> solicitudes = Services.listarSolicitudesUsuario(usu);
            for (Solicitud soli : solicitudes) {
                System.out.println("Datos: " + soli.getTitulo() + " " + soli.getFecha() + " " + soli.getHora());
            }
        } else {
            System.out.println("El usuario introducido no existe.");
        }


    }

    public static void videoSolicitadosDeUsuarios() {

    }
}
