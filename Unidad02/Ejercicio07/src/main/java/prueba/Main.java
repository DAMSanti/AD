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
            // Menu
            System.out.println("GESTION DE ALUMNOS");
            System.out.println("1.- Datos de alumno\n2.- Datos de curso\n3.- Listado de cursos\n4.- Listado de alumnos de curso\n5.- Modificar nombre del curso\n6.- Añadir curso\n0.- Salir");
            switch(teclado.nextInt()) {
                case 1-> {
                    teclado.nextLine();
                    System.out.println("Introduce el id del alumno:");
                    int id = teclado.nextInt();
                    Alumno alumno = em.find(Alumno.class, id);
                    if (alumno == null) {
                        System.out.println("Alumno no encontrado");
                        break;
                    }
                    System.out.println("Nombre: " + alumno.getNombre());
                }
                case 2-> {
                    teclado.nextLine();
                    System.out.println("Introduce el id del curso:");
                    String id = teclado.nextLine();
                    Curso curso = em.find(Curso.class, id);
                    if (curso == null) {
                        System.out.println("Curso no encontrado");
                        break;
                    }
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

                    if (alumnos.isEmpty()) {
                        System.out.println("No hay alumnos en el curso");
                        break;
                    }

                    for (Alumno alumno : alumnos) {
                        System.out.println("Nombre: " + alumno.getNombre());
                        System.out.println("Curso: " + alumno.getCurso().getNombre());
                        System.out.println("Nota Media: " + alumno.getNotaMedia());
                        System.out.println("-----");
                    }
                }
                case 5-> {
                    teclado.nextLine();
                    System.out.println("Introduce el id del curso: ");
                    String id = teclado.nextLine();
                    Curso curso = em.find(Curso.class, id);
                    if (curso != null) {
                        System.out.println("Nombre actual: " + curso.getNombre());
                        System.out.println("Introduce el nuevo nombre: ");
                        String nuevoNombre = teclado.nextLine();
                        try {
                            em.getTransaction().begin();
                            curso.setNombre(nuevoNombre);
                            em.getTransaction().commit();
                            System.out.println("Nombre del curso actualizado a: " + curso.getNombre());
                        } catch (Exception e) {
                            System.out.println("Error al actualizar el nombre del curso");
                        }
                    } else {
                        System.out.println("Curso no encontrado");
                    }
                }
                case 6-> {
                    teclado.nextLine();
                    String id;
                    do {
                        System.out.println("Introduce el id del curso: ");
                        id = teclado.nextLine();
                    } while (id.length() != 4 || id.length() == 0);
                    Curso curso = em.find(Curso.class, id);
                    if (curso == null) {
                        curso = new Curso();
                        curso.setId(id);
                        System.out.println("Introduce el nombre del curso: ");
                        curso.setNombre(teclado.nextLine());
                        Profesor profe = em.createNamedQuery("Profesor.findById", Profesor.class).setParameter("id", 2).getSingleResult();
                        curso.setProfesor(profe);
                        try {
                            em.getTransaction().begin();
                            em.persist(curso);
                            em.getTransaction().commit();
                            System.out.println("Curso añadido");
                        } catch (Exception e) {
                            System.out.println("Error al añadir el curso");
                        }
                    } else {
                        System.out.println("Curso ya existe");
                    }
                }
                case 7-> {
<<<<<<< Updated upstream
                    teclado.nextLine();
                    System.out.println("Introduce el id del curso: ");
                    String id = teclado.nextLine();
                    Curso curso = em.find(Curso.class, id);
                    if (curso != null) {
                        System.out.println("Nombre actual: " + curso.getNombre());
                        System.out.println("Introduce el id del nuevo tutor: ");
                        int nuevoTutorId = teclado.nextInt();
                        Profesor nuevoTutor = em.find(Profesor.class, nuevoTutorId);
                        if (nuevoTutor != null) {
                            em.getTransaction().begin();
                            curso.setProfesor(nuevoTutor);
                            em.getTransaction().commit();
                            System.out.println("Tutor del curso actualizado a: " + nuevoTutor.getNombre());
                        } else {
                            System.out.println("Tutor no encontrado");
=======
                    System.out.println("Introduce el id del curso: ");
                    String id = teclado.nextLine();
                    System.out.println("Introduce el nombre del curso: ");
                    String nombre = teclado.nextLine();

                    Curso curso = em.find(Curso.class, id);
                    em.getTransaction().begin();
                    if (curso == null) {
                        curso = new Curso();
                        curso.setId(id);
                        curso.setNombre(nombre);
                        Profesor profe = em.createNamedQuery("Profesor.findById", Profesor.class).setParameter("id", 2).getSingleResult();
                        curso.setProfesor(profe);
                        em.persist(curso);
                        System.out.println("Curso añadido");
                    } else {
                        curso.setNombre(nombre);
                        System.out.println("Introduce el id del tutor: ");
                        int tutorId = teclado.nextInt();
                        Profesor profe = em.find(Profesor.class, tutorId);
                        curso.setProfesor(profe);
                        System.out.println("Curso modificado");
                    }
                    em.getTransaction().commit();
                }
                case 8-> {
                    System.out.println("Introduce el id del curso: ");
                    String id = teclado.nextLine();
                    Curso curso = em.find(Curso.class, id);

                    if (curso != null) {
                        System.out.println("Nombre del curso: " + curso.getNombre());
                        System.out.println("Nombre del tutor actual: " + curso.getProfesor().getNombre());

                        System.out.println("Introduce el id del nuevo tutor: ");
                        int tutorId = teclado.nextInt();
                        teclado.nextLine(); // Limpiar el buffer

                        Profesor nuevoTutor = em.find(Profesor.class, tutorId);
                        if (nuevoTutor != null) {
                            List<Curso> cursos = em.createNamedQuery("Curso.findAll", Curso.class).getResultList();
                            boolean isAlreadyTutor = cursos.stream().anyMatch(c -> c.getProfesor().getId() == tutorId);

                            if (!isAlreadyTutor) {
                                em.getTransaction().begin();
                                curso.setProfesor(nuevoTutor);
                                em.getTransaction().commit();
                                System.out.println("Tutor del curso actualizado a: " + nuevoTutor.getNombre());
                            } else {
                                System.out.println("El profesor ya es tutor de otro curso");
                            }
                        } else {
                            System.out.println("Profesor no encontrado");
>>>>>>> Stashed changes
                        }
                    } else {
                        System.out.println("Curso no encontrado");
                    }
                }
<<<<<<< Updated upstream
=======
                case 9-> {
                    System.out.println("Introduce el id del alumno: ");
                    int id = teclado.nextInt();
                    teclado.nextLine(); // Limpiar el buffer

                    Alumno alumno = em.find(Alumno.class, id);
                    if (alumno != null) {
                        em.getTransaction().begin();
                        em.remove(alumno);
                        em.getTransaction().commit();
                        System.out.println("Alumno eliminado");
                    } else {
                        System.out.println("Alumno no encontrado");
                    }
                }
>>>>>>> Stashed changes
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