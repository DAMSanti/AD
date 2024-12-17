import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String palabra;
        String urlFichero = "D:\\Usuarios\\DAM227\\Desktop\\fichero_tarea3.json";
        Scanner teclado = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("GESTION DE FICHEROS\n1.- Leer Contenido1\n2,. Leer Contenido2\n3.- Reemplazar texto" +
                    "\n4.- Modificar linea\n5.- Eliminar linea\n6.- Añadir linea\n7.- Salir");
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1:
                    teclado.nextLine();
                    System.out.println("Introduce la palabra a buscar:");
                    palabra = teclado.nextLine();
                    leerFicheroYBuscarPalabra(urlFichero, palabra);
                    break;
                case 2:
                    teclado.nextLine();
                    System.out.println("Introduce la palabra a buscar:");
                    palabra = teclado.nextLine();
                    leerFicheroYBuscarPalabra2(urlFichero, palabra);
                    break;
                case 3:
                    teclado.nextLine();
                    System.out.println("Introduce el texto a reemplazar:");
                    String textoViejo = teclado.nextLine();
                    System.out.println("Introduce el nuevo texto:");
                    String textoNuevo = teclado.nextLine();
                    reemplazarTextoEnFichero(urlFichero, textoViejo, textoNuevo);
                    break;
                case 4:
                    System.out.println("Introduce el número de línea a modificar:");
                    int numeroLinea = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("Introduce el nuevo texto para la línea:");
                    String nuevoTexto = teclado.nextLine();
                    modificarLineaEnFichero(urlFichero, numeroLinea, nuevoTexto);
                    break;
                case 5:
                    System.out.println("Introduce el número de línea a eliminar:");
                    int lineaAEliminar = teclado.nextInt();
                    eliminarLineaEnFichero(urlFichero, lineaAEliminar);
                    break;
                case 6:
                    teclado.nextLine();
                    System.out.println("Introduce el texto a añadir:");
                    String nuevoTextoLinea = teclado.nextLine();
                    anadirLineaEnFichero(urlFichero, nuevoTextoLinea);
                    break;
                case 7:
                    System.out.println("Salir");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while (opcion != 7);

    }

    public static void leerFicheroYBuscarPalabra(String rutaFichero, String palabra) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(rutaFichero));
            for (String linea : lineas) {
                if (linea.contains(palabra)) {
                    System.out.println(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    public static void leerFicheroYBuscarPalabra2(String rutaFichero, String palabra) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(rutaFichero));
            for (int i = 0; i < lineas.size(); i++) {
                String linea = lineas.get(i);
                if (linea.contains(palabra)) {
                    System.out.println((i + 1) + ": " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    public static void reemplazarTextoEnFichero(String rutaFichero, String textoViejo, String textoNuevo) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(rutaFichero));
            for (int i = 0; i < lineas.size(); i++) {
                lineas.set(i, lineas.get(i).replace(textoViejo, textoNuevo));
            }
            Files.write(Paths.get(rutaFichero), lineas, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Texto reemplazado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al leer o escribir el fichero: " + e.getMessage());
        }
    }

    public static void modificarLineaEnFichero(String rutaFichero, int numeroLinea, String nuevoTexto) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(rutaFichero));
            if (numeroLinea > 0 && numeroLinea <= lineas.size()) {
                System.out.println("Contenido original de la línea " + numeroLinea + ": " + lineas.get(numeroLinea - 1));
                lineas.set(numeroLinea - 1, nuevoTexto);
                Files.write(Paths.get(rutaFichero), lineas, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Línea modificada exitosamente.");
            } else {
                System.out.println("Número de línea no válido.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer o escribir el fichero: " + e.getMessage());
        }
    }

    public static void eliminarLineaEnFichero(String rutaFichero, int numeroLinea) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(rutaFichero));
            if (numeroLinea > 0 && numeroLinea <= lineas.size()) {
                System.out.println("Contenido de la línea " + numeroLinea + ": " + lineas.get(numeroLinea - 1));
                lineas.remove(numeroLinea - 1);
                Files.write(Paths.get(rutaFichero), lineas, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Línea eliminada exitosamente.");
            } else {
                System.out.println("Número de línea no válido.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer o escribir el fichero: " + e.getMessage());
        }
    }

    public static void anadirLineaEnFichero(String rutaFichero, String nuevoTexto) {
        try {
            Files.write(Paths.get(rutaFichero), (nuevoTexto + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            System.out.println("Línea añadida exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }
}