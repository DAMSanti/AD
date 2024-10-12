package org.example;

import org.example.servicios.Servicios;

public class Main {
    public static void main(String[] args) {
        Servicios.siguienteJornada(Servicios.ultimaJornada());
        Servicios.partidosUltimaJornada(Servicios.ultimaJornada());
    }
}