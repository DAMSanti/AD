package prueba;

import prueba.Services.Services;
import prueba.conexion.Conection;
import prueba.modelos.Pregunta;
import prueba.modelos.Respuesta;

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



/*

EJERCICIO 1
Realizar un programa inicia y mantiene la misma conexión a la base de datos durante
toda su ejecución.
El programa presenta inicialmente los datos de todos los personajes y películas
cargados.

A continuación, presenta un menú con las opciones:

1.- Añadir personaje
2.- Añadir película
3.- Añadir actuación
4.- Eliminar actuación
0.- Salir

Para cada caso de añadir, se piden los datos necesarios.
Al añadir personaje o película, no se permitirá añadir si respectivamente ya existe el
nombre o el título. Las validaciones para hacerlo, se deben hacer sobre la lista de
películas y personajes que se habrán obtenido para escribir su listado.

Para añadir la actuación, se validará primero sobre las listas de personajes y películas
que el id del personaje y de la película existen. Al añadir ya a la tabla, se deberá detectar
el error de que la actuación del personaje en la película ya esté registrada.

Tras realizar cualquiera de las acciones, se volverán a mostrar los datos de todos los
personajes y de todas las películas.


 */