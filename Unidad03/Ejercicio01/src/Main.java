import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean salir = false;
        Scanner teclado = new Scanner(System.in);
        String ruta;
        Path path;
        while (!salir) {
            System.out.println("1.- Comprobar si es directorio\n2.- Obtener ficheros de un directorio\n3.- Obtener propiedades de un fichero\n" +
                    "4.- Obtener ruta del directorio actual\n5.- Eliminar fichero\n6.- Mover fichero\n7.- Renombrar fichero\n8.- Copipar fichero\n" +
                    "9.- Ver/Crear Directorio\n0.- Salir");
            switch (Integer.parseInt(teclado.nextLine())) {
                case 1:
                    System.out.println("Introduce la ruta del directorio");
                    ruta = teclado.nextLine();
                    path = Paths.get(ruta);
                    if (Files.isDirectory(path)) {
                        System.out.println("Es directorio");
                    } else {
                        System.out.println("No es directorio");
                    }
                    break;
                case 2:
                    System.out.println("Introduce la ruta del directorio");
                    ruta = teclado.nextLine();
                    path = Paths.get(ruta);
                    try {
                        Files.list(path).filter(Files::isRegularFile).forEach(System.out::println);
                    } catch (Exception e) {
                        System.out.println("No se ha podido listar el directorio");
                    }
                    break;
                case 3:
                    System.out.println("Introduce la ruta del fichero");
                    ruta = teclado.nextLine();
                    path = Paths.get(ruta);
                    try {
                        System.out.println("Propiedades del fichero: " + Files.readAttributes(path, "*"));
                    } catch (Exception e) {
                        System.out.println("No se ha podido obtener las propiedades del fichero");
                    }
                    break;
                case 4:
                    System.out.println("Ruta del directorio actual: " + Paths.get("").toAbsolutePath());
                    break;
                case 5:
                    System.out.println("Introduce la ruta del fichero");
                    ruta = teclado.nextLine();
                    path = Paths.get(ruta);
                    try {
                        Files.delete(path);
                        System.out.println("Fichero eliminado");
                    } catch (Exception e) {
                        System.out.println("No se ha podido eliminar el fichero");
                    }
                    break;
                case 6:
                    System.out.println("Introduce la ruta del fichero");
                    ruta = teclado.next();
                    path = Paths.get(ruta);
                    System.out.println("Introduce la ruta de destino");
                    String rutaDestino = teclado.next();
                    Path pathDestino = Paths.get(rutaDestino);
                    try {
                        Files.move(path, pathDestino);
                        System.out.println("Fichero movido");
                    } catch (Exception e) {
                        System.out.println("No se ha podido mover el fichero");
                    }
                    break;
                case 7:
                    System.out.println("Introduce la ruta del fichero");
                    ruta = teclado.next();
                    path = Paths.get(ruta);
                    System.out.println("Introduce el nuevo nombre");
                    String nuevoNombre = teclado.next();
                    Path nuevoPath = path.resolveSibling(nuevoNombre);
                    try {
                        Files.move(path, nuevoPath, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Fichero renombrado");
                    } catch (Exception e) {
                        System.out.println("No se ha podido renombrar el fichero");
                    }
                    break;
                case 8:
                    System.out.println("Introduce la ruta del fichero");
                    ruta = teclado.nextLine();
                    path = Paths.get(ruta);
                    System.out.println("Introduce la ruta de destino");
                    String destino = teclado.nextLine();
                    pathDestino = Paths.get(destino);
                    try {
                        Files.copy(path, pathDestino, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Fichero copiado");
                    } catch (Exception e) {
                        System.out.println("No se ha podido copiar el fichero");
                    }
                    break;
                case 9:
                    System.out.println("Introduce la ruta del directorio");
                    ruta = teclado.nextLine();
                    path = Paths.get(ruta);
                    try {
                        Files.createDirectories(path);
                        System.out.println("Directorio creado");
                    } catch (Exception e) {
                        System.out.println("No se ha podido crear el directorio");
                    }
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }

    }
}