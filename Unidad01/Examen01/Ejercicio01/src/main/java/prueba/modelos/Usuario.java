package prueba.modelos;

import java.sql.Date;

public class Usuario {
    private int num_usu;
    private String usuario;
    private String contra;
    private String nombre;
    private String apellidos;
    private Date fechanac;
    private int num_peticiones;

    public Usuario() {
    }

    public Usuario(int num_usu, String usuario, String contra, String nombre, String apellidos, Date fechanac, int num_peticiones) {
        this.num_usu = num_usu;
        this.usuario = usuario;
        this.contra = contra;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechanac = fechanac;
        this.num_peticiones = num_peticiones;
    }

    public int getNum_usu() {
        return num_usu;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContra() {
        return contra;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public int getNum_peticiones() {
        return num_peticiones;
    }

    public void setNum_usu(int num_usu) {
        this.num_usu = num_usu;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public void setNum_peticiones(int num_peticiones) {
        this.num_peticiones = num_peticiones;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "num_usu=" + num_usu +
                ", usuario='" + usuario + '\'' +
                ", contra='" + contra + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechanac=" + fechanac +
                ", num_peticiones=" + num_peticiones +
                '}';
    }
}
