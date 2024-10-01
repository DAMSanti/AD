package prueba.servicios;

import prueba.conexion.Conexion;
import prueba.objetos.Usuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Servicios {
    public static void addUser(Usuario user) {
        String sql = "INSERT INTO usuarios (user, contraseña, nombre, apellidos, fechanac, numVotos) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = Conexion.get_conexion().prepareStatement(sql);
            ps.setString(1, user.getUser());
            ps.setString(2, user.getContraseña());
            ps.setString(3, user.getNombre());
            ps.setString(4, user.getApellidos());
            ps.setDate(5, user.getFechaNacimiento());
            ps.setInt(6, user.getNumVotos());
            ps.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("El usuario ya existe");
                System.out.println(e.getMessage());
            } else {
                System.out.println("Error al insertar el usuario");
                System.out.println(e.getMessage());
            }
        }
    }
}
