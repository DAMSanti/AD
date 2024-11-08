package prueba.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@IdClass(EmpleadoEstudioId.class)
public class EmpleadoEstudio {
    @Id
    private long empleadoId;
    @Id
    private long estudioId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "empleadoId", insertable = false, updatable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "estudioId", insertable = false, updatable = false)
    private Estudio estudio;

    public EmpleadoEstudio() {
    }

    public EmpleadoEstudio(long empleadoId, long estudioId, LocalDate fechaInicio, LocalDate fechaFin) {
        this.empleadoId = empleadoId;
        this.estudioId = estudioId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public long getEmpleadoId() {
        return empleadoId;
    }

    public long getEstudioId() {
        return estudioId;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    @Override
    public String toString() {
        return "EmpleadoEstudio{" +
                "empleadoId=" + empleadoId +
                ", estudioId=" + estudioId +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }

}