package prueba.models;

import java.io.Serializable;
import java.util.Objects;

public class EmpleadoEstudioId implements Serializable {
    private long empleadoId;
    private long estudioId;

    public EmpleadoEstudioId() {
    }

    public EmpleadoEstudioId(long empleadoId, long estudioId) {
        this.empleadoId = empleadoId;
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