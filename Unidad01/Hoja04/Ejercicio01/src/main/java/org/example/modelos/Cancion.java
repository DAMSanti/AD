package org.example.modelos;

import java.sql.Time;

public class Cancion {
    private int numCancion;
    private int grupo;
    private Time duracion;
    private String titulo;
    private int votos;

    public Cancion() {
    }

    public Cancion(int numCancion, int grupo, Time duracion, String titulo, int votos) {
        this.numCancion = numCancion;
        this.grupo = grupo;
        this.duracion = duracion;
        this.titulo = titulo;
        this.votos = votos;
    }

    public int getNumCancion() {
        return numCancion;
    }

    public void setNumCancion(int numCancion) {
        this.numCancion = numCancion;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
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

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    @Override
    public String toString() {
        return "Cancion{" + "numCancion=" + numCancion + ", grupo=" + grupo + ", duracion=" + duracion + ", titulo=" + titulo + ", votos=" + votos + '}';
    }
}
