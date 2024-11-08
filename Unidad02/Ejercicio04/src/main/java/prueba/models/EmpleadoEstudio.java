package prueba.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class EmpleadoEstudio {
    @EmbeddedId
    private EmpleadoEstudioId id;

    @ManyToOne
    @MapsId("empleadoId")
    private Empleado empleado;

    @ManyToOne
    @MapsId("estudioId")
    private Estudio estudio;

    private LocalDate fechaFinalizacion;

    public EmpleadoEstudio() {
    }

    public EmpleadoEstudio(Empleado empleado, Estudio estudio, LocalDate fechaFinalizacion) {
        this.empleado = empleado;
        this.estudio = estudio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.id = new EmpleadoEstudioId(empleado.getId(), estudio.getId());
    }

    public EmpleadoEstudioId getId() {
        return id;
    }

    public void setId(EmpleadoEstudioId id) {
        this.id = id;
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

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }
}