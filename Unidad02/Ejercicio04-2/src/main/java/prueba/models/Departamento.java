package prueba.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Departamento {
    @Id
    private long id;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(length = 50)
    private String localidad;
    @OneToMany(mappedBy = "departamento")
    private List<Empleado> empleados;

    public Departamento() {
    }

    public Departamento(long id, String nombre, String localidad) {
        this.id = id;
        this.nombre = nombre;
        this.localidad = localidad;
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

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}