package prueba;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Servicio.ej1();
        String[] array=new String[2];
        for (int i=0;i<array.length;i++){
            array[i]=Teclado.string("Introduzca un comando");
        }
        Servicio.ej2(array);
        Servicio.ej3();
    }
}