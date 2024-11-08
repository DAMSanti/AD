package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Empleado {
    @Id
    @GeneratedValue
    private long id;
    private String nombre;
    private String oficio;
    private LocalDate fechaAlta;
    private double salario;

    public Empleado() {
    }

    public Empleado(String nombre, String oficio, LocalDate fechaAlta, double salario) {
        this.nombre = nombre;
        this.oficio = oficio;
        this.fechaAlta = fechaAlta;
        this.salario = salario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

}
