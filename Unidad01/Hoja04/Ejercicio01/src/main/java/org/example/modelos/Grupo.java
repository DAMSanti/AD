package org.example.modelos;

import java.sql.Date;

public class Grupo {
    private int codgrupo;
    private String nombre;
    private String localidad;
    private String estilo;
    private boolean esGrupo;
    private int annograb;
    private Date fechaEstreno;
    private String compàñia;

    public Grupo() {
    }

    public Grupo(int codgrupo, String nombre, String localidad, String estilo, boolean esGrupo, int annograb, Date fechaEstreno, String compàñia) {
        this.codgrupo = codgrupo;
        this.nombre = nombre;
        this.localidad = localidad;
        this.estilo = estilo;
        this.esGrupo = esGrupo;
        this.annograb = annograb;
        this.fechaEstreno = fechaEstreno;
        this.compàñia = compàñia;
    }

    public int getCodgrupo() {
        return codgrupo;
    }

    public void setCodgrupo(int codgrupo) {
        this.codgrupo = codgrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public boolean isEsGrupo() {
        return esGrupo;
    }

    public void setEsGrupo(boolean esGrupo) {
        this.esGrupo = esGrupo;
    }

    public int getAnnograb() {
        return annograb;
    }

    public void setAnnograb(int annograb) {
        this.annograb = annograb;
    }

    public Date getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(Date fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getCompàñia() {
        return compàñia;
    }

    public void setCompàñia(String compàñia) {
        this.compàñia = compàñia;
    }

    @Override
    public String toString() {
        return "Grupo{" + "codgrupo=" + codgrupo + ", nombre=" + nombre + ", localidad=" + localidad + ", estilo=" + estilo + ", esGrupo=" + esGrupo + ", annograb=" + annograb + ", fechaEstreno=" + fechaEstreno + ", compàñia=" + compàñia + '}';
    }
}
