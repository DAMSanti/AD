package prueba.objetos;

public class Pelicula {
    private int id;
    private String titulo;
    private int duracion;
    private int año;
    private String productora;

    public Pelicula(int id, String titulo, int duracion, int año, String productora) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.año = año;
        this.productora = productora;
    }

    public Pelicula(String titulo, int duracion, int año, String productora) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.año = año;
        this.productora = productora;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getAño() {
        return año;
    }

    public String getProductora() {
        return productora;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", duracion=" + duracion +
                ", año=" + año +
                ", productora='" + productora + '\'' +
                '}';
    }

    public String formatInfo() {
        return String.format("%-10d %-20s %-10s %-10s %-15s", id, titulo.length() > 20 ? titulo.substring(0, 17) + "..." : titulo , duracion, año, productora);
    }
}
