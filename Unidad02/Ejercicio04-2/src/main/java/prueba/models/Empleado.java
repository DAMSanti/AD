package prueba.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 50)
    private String nombre;
    private String oficio;
    private LocalDate fechaAlta;
    @Embedded
    private Sueldo sueldo;
    @ManyToOne
    private Departamento departamento;
    @ManyToMany
    @JoinTable(
            name = "empleado_estudio",
            joinColumns = @JoinColumn(name = "empleado_id"),
            inverseJoinColumns = @JoinColumn(name = "estudio_id")
    )
    private List<Estudio> estudios;

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

    public void setEstudios(List<Estudio> estudio) {
        this.estudios = estudio;
    }
}
