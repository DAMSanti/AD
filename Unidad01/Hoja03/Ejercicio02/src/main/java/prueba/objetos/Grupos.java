package prueba.objetos;

import java.sql.Date;

public class Grupos {
    private int codgrupo;
    private String nombre;
    private String localidad;
    private String estilo;
    private boolean esgrupo;
    private int annograb;
    private Date fechaEstreno;
    private String compañia;

    public Grupos(int codgrupo, String nombre, String localidad, String estilo, boolean esgrupo, int annograb, Date fechaEstreno, String compañia) {
        this.codgrupo = codgrupo;
        this.nombre = nombre;
        this.localidad = localidad;
        this.estilo = estilo;
        this.esgrupo = esgrupo;
        this.annograb = annograb;
        this.fechaEstreno = fechaEstreno;
        this.compañia = compañia;
    }

    public Grupos(String nombre, String localidad, String estilo, boolean esgrupo, int annograb, Date fechaEstreno, String compañia) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.estilo = estilo;
        this.esgrupo = esgrupo;
        this.annograb = annograb;
        this.fechaEstreno = fechaEstreno;
        this.compañia = compañia;
    }

    public int getCodgrupo() {
        return codgrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getEstilo() {
        return estilo;
    }

    public boolean getEsgrupo() {
        return esgrupo;
    }

    public int getAnnograb() {
        return annograb;
    }

    public Date getFechaEstreno() {
        return fechaEstreno;
    }

    public String getCompañia() {
        return compañia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodgrupo(int codgrupo) {
        this.codgrupo = codgrupo;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public boolean isEsgrupo() {
        return esgrupo;
    }

    public void setEsgrupo(boolean esgrupo) {
        this.esgrupo = esgrupo;
    }

    public void setFechaEstreno(Date fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public void setAnnograb(int annograb) {
        this.annograb = annograb;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
    }

    @Override
    public String toString() {
        return "Grupos{" +
                "codgrupo=" + codgrupo +
                ", nombre='" + nombre + '\'' +
                ", localidad='" + localidad + '\'' +
                ", estilo='" + estilo + '\'' +
                ", esgrupo=" + esgrupo +
                ", annograb=" + annograb +
                ", fechaEstreno=" + fechaEstreno +
                ", compañia='" + compañia + '\'' +
                '}';
    }

    public String formatInfo() {
        return String.format("%-5d %-20s %-15s %-10s %-5s %-10d %-15s %-10s", codgrupo, nombre , localidad, estilo.length() > 10 ? estilo.substring(0, 7) + "..." : estilo , esgrupo ? "Sí" : "No", annograb, fechaEstreno, compañia);
    }
}
