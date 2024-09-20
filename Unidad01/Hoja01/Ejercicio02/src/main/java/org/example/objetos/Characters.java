package org.example.objetos;

public class Characters {
    private int id;
    private String name;
    private String powers;
    private String company;
    private String origin;
    private int isHeroe;

    public Characters(int id, String name, String powers, String company, String origin, int isHeroe) {
        this.id = id;
        this.name = name;
        this.powers = powers;
        this.company = company;
        this.origin = origin;
        this.isHeroe = isHeroe;
    }

    public Characters(String name, String powers, String company, String origin, int isHeroe) {
        this.name = name;
        this.powers = powers;
        this.company = company;
        this.origin = origin;
        this.isHeroe = isHeroe;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPowers() {
        return powers;
    }

    public String getCompany() {
        return company;
    }

    public String getOrigin() {
        return origin;
    }

    public int getIsHeroe() {
        return isHeroe;
    }

    @Override
    public String toString() {
        return "Characters{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", powers='" + powers + '\'' +
                ", company='" + company + '\'' +
                ", origin='" + origin + '\'' +
                ", isHeroe=" + isHeroe +
                '}';
    }
}
