package prueba.modelos;

import java.sql.Date;
import java.sql.Time;

public class Emitido {
    private int num_emision;
    private int video;
    private Date fecha;
    private Time hora;
    private int num_peticiones;

    public Emitido() {
    }

    public Emitido(int num_emision, int video, Date fecha, Time hora, int num_peticiones) {
        this.num_emision = num_emision;
        this.video = video;
        this.fecha = fecha;
        this.hora = hora;
        this.num_peticiones = num_peticiones;
    }

    public int getNum_emision() {
        return num_emision;
    }

    public int getVideo() {
        return video;
    }

    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    public int getNum_peticiones() {
        return num_peticiones;
    }

    public void setNum_emision(int num_emision) {
        this.num_emision = num_emision;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setNum_peticiones(int num_peticiones) {
        this.num_peticiones = num_peticiones;
    }

    @Override
    public String toString() {
        return "Emitido{" +
                "num_emision=" + num_emision +
                ", video=" + video +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", num_peticiones=" + num_peticiones +
                '}';
    }
}
