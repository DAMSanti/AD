package prueba;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Teclado {
    public static int nota(){
        Scanner teclado=new Scanner(System.in);
        boolean aux=false;
        int num=0;
        while(!aux){
            try{
                System.out.print("Introduce la nota ");
                num=teclado.nextInt();
                if(num<0 || num>10){
                    throw new Exception("Nota fuera de rango");
                }
                aux=true;

            }catch( java.util.InputMismatchException miss){
                teclado.next();
                System.out.println("No se ha introducido un entero");
            }catch (Exception e){
                teclado.next();
                System.out.println(e.toString());
            }
        }
        return num;
    }






    public static String string(String texto){
        System.out.println(texto);
        return new Scanner (System.in).nextLine();
    }

    public static int entero(String texto){
        System.out.print(texto);
        int aux=0;
        try{
            aux= new Scanner (System.in).nextInt();
        }catch(InputMismatchException a){
            System.out.println("No se ha introducido un entero");
        }

        return aux;
    }

    public static double doble(String texto){
        System.out.print(texto);
        double aux=0;
        try{
            aux= new Scanner (System.in).nextDouble();
        }catch(InputMismatchException a){
            System.out.println("No se ha introducido un numero");
        }
        return aux;
    }

    public static boolean boleano(String cadena) {
        boolean sol = false, fin = false;
        Scanner teclado = new Scanner(System.in);
        String x;
        do {
            try {
                System.out.println(cadena);
                System.out.println("Si/No");
                x = teclado.nextLine();
                if (x.equalsIgnoreCase("Si") || x.equalsIgnoreCase("S")) {
                    sol = true;
                    fin = true;
                } else if (x.equalsIgnoreCase("No") || x.equalsIgnoreCase("N")) {
                    sol = false;
                    fin = true;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Caracter no reconocido");
            }
        } while (!fin);
        return sol;
    }

    public static LocalDate fecha(String texto){
        System.out.print(texto);
        int dia = entero("Introduce el dia: ");
        int mes = entero("Introduce el mes: ");
        int anio = entero("Introduce el año: ");
        LocalDate aux=LocalDate.now();
        try{
            aux=LocalDate.of(anio,mes,dia);
        }catch(DateTimeException a){
            System.out.println("Los datos introducidos no son compatibles con el formato de fecha");
        }
        return aux;
    }


//TELEFONO COMPROBADO UNICAMENTE POR CANTIDAD DE NUMEROS

    public static String telefono(String texto){
        String aux = string(texto);
        while ( !aux.matches("[0-9]{9}")){
            System.out.println("Valor introducido invalido, prueve otra vez");
            aux= string(texto);
        }

        return aux;
    }





//INTRO DNI

    private static boolean letraDni(String dni){
        int numDni = Integer.parseInt(dni.substring(0,8));
        boolean valido=false;
        int resto;
        char[] letras= {'T','R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        resto = numDni %23;
        if (letras[resto]==dni.charAt(8)){
            valido=true;
        }
        return valido;
    }



    public static String generarDNI() {
        int numDNI = (int) (Math.random() * 99999999);

        String[] letrasDNI = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
        int indiceLetra = numDNI % 23;
        String letraDNI = letrasDNI[indiceLetra];

        String dni = String.format("%08d%s", numDNI, letraDNI);

        return dni;
    }
}
