package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.example.models.Grupo;
import org.example.models.Usuario;

import java.util.List;

public class Main {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();


        // 1. Listado de grupos
        TypedQuery<Grupo> query1 = em.createQuery("SELECT g FROM Grupo g", Grupo.class);
        List<Grupo> grupos = query1.getResultList();
        grupos.forEach(g -> System.out.println(g.getNombre()));

        // 2. Listado de usuarios que no han votado
        TypedQuery<Usuario> query2 = em.createQuery("SELECT u FROM Usuario u WHERE u.haVotado = false", Usuario.class);
        List<Usuario> usuariosNoVotaron = query2.getResultList();
        usuariosNoVotaron.forEach(u -> System.out.println(u.getNombre()));

        // 3. Listado de usuarios nacidos a partir de 1990
        TypedQuery<Usuario> query3 = em.createQuery("SELECT u FROM Usuario u WHERE u.fechaNacimiento >= '1990-01-01'", Usuario.class);
        List<Usuario> usuariosNacidos1990 = query3.getResultList();
        usuariosNacidos1990.forEach(u -> System.out.println(u.getNombre()));

        // 4. Grupos sin componentes cargados
        TypedQuery<Grupo> query4 = em.createQuery("SELECT g FROM Grupo g WHERE g.canciones IS EMPTY", Grupo.class);
        List<Grupo> gruposSinComponentes = query4.getResultList();
        gruposSinComponentes.forEach(g -> System.out.println(g.getNombre()));

        // 5. Grupos sin compañía cargada
        TypedQuery<Grupo> query5 = em.createQuery("SELECT g FROM Grupo g WHERE g.compania IS NULL", Grupo.class);
        List<Grupo> gruposSinCompania = query5.getResultList();
        gruposSinCompania.forEach(g -> System.out.println(g.getNombre()));

        // 6. Grupos de Barcelona con primer disco en año antes de 2010
        TypedQuery<Grupo> query6 = em.createQuery("SELECT g FROM Grupo g WHERE g.ciudad = 'Barcelona' AND g.primerDisco < 2010", Grupo.class);
        List<Grupo> gruposBarcelonaAntes2010 = query6.getResultList();
        gruposBarcelonaAntes2010.forEach(g -> System.out.println(g.getNombre()));

        // 7. Número de grupos de Madrid
        TypedQuery<Long> query7 = em.createQuery("SELECT COUNT(g) FROM Grupo g WHERE g.ciudad = 'Madrid'", Long.class);
        Long numeroGruposMadrid = query7.getSingleResult();
        System.out.println("Número de grupos de Madrid: " + numeroGruposMadrid);

        em.close();
        emf.close();
    }
}