package org.example;

import org.example.modelos.Grupo;
import org.example.services.Servicios;

public class Main {
    public static void main(String[] args) {
        Grupo grupo = new Grupo();
        grupo.setCodgrupo(2);
        Servicios.listarCancionesGrupo(grupo);
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