package org.example.basedatos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.example.modelos.Grupo;
import org.example.modelos.Usuario;

import java.util.List;

public class GestorBaseDatos {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Usuario> obtenerUsuariosNacidosDesde(int anio) {
        EntityManager em = getEntityManager();
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE year(u.fechanac) >= :anio", Usuario.class);
        query.setParameter("anio", anio);
        return query.getResultList();
    }

    public List<Grupo> obtenerGruposPorMinCanciones(int numCanciones) {
        EntityManager em = getEntityManager();
        TypedQuery<Grupo> query = em.createNamedQuery("Grupo.findByMinCanciones", Grupo.class);
        query.setParameter("numCanciones", numCanciones);
        return query.getResultList();
    }

    public List<String> obtenerNombresDeGrupos() {
        EntityManager em = getEntityManager();
        TypedQuery<String> query = em.createQuery("SELECT g.nombre FROM Grupo g", String.class);
        return query.getResultList();
    }

    public List<Object[]> obtenerCancionesPorGrupos(List<String> nombresGrupos) {
        EntityManager em = getEntityManager();
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT c.titulo, g.nombre FROM Cancion c JOIN c.grupo g WHERE g.nombre IN :nombresGrupos", Object[].class);
        query.setParameter("nombresGrupos", nombresGrupos);
        return query.getResultList();
    }

    public List<Grupo> obtenerGruposPorLocalidadYAnio(String localidad, int anio) {
        EntityManager em = getEntityManager();
        TypedQuery<Grupo> query = em.createNamedQuery("Grupo.findByLocalidadAndAnio", Grupo.class);
        query.setParameter("localidad", localidad);
        query.setParameter("anio", anio);
        return query.getResultList();
    }

    public List<Object[]> obtenerLocalidadesConGrupos() {
        EntityManager em = getEntityManager();
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT g.localidad, COUNT(g) FROM Grupo g GROUP BY g.localidad", Object[].class);
        return query.getResultList();
    }
}
