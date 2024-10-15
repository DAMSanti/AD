package org.example;

import org.example.conexion.Conexion;
import static org.example.servicios.Servicios.datos10UltimosVotos;

public class Main {
    public static void main(String[] args) {
        if (Conexion.get_conexion() != null) {
            datos10UltimosVotos();
        } else {
            System.out.println("No se pudo establecer la conexión");
        }
    }
}