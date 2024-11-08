package prueba.models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmpleadoEstudioId implements Serializable {
    private long empleadoId;
    private long estudioId;

    public EmpleadoEstudioId() {
    }

    public EmpleadoEstudioId(long empleadoId, long estudioId) {
        this.empleadoId = empleadoId;
        this.estudioId = estudioId;
    }

    public long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public long getEstudioId() {
        return estudioId;
    }

    public void setEstudioId(long estudioId) {
        this.estudioId = estudioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoEstudioId that = (EmpleadoEstudioId) o;
        return empleadoId == that.empleadoId && estudioId == that.estudioId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(empleadoId, estudioId);
    }
}