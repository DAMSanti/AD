package org.example.services;

import org.example.conection.Conection;
import org.example.modelos.Pregunta;
import org.example.modelos.Respuesta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Services {
    public static List<Pregunta> obtenerPreguntas(int tipo) {
        List<Pregunta> preguntas = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        if (Conection.get_conexion() != null) {
            String sql = "SELECT num_preg, enunciado, tipo FROM preguntas WHERE tipo = ?";
            try {
                st = Conection.get_conexion().prepareStatement(sql);
                st.setInt(1, tipo);
                rs = st.executeQuery();
                while (rs.next()) {
                    Pregunta pregunta = new Pregunta();
                    pregunta.setNum_preg(rs.getInt("num_preg"));
                    pregunta.setEnunciado(rs.getString("enunciado"));
                    pregunta.setTipo(rs.getInt("tipo"));
                    preguntas.add(pregunta);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return preguntas;
    }

    public static List<Respuesta> obtenerRespuestas(Pregunta pregunta) {
        List<Respuesta> respuestas = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        if (Conection.get_conexion() != null) {
            String sql = "SELECT respuestas.num_preg, num_resp, texto_resp FROM respuestas INNER JOIN preguntas ON respuestas.num_preg = preguntas.num_preg WHERE tipo = ?";
            try {
                st = Conection.get_conexion().prepareStatement(sql);
                st.setInt(1, pregunta.getTipo());
                rs = st.executeQuery();
                while (rs.next()) {
                    Respuesta respuesta = new Respuesta();
                    respuesta.setNum_preg(rs.getInt("num_preg"));
                    respuesta.setNum_resp(rs.getInt("num_resp"));
                    respuesta.setTexto_preg(rs.getString("texto_resp"));
                    respuestas.add(respuesta);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return respuestas;
    }
}
