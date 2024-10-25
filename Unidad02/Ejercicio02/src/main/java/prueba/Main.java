package prueba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import prueba.models.Empleado;
import prueba.models.Sueldo;

import java.time.LocalDate;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistencia");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("Introduce el nombre del empleado: ");
        String nombre = teclado.nextLine();
        System.out.println("Introduce el oficio del empleado: ");
        String oficio = teclado.nextLine();
        System.out.println("Introduce la fecha de alta del empleado: ");
        LocalDate fechaAlta = LocalDate.parse(teclado.nextLine());
        System.out.println("Introduce el salario del empleado: ");
        double salario = Double.parseDouble(teclado.nextLine());
        System.out.println("Introduce la comisión del empleado: ");
        double comision = Double.parseDouble(teclado.nextLine());

        Sueldo sueldo = new Sueldo(salario, comision);
        Empleado empleado = new Empleado(nombre, oficio, fechaAlta, sueldo);

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(empleado);
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