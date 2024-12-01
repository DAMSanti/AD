package org.example;

import org.example.basedatos.GestorBaseDatos;
import org.example.modelos.Grupo;
import org.example.modelos.Usuario;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GestorBaseDatos gestor = new GestorBaseDatos();

        List<Usuario> usuarios = gestor.obtenerUsuariosNacidosDesde(1990);
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNombre());
        }

        List<Grupo> grupos = gestor.obtenerGruposPorMinCanciones(10);
        for (Grupo grupo : grupos) {
            System.out.println(grupo.getNombre());
        }

        List<Object[]> cancionesPorGrupos = gestor.obtenerCancionesPorGrupos(gestor.obtenerNombresDeGrupos());
        for (Object[] cancionPorGrupo : cancionesPorGrupos) {
            System.out.println(cancionPorGrupo[0] + " - " + cancionPorGrupo[1]);
        }

        List<Grupo> gruposBarcelonaAntes2010 = gestor.obtenerGruposPorLocalidadYAnio("Barcelona", 2010);
        for (Grupo grupo : gruposBarcelonaAntes2010) {
            System.out.println(grupo.getNombre());
        }

        List<Object[]> localidadesConGrupos = gestor.obtenerLocalidadesConGrupos();
        for (Object[] localidadConGrupos : localidadesConGrupos) {
            System.out.println(localidadConGrupos[0] + " - " + localidadConGrupos[1]);
        }

    }
}