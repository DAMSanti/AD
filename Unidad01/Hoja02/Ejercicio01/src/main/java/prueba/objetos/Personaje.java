package prueba.objetos;

public class Personaje {
    private int id;
    private String nombre;
    private String poderes;
    private String empresa;
    private String origen;
    private int esHeroe;

    public Personaje(int id, String nombre, String poderes, String empresa, String origen, int esHeroe) {
        this.id = id;
        this.nombre = nombre;
        this.poderes = poderes;
        this.empresa = empresa;
        this.origen = origen;
        this.esHeroe = esHeroe;
    }

    public Personaje(String nombre, String poderes, String empresa, String origen, int esHeroe) {
        this.nombre = nombre;
        this.poderes = poderes;
        this.empresa = empresa;
        this.origen = origen;
        this.esHeroe = esHeroe;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPoderes() {
        return poderes;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getOrigen() {
        return origen;
    }

    public int getEsHeroe() {
        return esHeroe;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", poderes='" + poderes + '\'' +
                ", empresa='" + empresa + '\'' +
                ", origen='" + origen + '\'' +
                ", esHeroe=" + esHeroe +
                '}';
    }

    public String formatoInfo() {
        return String.format("%-4d %-20s %-50s %-15s %-20s %-10s", id, nombre, poderes.length()>50?poderes.substring(0, 47) + "...":poderes, empresa, origen, esHeroe==1?"Superheroe":"Villano");
    }
}
