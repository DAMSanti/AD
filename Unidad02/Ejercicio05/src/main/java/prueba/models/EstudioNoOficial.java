package prueba.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("EmpleadoEstudio")
public class EstudioNoOficial extends Estudio {
    private String academia;
    private int numeroHoras;

    public EstudioNoOficial() {
    }

    public EstudioNoOficial(String nombre, String academia, int numeroHoras) {
        super(nombre);
        this.academia = academia;
        this.numeroHoras = numeroHoras;
    }

    public String getAcademia() {
        return academia;
    }

    public void setAcademia(String academia) {
        this.academia = academia;
    }

    public int getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(int numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    @Override
    public String toString() {
        return "EstudioNoOficial{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", academia='" + academia + '\'' +
                ", numeroHoras=" + numeroHoras +
                '}';
    }
}