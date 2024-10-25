package prueba.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, length = 45)
    private String nombre;
    @Column(length = 30)
    private String oficio;
    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;
    @Embedded
    private Sueldo sueldo;

    public Empleado() {
    }

    public Empleado(String nombre, String oficio, LocalDate fechaAlta, Sueldo sueldo) {
        this.nombre = nombre;
        this.oficio = oficio;
        this.fechaAlta = fechaAlta;
        this.sueldo = sueldo;
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
