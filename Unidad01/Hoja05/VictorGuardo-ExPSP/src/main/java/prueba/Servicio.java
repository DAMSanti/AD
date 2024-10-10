package prueba;

import com.sun.security.jgss.GSSUtil;

import java.io.*;

public class Servicio {
    public static void ej1() {
        Runtime runtime = Runtime.getRuntime();//creamos padre
        Process process = null;
        String comando = "ipconfig"; // Comando a ejecutar
        try {
            process = runtime.exec(comando);//creamos proceso hijo
            InputStream is = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));//y las herramientas para leer lo que nos devuelve
            String linea;
            while ((linea = br.readLine()) != null)
                if (linea.contains("Puerta de enlace predeterminada") || linea.contains("Adaptador")) {
                    System.out.println(linea);
                }
            br.close();
        } catch (Exception ex) {
            System.err.println("Excepción de E/S!!");
            System.exit(-1);
        }
        int exitVal;
        try {
            exitVal = process.waitFor();//Comprobacion de la ejecucion del proceso en especifico
            System.out.println("Valor de salida: " + exitVal);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void ej2(String[] param) {
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            if (param.length > 0 && param.length < 100) {
                for (int i = 0; i < param.length; i++) {//recorremos el array que guarda los comandos ejecutamos y mostramos
                    System.out.println(param[i]);
                    process = runtime.exec(param[i]);
                    InputStream is = process.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String linea;
                    while ((linea = br.readLine()) != null)
                        System.out.println(linea);
                    br.close();
                    int exitVal;
                    exitVal = process.waitFor();//Comprobacion de la ejecucion del proceso en especifico
                    System.out.println("Valor de salida: " + exitVal);
                }

            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Excepción de E/S!!");
            System.exit(-1);
        }


    }

    public static void ej3() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(isr);
        // Obtenemos el stream de lectura de la entrada estandar
        // utilizamos un lector con Buffered, para no perder ningun dato
        String lineaTeclado = null; //Variable para ir leyendo lo lei¬do de teclado
        String comando = "java -jar D:\\Usuarios\\DAM2\\Downloads\\aleatorio.jar"; // Ejecutable del programa
        Runtime runtime = Runtime.getRuntime(); //Obtenemos proceso actual
        Process process = null;
        Process process2 = null;
        try {
            process = runtime.exec(comando); // Se crea proceso hijo y se le
            process2 = runtime.exec(comando);
            InputStream is = process.getInputStream(); // Conectamos la entrada
            InputStream is2 = process2.getInputStream();
            OutputStream os2 = process2.getOutputStream();
            BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(os2));
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            OutputStream os = process.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            int linea = 0;
            int linea2 = 0;
            int victoriasazul = 0;
            int victoriasrojo = 0;
            System.out.println("Introduce lineas de texto ( cuando un equipo llegue a 3 o introduzcas fin acabara)");
            while (((lineaTeclado = bf.readLine()) != null) && (!lineaTeclado.equals("fin")) && (victoriasazul < 3 && victoriasrojo < 3)) { //Mientras haya datos disponibles
                lineaTeclado = lineaTeclado + "\n"; // Añadimos un salto de línea atexto a enviar
                os.write(lineaTeclado.getBytes()); // Enviamos el texto al hijo
                os2.write(lineaTeclado.getBytes());
                os.flush();
                os2.flush();
                linea = Integer.parseInt(br.readLine()); // Leemos lo que el hijo envía al padre
                linea2 = Integer.parseInt(br2.readLine());
                System.out.println("Puntuacion azul: " + linea + " | Puntuacion roja: " + linea2); // Mostramos en pantalla lo que envió el hijo
                if (linea > linea2) {
                    System.out.println("Ha ganado el equipo azul");
                    victoriasazul++;
                } else if (linea2 > linea) {
                    System.out.println("Ha ganado el equipo rojo ");
                    victoriasrojo++;
                }
                System.out.println("Marcador - Equipo azul: " + victoriasazul + " | Equipo rojo: " + victoriasrojo);

            }
            System.out.println("Partido finalizado --> Equipo azul: " + victoriasazul + " | Equipo rojo: " + victoriasrojo);

            os.write("fin\n".getBytes()); // Enviamos al hijo la cadena fin para que también termine
            os2.write("fin\n".getBytes());
            os.flush();
            os2.flush();
            br.close();
            br2.close();

            int exitVal;

            exitVal = process.waitFor(); // Esperamos a que termine el hijo yobtenemos su valor de salida

            exitVal += exitVal > 0 ? exitVal : process2.waitFor();
            System.out.println("Proceso finalizado con valor de salida: " + exitVal);
        } catch (IOException ex) {
            System.err.println("Se ha producido un error de E/S. Su descripcion es: ");
            System.err.println(ex.toString());
        } catch (Exception ex) {
            System.err.println("Se ha producido un error. Su descripcion es: ");
            System.err.println(ex.toString());
        }

    }
}
