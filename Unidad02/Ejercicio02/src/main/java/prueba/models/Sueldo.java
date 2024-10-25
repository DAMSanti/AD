package prueba.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

@Embeddable
@Table(name = "empleados")
public class Sueldo {
    @Column (nullable = false)
    private double salario;
    private double comision;

    public Sueldo() {
    }

    public Sueldo(double salario, double comision) {
        this.salario = salario;
        this.comision = comision;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }
}
