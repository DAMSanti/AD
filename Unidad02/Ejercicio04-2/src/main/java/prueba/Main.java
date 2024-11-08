package prueba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import prueba.models.Departamento;
import prueba.models.Empleado;
import prueba.models.Estudio;
import prueba.models.Sueldo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        boolean salir = false;
        List<Empleado> empleados = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistencia");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("Introduce el identificador del departamento: ");
        long id = Long.parseLong(teclado.nextLine());
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
            System.out.println("Introduce el identificador del departamento:");
            long idDepartamento = Long.parseLong(teclado.nextLine());
            Departamento departamentoEmpleado = entityManager.find(Departamento.class, idDepartamento);
            Sueldo sueldo = new Sueldo(salario, comision);
            Empleado empleado = new Empleado(nombreEmpleado, oficio, fechaAlta, sueldo, departamentoEmpleado);
            Estudio estudio = new Estudio("Ingeniería", "UCS" );
            empleado.setEstudios(List.of(estudio));
            if (departamentoEmpleado != null) {
                try {
                    entityManager.getTransaction().begin();
                    entityManager.persist(estudio);
                    entityManager.persist(empleado);
                    empleados.add(empleado);
                    entityManager.getTransaction().commit();
                } catch (Exception e) {
                    entityManager.getTransaction().rollback();
                    e.printStackTrace();
                }
            } else {
                System.out.println("Departamento no encontrado");
            }
        } while (!salir);
    }
}