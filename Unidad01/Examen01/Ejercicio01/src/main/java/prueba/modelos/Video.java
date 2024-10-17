package prueba.modelos;

import java.sql.Time;

public class Video {
    private int num_video;
    private String titulo;
    private String interprete;
    private Time duracion;
    private int anno;
    private int num_emisiones;
    private boolean disponible;

    public Video() {

    }

    public Video(int num_video, String titulo, String interprete, Time duracion, int anno, int num_emisiones, boolean disponible) {
        this.num_video = num_video;
        this.titulo = titulo;
        this.interprete = interprete;
        this.duracion = duracion;
        this.anno = anno;
        this.num_emisiones = num_emisiones;
        this.disponible = disponible;
    }

    public void setNum_video(int num_video) {
        this.num_video = num_video;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public void setNum_emisiones(int num_emisiones) {
        this.num_emisiones = num_emisiones;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getNum_video() {
        return num_video;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getInterprete() {
        return interprete;
    }

    public Time getDuracion() {
        return duracion;
    }

    public int getAnno() {
        return anno;
    }

    public int getNum_emisiones() {
        return num_emisiones;
    }

    public boolean isDisponible() {
        return disponible;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "num_video=" + num_video +
                ", titulo='" + titulo + '\'' +
                ", interprete='" + interprete + '\'' +
                ", duracion=" + duracion +
                ", anno=" + anno +
                ", num_emisiones=" + num_emisiones +
                ", disponible=" + disponible +
                '}';
    }
}
