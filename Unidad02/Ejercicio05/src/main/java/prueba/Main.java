package prueba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import prueba.models.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistencia");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            EstudioOficial estudioTecnico1 = new EstudioOficial();
            estudioTecnico1.setNombre("Ingeniería Informática");
            estudioTecnico1.setCentro("Miguel Herrero");
            estudioTecnico1.setRealDecreto("RD 123/2020");

            EstudioOficial estudioTecnico2 = new EstudioOficial();
            estudioTecnico2.setNombre("Ingeniería Civil");
            estudioTecnico2.setCentro("Marques de Monistrol");
            estudioTecnico2.setRealDecreto("RD 456/2020");

            EstudioNoOficial estudioHumanidades1 = new EstudioNoOficial();
            estudioHumanidades1.setNombre("Historia");
            estudioHumanidades1.setAcademia("Historia Antigua");
            estudioHumanidades1.setNumeroHoras(100);

            EstudioNoOficial estudioHumanidades2 = new EstudioNoOficial();
            estudioHumanidades2.setAcademia("Filosofía");
            estudioHumanidades2.setNumeroHoras(150);

            entityManager.persist(estudioTecnico1);
            entityManager.persist(estudioTecnico2);
            entityManager.persist(estudioHumanidades1);
            entityManager.persist(estudioHumanidades2);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}