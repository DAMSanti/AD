package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.Empleado;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistencia");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Empleado em1 = new Empleado("Juan", "Programador", LocalDate.of(2021, 1, 1), 1000);
        Empleado em2 = new Empleado("Ana", "Analista", LocalDate.of(2021, 1, 1), 1200);

        try{
            entityManager.getTransaction().begin();
            entityManager.persist(em1);
            entityManager.persist(em2);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }
}