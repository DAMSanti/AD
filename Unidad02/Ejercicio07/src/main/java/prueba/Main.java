package prueba;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import prueba.modelo.Alumno;
import prueba.modelo.Curso;
import prueba.modelo.Profesor;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
        EntityManager em = emf.createEntityManager();
        Scanner teclado = new Scanner(System.in);
        boolean salir = false;

        while (salir == false) {
            System.out.println("GESTION DE ALUMNOS");
            System.out.println("1.- Datos de alumno\n2.- Datos de curso\n3.- Listado de cursos\n4.- Listado de alumnos de curso\n0.- Salir");
            switch(teclado.nextInt()) {
                case 1-> {
                    teclado.nextLine();
                    System.out.println("Introduce el id del alumno:");
                    int id = teclado.nextInt();
                    Alumno alumno = em.find(Alumno.class, id);
                    System.out.println("Nombre: " + alumno.getNombre());
                }
                case 2-> {
                    teclado.nextLine();
                    System.out.println("Introduce el id del curso:");
                    String id = teclado.nextLine();
                    Curso curso = em.find(Curso.class, id);
                    Profesor profesor = em.find(Profesor.class, curso.getProfesor().getId());
                    System.out.println("Nombre: " + curso.getNombre() + " Tutor: " + profesor.getNombre());

                }
                case 3-> {
                    List<Curso> cursos = em.createNamedQuery("Curso.findAll", Curso.class).getResultList();

                    for (Curso curso : cursos) {
                        System.out.println("Nombre: " + curso.getNombre());
                        System.out.println("-----");
                    }
                }
                case 4-> {
                    teclado.nextLine();
                    System.out.println("Introduce el id del curso: ");
                    String id = teclado.nextLine();
                    Curso curso = em.find(Curso.class, id);
                    List<Alumno> alumnos = em.createNamedQuery("Alumno.findByCurso", Alumno.class).setParameter("id", curso).getResultList();

                    for (Alumno alumno : alumnos) {
                        System.out.println("Nombre: " + alumno.getNombre());
                        System.out.println("Curso: " + alumno.getCurso().getNombre());
                        System.out.println("Nota Media: " + alumno.getNotaMedia());
                        System.out.println("-----");
                    }
                }
                case 0-> {
                    salir = true;
                }
                default -> System.out.println("Opción no válida");
            }
        }
        em.close();
        emf.close();
    }
}