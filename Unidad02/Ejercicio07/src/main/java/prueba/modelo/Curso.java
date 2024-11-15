package prueba.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "curso")
@NamedQueries({
        @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
        @NamedQuery(name = "Curso.findById", query = "SELECT c FROM Curso c WHERE c.id = :id")
})
public class Curso {
    @Id
    @SequenceGenerator(name = "curso_id_gen", sequenceName = "alumno2_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false, length = 4)
    private String id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "curso")
    private List<Alumno> alumnos;

    @ManyToOne
    @JoinColumn(name = "tutor_id", nullable = false)
    private Profesor profesor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

}