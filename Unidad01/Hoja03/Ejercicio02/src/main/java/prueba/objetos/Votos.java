package prueba.objetos;

import java.sql.Date;
import java.sql.Time;

public class Votos {
    private String usuario;
    private Date fecha;
    private String cancion;
    private String grupo;

    public Votos(String usuario, Date fecha, String cancion, String grupo) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.cancion = cancion;
        this.grupo = grupo;
    }

    public String getUsuario() {
        return usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCancion() {
        return cancion;
    }

    public String getGrupo() {
        return grupo;
    }

    @Override
    public String toString() {
        return "Votos{" +
                "usuario='" + usuario + '\'' +
                ", fecha=" + fecha +
                ", cancion=" + cancion +
                '}';
    }

    public String formatInfo() {
        return String.format("%-15s %-10s %-20s %-20s", usuario, fecha , cancion.length() > 20 ? cancion.substring(0, 17) + "..." : cancion , grupo.length() > 20 ? grupo.substring(0, 17) + "..." : grupo);
    }
}
