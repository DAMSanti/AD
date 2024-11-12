package prueba.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("EmpleadoEstudio")
public class EstudioOficial extends Estudio {
    private String centro;
    private String realDecreto;

    public EstudioOficial() {
    }

    public EstudioOficial(String nombre, String centro, String realDecreto) {
        super(nombre);
        this.centro = centro;
        this.realDecreto = realDecreto;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getRealDecreto() {
        return realDecreto;
    }

    public void setRealDecreto(String realDecreto) {
        this.realDecreto = realDecreto;
    }

    @Override
    public String toString() {
        return "EstudioOficial{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", centro='" + centro + '\'' +
                ", realDecreto='" + realDecreto + '\'' +
                '}';
    }
}