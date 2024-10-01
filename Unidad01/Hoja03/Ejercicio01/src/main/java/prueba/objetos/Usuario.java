package prueba.objetos;

import java.sql.Date;

public class Usuario {
    private String user;
    private String contraseña;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private int numVotos;

    public Usuario(String user, String contraseña, String nombre, String apellidos, Date fechaNacimiento, int numVotos) {
        this.user = user;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.numVotos = numVotos;
    }

    public Usuario(String user, String contraseña, String nombre, String apellidos, Date fechaNacimiento) {
        this.user = user;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.numVotos = 0;
    }

    public String getUser() {
        return user;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getNumVotos() {
        return numVotos;
    }

    public void setNumVotos(int numVotos) {
        this.numVotos = numVotos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "user='" + user + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", numVotos=" + numVotos +
                '}';
    }
}
