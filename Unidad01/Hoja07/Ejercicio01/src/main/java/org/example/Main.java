package org.example;

import org.example.conection.Conection;
import org.example.modelos.Pregunta;
import org.example.modelos.Respuesta;
import org.example.services.Services;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (Conection.get_conexion() != null) {
            System.out.println("Conexión establecida");
            muestraPreguntas(introduceTipo());
            Conection.close_conexion();
        } else {
            System.out.println("No se pudo establecer la conexión");
        }
    }

    public static int introduceTipo() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el tipo de pregunta: ");
        return teclado.nextInt();
    }

    public static void muestraPreguntas(int tipo) {
        Scanner teclado = new Scanner(System.in);
        List<Pregunta> preguntas = Services.obtenerPreguntas(tipo);
        for (Pregunta pregunta : preguntas) {
            System.out.println(pregunta.getNum_preg() + ". " + pregunta.getEnunciado());
            List<Respuesta> respuestas = Services.obtenerRespuestas(pregunta);
            for (Respuesta respuesta : respuestas) {
                if (respuesta.getNum_preg() == pregunta.getNum_preg()) {
                    System.out.println("\t" + respuesta.getNum_resp() + ". " + respuesta.getTexto_preg());
                }
            }
            System.out.println("PULSA ENTER PARA CONTINUAR");
            teclado.nextLine();
        }
    }
}


