package prueba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import prueba.models.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistencia");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("Introduce el id del estudio:");
        long estudioId = Long.parseLong(teclado.nextLine());
        System.out.println("Introduce el id del empleado:");
        long empleadoId = Long.parseLong(teclado.nextLine());

        Estudio estudio = entityManager.find(Estudio.class, estudioId);
        Empleado empleado = entityManager.find(Empleado.class, empleadoId);

        if (estudio != null && empleado != null) {
            System.out.println("Introduce la fecha de finalización del estudio (yyyy-MM-dd):");
            LocalDate fechaFinalizacion = LocalDate.parse(teclado.nextLine());

            EmpleadoEstudio empleadoEstudio = new EmpleadoEstudio(empleado, estudio, fechaFinalizacion);

            try {
                entityManager.getTransaction().begin();
                entityManager.persist(empleadoEstudio);
                entityManager.getTransaction().commit();
                System.out.println("Empleado añadido al estudio con éxito.");
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Empleado o Estudio no encontrado.");
        }
    }
}