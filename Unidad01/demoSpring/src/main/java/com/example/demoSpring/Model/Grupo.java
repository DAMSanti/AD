package com.example.demoSpring.Model;

import java.time.LocalDate;

public class Grupo {
    private int codGrupo;
    private String nombre;
    private String localidad;
    private String estilo;
    private boolean esGrupo;
    private Integer annoGrab;
    private LocalDate fechaEstreno;
    private String compania;

    public Grupo() {
    }

    public Grupo(int codGrupo, String nombre, String localidad, String estilo, boolean esGrupo, Integer annoGrab, LocalDate fechaEstreno, String compania) {
        this.codGrupo = codGrupo;
        this.nombre = nombre;
        this.localidad = localidad;
        this.estilo = estilo;
        this.esGrupo = esGrupo;
        this.annoGrab = annoGrab;
        this.fechaEstreno = fechaEstreno;
        this.compania = compania;
    }

    public int getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(int codGrupo) {
        this.codGrupo = codGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public boolean isEsGrupo() {
        return esGrupo;
    }

    public void setEsGrupo(boolean esGrupo) {
        this.esGrupo = esGrupo;
    }

    public Integer getAnnoGrab() {
        return annoGrab;
    }

    public void setAnnoGrab(Integer annoGrab) {
        this.annoGrab = annoGrab;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "codGrupo=" + codGrupo +
                ", nombre='" + nombre + '\'' +
                ", localidad='" + localidad + '\'' +
                ", estilo='" + estilo + '\'' +
                ", esGrupo=" + esGrupo +
                ", annoGrab=" + annoGrab +
                ", fechaEstreno=" + fechaEstreno +
                ", compania='" + compania + '\'' +
                '}';
    }
}
