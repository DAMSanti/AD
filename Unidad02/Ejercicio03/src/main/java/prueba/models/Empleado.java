package prueba.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    private long id;
    @Column(nullable = false, length = 50)
    private String nombre;
    private String oficio;
    private LocalDate fechaAlta;
    @Embedded
    private Sueldo sueldo;
    @ManyToOne
    private Departamento departamento;

    public Empleado() {
    }

    public Empleado(String nombre, String oficio, LocalDate fechaAlta, Sueldo sueldo, Departamento departamento) {
        this.nombre = nombre;
        this.oficio = oficio;
        this.fechaAlta = fechaAlta;
        this.sueldo = sueldo;
        this.departamento = departamento;
    }

    public long getId() {
        return id;
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
}
