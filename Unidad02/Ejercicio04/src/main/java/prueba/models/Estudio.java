package prueba.models;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;
import java.util.List;

@Entity
public class Estudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    @OneToMany(mappedBy = "estudio")
    private List<EmpleadoEstudio> empleados;
    @NaturalId
    @Column(nullable = false, length = 5)
    private String codEstudio;

    public Estudio() {
    }

    public Estudio(String nombre, String codEstudio) {
        this.nombre = nombre;
        this.codEstudio = codEstudio;
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

    public String getCodEstudio() {
        return codEstudio;
    }

    public void setCodEstudio(String codEstudio) {
        this.codEstudio = codEstudio;
    }

    public List<EmpleadoEstudio> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EmpleadoEstudio> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "Estudio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", codEstudio='" + codEstudio + '\'' +
                '}';
    }
}
