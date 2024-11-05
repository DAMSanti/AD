package prueba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import prueba.models.Departamento;
import prueba.models.Empleado;
import prueba.models.Sueldo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean salir = false;
        boolean terminar = false;
        Scanner teclado = new Scanner(System.in);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistencia");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        do {
            System.out.println("Introduce el identificador del departamento: ");
            long id = Long.parseLong(teclado.nextLine());
            if (id == 0) {
                salir = true;
                break;
            }
            System.out.println("Introduce el nombre del departamento: ");
            String nombre = teclado.nextLine();
            System.out.println("Introduce la localidad del departamento: ");
            String localidad = teclado.nextLine();
            Departamento departamento = new Departamento(id, nombre, localidad);

            try {
                entityManager.getTransaction().begin();
                entityManager.persist(departamento);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                e.printStackTrace();
            }

            List<Empleado> empleados = new ArrayList<>();
            do {
                System.out.println("Introduce el nombre del empleado:");
                String nombreEmpleado = teclado.nextLine();
                if (nombreEmpleado.equals("")) {
                    salir = true;
                    break;
                }
                System.out.println("Introduce el oficio del empleado:");
                String oficio = teclado.nextLine();
                System.out.println("Introduce la fecha de alta del empleado:");
                LocalDate fechaAlta = LocalDate.parse(teclado.nextLine());
                System.out.println("Introduce el salario del empleado:");
                double salario = Double.parseDouble(teclado.nextLine());
                System.out.println("Introduce la comisión del empleado:");
                double comision = Double.parseDouble(teclado.nextLine());
                Sueldo sueldo = new Sueldo(salario, comision);
                Empleado empleado = new Empleado(nombreEmpleado, oficio, fechaAlta, sueldo, departamento);

                try {
                    entityManager.getTransaction().begin();
                    empleados.add(empleado);
                    entityManager.persist(empleado);
                    entityManager.getTransaction().commit();
                } catch (Exception e) {
                    entityManager.getTransaction().rollback();
                    e.printStackTrace();
                }
            } while (!salir);

            departamento.setEmpleados(empleados);
        } while (!terminar);

        entityManager.close();
        entityManagerFactory.close();
    }
}