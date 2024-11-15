package prueba.modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "alumno")
@NamedQueries({
        @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
        @NamedQuery(name = "Alumno.findByCurso", query = "SELECT a FROM Alumno a WHERE a.curso = :id")
})
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alumno_id_gen")
    @SequenceGenerator(name = "alumno_id_gen", sequenceName = "alumno2_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "nota_media", precision = 4, scale = 2)
    private BigDecimal notaMedia;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    public Curso getCurso() { return curso; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(BigDecimal notaMedia) {
        this.notaMedia = notaMedia;
    }

}