package prueba;

import prueba.conexion.Conexion;
import prueba.objetos.Canciones;
import prueba.objetos.Grupos;
import prueba.objetos.Votos;
import prueba.servicios.Servicios;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (Conexion.get_conexion() != null) {
            System.out.println("Conexión establecida");
            programaPrincipal();
            Conexion.close_conexion();
        } else {
            System.out.println("No se pudo establecer la conexión");
        }
    }

    public static void programaPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("MENU DE OPCIONES");
            System.out.println("\t1- Listado de grupos\n\t2- Listado de canciones\n" +
                    "\t3- Número de canciones por grupo\n\t4- Canciones de un grupo\n" +
                    "\t5- Las 5 canciones más votadas\n\t6- Grupos sin canciones\n" +
                    "\t7- Los últimos 5 votos\n\t8- Eliminar canciones de un grupo\n" +
                    "\t9- Modificar datos de grupo\n\t0- Salir\n");
            System.out.print("Introduce una opción: ");
            int opcion = new Scanner(System.in).nextInt();
            switch (opcion) {
                case 1 -> listarGrupos();
                case 2 -> listarCanciones();
                case 3 -> numCancionesPorGrupo();
                case 4 -> cancionesDeGrupo();
                case 5 -> masVotadas();
                case 6 -> gruposSinCanciones();
                case 7 -> cincoUltimosVotos();
                case 8 -> eliminarCancionesGrupo();
                case 9 -> modificarGrupo();
                case 0 -> {
                    System.out.println("Saliendo...");
                    salir = true;
                }
                default -> System.out.println("Opción no válida");
            }
        }
    }

    public static void listarGrupos() {
        System.out.println("Listado de grupos\n");
        List<Grupos> grupos = Servicios.listarGrupos();
        if (grupos.isEmpty()) {
            System.out.println("No hay películas");
        } else {
            String cabecera = String.format("%-5s %-20s %-15s %-10s %-5s %-10s %-15s %-10s", "cod", "nombre", "localidad", "estilo", "grupo", "annograb", "fechaestreno", "compañia");
            String row = "-".repeat(cabecera.length()) + "\n";
            System.out.printf("%s\n%s", cabecera, row);
            grupos.forEach((v) -> System.out.println(v.formatInfo()));
            System.out.println(row);
        }
    }

    public static void listarCanciones() {
        System.out.println("Listado de canciones\n");
        Map<String, List<Canciones>> canciones = Servicios.listarCanciones();
        if (canciones.isEmpty()) {
            System.out.println("No hay canciones");
        } else {
            canciones.forEach((k, v) -> {
                System.out.println(k);
                v.forEach((c) -> System.out.println("\t" + c.getTitulo()));
            });
        }
    }

    public static void numCancionesPorGrupo() {
        System.out.println("Número de canciones por grupo");
        Map<String, Integer> canciones = Servicios.numeroCancionesPorGrupo();
        if (canciones.isEmpty()) {
            System.out.println("No hay canciones");
        } else {
            canciones.forEach((k, v) -> System.out.println(k + ": " + v));
        }
    }

    public static void cancionesDeGrupo() {
        System.out.println("Canciones de un grupo");
        System.out.println("Introduce el nombre del grupo: ");
        String grupo = new Scanner(System.in).nextLine();
        List<Canciones> canciones = Servicios.mostrarCancionesGrupo(grupo);
        if (canciones.isEmpty()) {
            System.out.println("No hay canciones");
        } else {
            String cabecera = String.format("%-40s %-15s %-5s", "titulo", "duracion", "total_votos");
            String row = "-".repeat(cabecera.length()) + "\n";
            System.out.printf("%s\n%s", cabecera, row);
            canciones.forEach((v) -> System.out.println(v.formatInfo()));
            System.out.println(row);
        }
    }

    public static void masVotadas() {
        System.out.println("Las 5 canciones más votadas");
        List<Canciones> canciones = Servicios.cancionesMasVotadas();
        if (canciones.isEmpty()) {
            System.out.println("No hay canciones");
        } else {
            String cabecera = String.format("%-40s %-15s %-5s", "titulo", "duracion", "total_votos");
            String row = "-".repeat(cabecera.length()) + "\n";
            System.out.printf("%s\n%s", cabecera, row);
            canciones.forEach((v) -> System.out.println(v.formatInfo()));
            System.out.println(row);
        }
    }

    public static void gruposSinCanciones() {
        System.out.println("Grupos sin canciones");
        List<Grupos> grupos = Servicios.gruposSinCancion();
        if (grupos.isEmpty()) {
            System.out.println("No hay grupos");
        } else {
            String cabecera = String.format("%-5s %-20s %-15s %-10s %-5s %-10s %-15s %-10s", "cod", "nombre", "localidad", "estilo", "grupo", "annograb", "fechaestreno", "compañia");
            String row = "-".repeat(cabecera.length()) + "\n";
            System.out.printf("%s\n%s", cabecera, row);
            grupos.forEach((v) -> System.out.println(v.formatInfo()));
            System.out.println(row);
        }
    }

    public static void cincoUltimosVotos() {
        System.out.println("Los últimos 5 votos");
        List<Votos> votos = Servicios.ultimosVotos();
        if (votos.isEmpty()) {
            System.out.println("No hay canciones");
        } else {
            String cabecera = String.format("%-15s %-10s %-20s %-20s", "usuario", "fecha", "cancion", "grupo");
            String row = "-".repeat(cabecera.length()) + "\n";
            System.out.printf("%s\n%s", cabecera, row);
            votos.forEach((v) -> System.out.println(v.formatInfo()));
            System.out.println(row);
        }
    }

    public static void eliminarCancionesGrupo() {
        System.out.println("Eliminar canciones de un grupo");
        System.out.println("Introduce el nombre del grupo: ");
        String grupo = new Scanner(System.in).nextLine();
        if (Servicios.eliminarGrupo(grupo)) {
            System.out.println("Grupo eliminado");
        } else {
            System.out.println("No se pudo eliminar el grupo");
        }
    }

    public static void modificarGrupo() {
        System.out.println("Modificar datos de grupo");
        System.out.println("Introduce el nombre del grupo: ");
        String nombre = new Scanner(System.in).nextLine();
        Grupos grupo = Servicios.listaGrupo(nombre);
        if (grupo == null) {
            System.out.println("No se encontró el grupo");
        } else {
            String cabecera = String.format("%-5s %-20s %-15s %-10s %-5s %-10s %-15s %-10s", "cod", "nombre", "localidad", "estilo", "grupo", "annograb", "fechaestreno", "compañia");
            String row = "-".repeat(cabecera.length()) + "\n";
            System.out.printf("%s\n%s", cabecera, row);
            System.out.println(grupo.formatInfo());
            System.out.println(row);
            System.out.println("Que datos quieres modificar?");
            System.out.println("\t1- Nombre\n\t2- Localidad\n\t3- Estilo\n\t4- Grupo\n\t5- Año grabación\n\t6- Fecha estreno\n\t7- Compañía\n");
            System.out.print("Introduce una opción: ");
            int opcion = new Scanner(System.in).nextInt();
            System.out.print("Introduce el nuevo valor: ");
            switch (opcion) {
                case 1 -> grupo.setNombre(new Scanner(System.in).nextLine());
                case 2 -> grupo.setLocalidad(new Scanner(System.in).nextLine());
                case 3 -> grupo.setEstilo(new Scanner(System.in).nextLine());
                case 4 -> grupo.setEsgrupo(new Scanner(System.in).nextBoolean());
                case 5 -> grupo.setAnnograb(new Scanner(System.in).nextInt());
                case 6 -> {
                    String fechaStr = new Scanner(System.in).nextLine();
                    try {
                        Date fecha = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr).getTime());
                        grupo.setFechaEstreno(fecha);
                    } catch (ParseException e) {
                        System.out.println("Formato de fecha incorrecto");
                    }
                }
                case 7 -> grupo.setCompañia(new Scanner(System.in).nextLine());
                default -> System.out.println("Opción no válida");
            }
            if (Servicios.modificarGrupo(grupo)) {
                System.out.println("Grupo modificado");
            } else {
                System.out.println("No se pudo modificar el grupo");
            }
        }
    }
}
