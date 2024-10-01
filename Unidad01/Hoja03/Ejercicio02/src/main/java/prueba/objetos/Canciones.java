package prueba.objetos;

import java.sql.Time;

public class Canciones {
    private int numCancion;
    private int grupo;
    private Time duracion;
    private String titulo;
    private int total_votos;

    public Canciones(int numCancion, int grupo, Time duracion, String titulo, int total_votos) {
        this.numCancion = numCancion;
        this.grupo = grupo;
        this.duracion = duracion;
        this.titulo = titulo;
        this.total_votos = total_votos;
    }

    public Canciones(int grupo, Time duracion, String titulo) {
        this.grupo = grupo;
        this.duracion = duracion;
        this.titulo = titulo;
    }

    public int getNumCancion() {
        return numCancion;
    }

    public int getGrupo() {
        return grupo;
    }

    public Time getDuracion() {
        return duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getTotal_votos() {
        return total_votos;
    }

    @Override
    public String toString() {
        return "Canciones{" +
                "numCancion=" + numCancion +
                ", grupo=" + grupo +
                ", duracion=" + duracion +
                ", titulo='" + titulo + '\'' +
                ", total_votos=" + total_votos +
                '}';
    }

    public String formatInfo() {
        return String.format("%-40s %-15s %-5d", titulo.length() > 30 ? titulo.substring(0, 27) + "..." : titulo , duracion, total_votos);
    }

}