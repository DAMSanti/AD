package prueba.modelos;

import java.sql.Date;
import java.sql.Time;

public class Solicitud {
    private int num;
    private int num_usu;
    private int video;
    private Date fecha;
    private Time hora;
    private String via;
    private String titulo;
    private String nombre;
    private String apellidos;

    public Solicitud() {
    }

    public Solicitud(int num, int num_usu, int video, Date fecha, Time hora, String via) {
        this.num = num;
        this.num_usu = num_usu;
        this.video = video;
        this.fecha = fecha;
        this.hora = hora;
        this.via = via;
    }

    public int getNum() {
        return num;
    }

    public int getNum_usu() {
        return num_usu;
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

    public String getVia() {
        return via;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setNum_usu(int num_usu) {
        this.num_usu = num_usu;
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

    public void setVia(String via) {
        this.via = via;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "num=" + num +
                ", num_usu=" + num_usu +
                ", video=" + video +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", via='" + via + '\'' +
                '}';
    }
}
