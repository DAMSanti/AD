package org.example.modelos;

public class Respuesta {
    private int num_preg;
    private int num_resp;
    private String texto_preg;

    public Respuesta() {
    }

    public Respuesta(int num_preg, int num_resp, String texto_preg) {
        this.num_preg = num_preg;
        this.num_resp = num_resp;
        this.texto_preg = texto_preg;
    }

    public int getNum_preg() {
        return num_preg;
    }

    public void setNum_preg(int num_preg) {
        this.num_preg = num_preg;
    }

    public int getNum_resp() {
        return num_resp;
    }

    public void setNum_resp(int num_resp) {
        this.num_resp = num_resp;
    }

    public String getTexto_preg() {
        return texto_preg;
    }

    public void setTexto_preg(String texto_preg) {
        this.texto_preg = texto_preg;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "num_preg=" + num_preg +
                ", num_resp=" + num_resp +
                ", texto_preg='" + texto_preg + '\'' +
                '}';
    }
}
