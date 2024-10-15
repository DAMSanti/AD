package prueba.modelos;

public class Pregunta {
    private int num_preg;
    private String enunciado;
    private int tipo;

    public Pregunta() {
    }

    public Pregunta(int num_preg, String enunciado, int tipo) {
        this.num_preg = num_preg;
        this.enunciado = enunciado;
        this.tipo = tipo;
    }

    public int getNum_preg() {
        return num_preg;
    }

    public void setNum_preg(int num_preg) {
        this.num_preg = num_preg;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "num_preg=" + num_preg +
                ", enunciado='" + enunciado + '\'' +
                '}';
    }
}
