package com.example.demoSpring.Model;

import java.sql.Time;

public class Cancion {
    private int numCancion;
    private Grupo grupo;
    private Time duracion;
    private String titulo;
    private int total_votos;

    public Cancion() {
    }

    public Cancion(int numCancion, Grupo grupo, Time duracion, String titulo, int total_votos) {
        this.numCancion = numCancion;
        this.grupo = grupo;
        this.duracion = duracion;
        this.titulo = titulo;
        this.total_votos = total_votos;
    }

    public int getNumCancion() {
        return numCancion;
    }

    public void setNumCancion(int numCancion) {
        this.numCancion = numCancion;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTotal_votos() {
        return total_votos;
    }

    public void setTotal_votos(int total_votos) {
        this.total_votos = total_votos;
    }

    @Override
    public String toString() {
        return "Cancion{" + "numCancion=" + numCancion + ", grupo=" + grupo + ", duracion=" + duracion + ", titulo=" + titulo + ", total_votos=" + total_votos + '}';
    }
}
