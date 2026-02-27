package com.proyectograficacion.proyectograficacion.view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.proyectograficacion.proyectograficacion.controller.GestorBiblioteca;
import com.proyectograficacion.proyectograficacion.model.Material;
import com.proyectograficacion.proyectograficacion.model.Persona;
import com.proyectograficacion.proyectograficacion.model.Prestamo;

public class ConsolaView {

    private GestorBiblioteca gestor;
    private Scanner sc;

    public ConsolaView(GestorBiblioteca gestor) {
        this.gestor = gestor;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = 0;
        do {
            System.out.println("\n=== MENU BIBLIOTECA ===");
            System.out.println("1. Agregar Material");
            System.out.println("2. Agregar Persona");
            System.out.println("3. Registrar Prestamo");
            System.out.println("4. Listar Personas");
            System.out.println("5. Listar Materiales");
            System.out.println("6. Listar Prestamos");
            System.out.println("7. Salir");
            System.out.print("Elija una opcion: ");

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); 
            } else {
                sc.nextLine();
                continue;
            }

            switch (opcion) {
                case 1: menuAgregarMaterial(); break;
                case 2: menuAgregarPersona(); break;
                case 3: menuRegistrarPrestamo(); break;
                case 4: menuListarPersonas(); break;
                case 5: menuListarMateriales(); break;
                case 6: menuListarPrestamos(); break;
                case 7: System.out.println("Saliendo de la consola..."); break;
                default: System.out.println("Opcion invalida.");
            }
        } while (opcion != 7);
    }

    private void menuAgregarPersona() {
        System.out.print("Alumno [1] o Profesor [2]?: ");
        int tipo = sc.nextInt(); sc.nextLine();
        
        System.out.print("Nombre: "); String nombre = sc.nextLine();
        System.out.print("Apellido: "); String apellido = sc.nextLine();
        System.out.print("Correo: "); String correo = sc.nextLine();
        System.out.print("Telefono: "); int tel = sc.nextInt(); sc.nextLine();
        
        if (tipo == 1) {
            System.out.print("Matricula: "); int mat = sc.nextInt(); sc.nextLine();
            int id = gestor.registrarAlumno("Alumno", nombre, apellido, correo, tel, 0, 0.0, mat);
            System.out.println("Alumno registrado con ID: " + id);
        } else if (tipo == 2) {
            System.out.print("Num Empleado: "); int emp = sc.nextInt(); sc.nextLine();
            int id = gestor.registrarProfesor("Profesor", nombre, apellido, correo, tel, 0, 0.0, emp);
            System.out.println("Profesor registrado con ID: " + id);
        }
    }

    private void menuAgregarMaterial() {
        System.out.print("Libro [1] o Revista [2]?: ");
        int tipo = sc.nextInt(); sc.nextLine();
        
        System.out.print("Codigo: "); String cod = sc.nextLine();
        System.out.print("Titulo: "); String tit = sc.nextLine();
        System.out.print("Autor: "); String aut = sc.nextLine();
        System.out.print("Anio: "); int anio = sc.nextInt(); sc.nextLine();

        if (tipo == 1) {
            System.out.print("Editorial: "); String edit = sc.nextLine();
            gestor.registrarLibro("Libro", cod, aut, tit, anio, "Disponible", edit);
            System.out.println("Libro guardado exitosamente.");
        } else if (tipo == 2) {
            gestor.registrarRevista("Revista", cod, aut, tit, anio, "Disponible");
            System.out.println("Revista guardada exitosamente.");
        }
    }

    private void menuRegistrarPrestamo() {
        System.out.print("ID de la persona: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Codigo del material: "); String cod = sc.nextLine();
        
        String resultado = gestor.registrarPrestamo(id, cod);
        System.out.println(resultado);
    }

    private void menuListarPersonas() {
        System.out.println("\n--- LISTA DE PERSONAS ---");
        Map<Integer, Persona> personas = gestor.obtenerTodasLasPersonas();
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            for (Map.Entry<Integer, Persona> entry : personas.entrySet()) {
                System.out.println("ID: " + entry.getKey() + " - " + entry.getValue().toString());
            }
        }
    }

    private void menuListarMateriales() {
        System.out.println("\n--- LISTA DE MATERIALES ---");
        Map<String, Material> materiales = gestor.obtenerTodosLosMateriales();
        if (materiales.isEmpty()) {
            System.out.println("No hay materiales registrados.");
        } else {
            for (Map.Entry<String, Material> entry : materiales.entrySet()) {
                System.out.println("Codigo: " + entry.getKey() + " - " + entry.getValue().toString());
            }
        }
    }

    private void menuListarPrestamos() {
        System.out.println("\n--- LISTA DE PRESTAMOS ---");
        List<Prestamo> prestamos = gestor.obtenerTodosLosPrestamos();
        if (prestamos.isEmpty()) {
            System.out.println("No hay prestamos registrados.");
        } else {
            for (Prestamo p : prestamos) {
                System.out.println("Prestamo ID: " + p.getId() + " - Codigo Material: " + p.getCodigo() + " - Fecha Regreso: " + p.getFechaRegreso());
            }
        }
    }

}
