import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce la ruta del fichero a gestionar: ");
        String ruta = teclado.nextLine();
        Path path = Paths.get(ruta);
        if (!path.toFile().exists()) {
            System.out.println("El fichero no existe");
            return;
        }
        System.out.println("GESTION DE FICHEROS\n1.- Leer valores de bytes\n2.- Leer valores como caracter\n3.- Buscar byte\n4.- Modificar byte\n5.- Añadir byte\n6.- Eliminar byte\n7.- Salir");
        int opcion = teclado.nextInt();
        switch (opcion) {
            case 1 -> leerBytes(path); // Leer valores de bytes
            case 2 -> leerCaracteres(path); // Leer valores como caracter
            case 3 -> buscarByte(path); // Buscar byte
            case 4 -> modificarByte(path); // Modificar byte
            case 5 -> anadirByte(path); // Añadir byte
            case 6 -> eliminarByte(path); // Eliminar byte
        }

    }

    private static void leerBytes(Path path) {
        try {
            byte[] bytes = Files.readAllBytes(path);
            for (byte b : bytes) {
                System.out.printf("%02x ", b);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    private static void leerCaracteres(Path path) {
        try {
            byte[] bytes = Files.readAllBytes(path);
            for (byte b : bytes) {
                System.out.print((char) b);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    private static void buscarByte(Path path) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el byte a buscar: ");
        byte byteBuscar = teclado.nextByte();
        try {
            byte[] bytes = Files.readAllBytes(path);
            int count = 0;
            for (byte b : bytes) {
                if (b == byteBuscar) {
                    count++;
                }
            }
            if (count > 0) {
                System.out.println("El byte " + byteBuscar + " se encuentra " + count + " veces en el fichero");
            } else {
                System.out.println("El byte " + byteBuscar + " no se encuentra en el fichero");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    private static void modificarByte(Path path) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce la posición del byte a modificar: ");
        int posicion = teclado.nextInt();
        System.out.println("Introduce el nuevo valor del byte: ");
        byte nuevoValor = teclado.nextByte();
        try {
            byte[] bytes = Files.readAllBytes(path);
            if (posicion < 0 || posicion >= bytes.length) {
                System.out.println("La posición no es válida");
                return;
            }
            bytes[posicion] = nuevoValor;
            Files.write(path, bytes);
            System.out.println("Byte modificado correctamente");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    private static void anadirByte(Path path) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce la posición donde añadir el byte: ");
        int posicion = teclado.nextInt();
        System.out.println("Introduce el valor del byte a añadir: ");
        byte valor = teclado.nextByte();
        try {
            byte[] bytes = Files.readAllBytes(path);
            if (posicion < 0 || posicion > bytes.length) {
                System.out.println("La posición no es válida");
                return;
            }
            byte[] nuevoArray = new byte[bytes.length + 1];
            for (int i = 0; i < posicion; i++) {
                nuevoArray[i] = bytes[i];
            }
            nuevoArray[posicion] = valor;
            for (int i = posicion; i < bytes.length; i++) {
                nuevoArray[i + 1] = bytes[i];
            }
            Files.write(path, nuevoArray);
            System.out.println("Byte añadido correctamente");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    private static void eliminarByte(Path path) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce la posición del byte a eliminar: ");
        int posicion = teclado.nextInt();
        try {
            byte[] bytes = Files.readAllBytes(path);
            if (posicion < 0 || posicion >= bytes.length) {
                System.out.println("La posición no es válida");
                return;
            }
            byte[] nuevoArray = new byte[bytes.length - 1];
            for (int i = 0; i < posicion; i++) {
                nuevoArray[i] = bytes[i];
            }
            for (int i = posicion + 1; i < bytes.length; i++) {
                nuevoArray[i - 1] = bytes[i];
            }
            Files.write(path, nuevoArray);
            System.out.println("Byte eliminado correctamente");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}