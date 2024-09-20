package org.example.objetos;

public class Movies {
    private int id;
    private String title;
    private int duration;
    private int year;
    private String productora;

    public Movies(int id, String title, int duration, int year, String productora) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.year = year;
        this.productora = productora;
    }

    public Movies(String title, int duration, int year, String productora) {
        this.title = title;
        this.duration = duration;
        this.year = year;
        this.productora = productora;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public int getYear() {
        return year;
    }

    public String getProductora() {
        return productora;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", year=" + year +
                ", productora='" + productora + '\'' +
                '}';
    }
}
