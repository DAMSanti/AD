package prueba.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_estudio")
public class Estudio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @ManyToMany(mappedBy = "estudios")
    private List<Empleado> empleados;


    public Estudio() {
    }

    public Estudio(String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Estudio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}