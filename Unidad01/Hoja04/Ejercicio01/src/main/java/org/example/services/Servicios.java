package org.example.services;

import org.example.conexion.Conexion;
import org.example.modelos.Grupo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Servicios {

    public static void listarCancionesGrupo(Grupo g) {
        Scanner teclado = new Scanner(System.in);
        PreparedStatement st = null;
        ResultSet rs = null;
        if (Conexion.get_conexion() != null) {
            try {
                String sql = "SELECT numcancion, titulo, total_votos FROM canciones WHERE grupo = ?";
                st = Conexion.get_conexion().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                st.setInt(1, g.getCodgrupo());
                rs = st.executeQuery();
                while (rs.next()) {
                    System.out.println("Canción: " + rs.getString("titulo") + " Votos: " + rs.getInt("total_votos"));
                    System.out.println("Desea incrementar el numero de votos de esta cancion? [S/N]");
                    char respuesta = teclado.next().charAt(0);
                    if (respuesta == 'S' || respuesta == 's') {
                        rs.updateInt("total_votos", rs.getInt("total_votos") + 1);
                        rs.updateRow();
                    } else if (respuesta == 'N' || respuesta == 'n') {
                        System.out.println("Votos no incrementados");
                    } else {
                        System.out.println("Respuesta no válida");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al ejecutar la consulta");
            } finally {
                Conexion.close_conexion();
            }
        }

    }
}




/*

EJERCICIO 1
Realiza un programa que pide por teclado el número de un grupo y a continuación va
mostrando en pantalla los datos de las canciones del grupo.
De cada canción pregunta si se desea incrementar el total de votos. Si se responde que
si, se incrementa en uno el total de votos.
Lo más adecuado es usar un ResultSet editable.
• Hay que construir un PreparedStatement o un Statement que devuelva
ResultSet editables y que consulte el número de canción, título y total de votos
de cada canción del grupo.
• Para cada fila del ResultSet deberás mostrar el contenido de la fila (titulo de la
canción y votos), preguntar si se quiere incrementar los votos y en ese caso
sumar uno al número de votos y actualizar el número de votos y la fila
correspondiente

 */