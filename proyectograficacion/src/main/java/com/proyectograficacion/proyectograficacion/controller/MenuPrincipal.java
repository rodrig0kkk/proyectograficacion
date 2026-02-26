package com.proyectograficacion.proyectograficacion.controller;

import java.time.LocalDate;
import java.util.Scanner;
import com.proyectograficacion.proyectograficacion.model.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.proyectograficacion.proyectograficacion.view.Prints;

public class MenuPrincipal {

    private GestorBiblioteca gestorBiblioteca;
    private GestorPrestamo gestorPrestamo;

    public MenuPrincipal() {
        gestorBiblioteca = new GestorBiblioteca();
         gestorBiblioteca = new GestorBiblioteca();
       
    }
        public void registrarPrestamo(int idPersona, String codigoMaterial) {

    Persona persona = gestorBiblioteca.buscarPersona(idPersona);
    Material material = gestorBiblioteca.buscarMaterial(codigoMaterial);

    if (persona != null && material != null) {

        LocalDate fechaRegreso = LocalDate.now().plusDays(7);
        double cuota = 0;

        Prestamo prestamo = new Prestamo(codigoMaterial, persona, material, fechaRegreso, cuota);

        gestorBiblioteca.getBiblioteca().getPrestamos().add(prestamo);

        System.out.println("Préstamo registrado correctamente: " +
                           "Persona: " + persona.getNombre() +
                           ", Material: " + material.getTitulo() +
                           ", Código Préstamo: " + prestamo.getCodigo() +
                           ", Fecha regreso: " + prestamo.getFechaRegreso());
    } else {
        System.out.println("Persona o Material no encontrado");
    }
}

    
    public void start(Stage stage) {
        Button btnPersona = new Button("Agregar Alumno");
        Button btnMaterial = new Button("Agregar Libro");
        Button btnPrestamo = new Button("Registrar Préstamo");

        btnPersona.setOnAction(e -> System.out.println("Alumno agregado"));
        btnMaterial.setOnAction(e -> System.out.println("Libro agregado"));
     

        VBox root = new VBox(10, btnPersona, btnMaterial, btnPrestamo);
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Sistema Biblioteca");
        stage.setScene(scene);
        stage.show();
    }

    public void menuConsola() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            Prints.printMenuInicial();

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); 
            } else {
                System.out.println("Ingrese un número válido.");
                sc.nextLine();
                continue;
            }

            switch (opcion) {
                case 1: // Material
    System.out.print("Libro [1] o Revista [2]? ");
    int tipoMat = sc.nextInt();
    sc.nextLine();

    System.out.print("Ingrese tipo de material: "); // ej: "Libro" o "Revista"
    String tipoMaterial = sc.nextLine();

    if (tipoMat == 1) { // Libro
        System.out.println("Ingrese código:");
        String codigo = sc.nextLine();
        System.out.println("Ingrese título:");
        String titulo = sc.nextLine();
        System.out.println("Ingrese autor:");
        String autor = sc.nextLine();
        System.out.println("Ingrese editorial:");
        String editorial = sc.nextLine();
        System.out.println("Ingrese año:");
        int anio = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese status:");
        String status = sc.nextLine();

        Libro libro = new Libro(tipoMaterial, codigo, autor, titulo, anio, status, editorial);
        gestorBiblioteca.agregarMaterial(libro);
        System.out.println("Libro registrado correctamente.");

    } else if (tipoMat == 2) { 
        System.out.println("Ingrese código:");
        String codigo = sc.nextLine();
        System.out.println("Ingrese título:");
        String titulo = sc.nextLine();
        System.out.println("Ingrese autor:");
        String autor = sc.nextLine();
        System.out.println("Ingrese año:");
        int anio = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese status:");
        String status = sc.nextLine();

        Revista revista = new Revista(tipoMaterial, codigo, autor, titulo, anio, status);
        gestorBiblioteca.agregarMaterial(revista);
        System.out.println("Revista registrada correctamente.");

    } else {
        System.out.println("Opción no válida.");
    }
    break;

case 2: 
    System.out.print("Alumno [1] o Profesor [2]? ");
    int tipoPer = sc.nextInt();
    sc.nextLine();

    System.out.print("Ingrese tipo de persona: "); // ej: "Alumno" o "Profesor"
    String tipoPersona = sc.nextLine();

    System.out.println("Ingrese nombre:");
    String nombre = sc.nextLine();
    System.out.println("Ingrese apellido:");
    String apellido = sc.nextLine();
    System.out.println("Ingrese correo:");
    String correo = sc.nextLine();
    System.out.println("Ingrese teléfono:");
    int telefono = sc.nextInt();
    sc.nextLine();
    System.out.println("Ingrese número de libros:");
    int numLibros = sc.nextInt();
    sc.nextLine();
    System.out.println("Ingrese deuda:");
    double deuda = sc.nextDouble();
    sc.nextLine();

    if (tipoPer == 1) { 
        System.out.println("Ingrese número de matrícula:");
        int matricula = sc.nextInt();
        sc.nextLine();

        Alumno alumno = new Alumno(tipoPersona, nombre, apellido, correo, telefono, numLibros, deuda, matricula);
        int idAlumno = gestorBiblioteca.agregarPersona(alumno);
        System.out.println("Alumno registrado correctamente con ID: " + idAlumno);

    } else if (tipoPer == 2) { 
        System.out.println("Ingrese número de empleado:");
        int numEmpleado = sc.nextInt();
        sc.nextLine();

        Profesor profesor = new Profesor(tipoPersona, nombre, apellido, correo, telefono, numLibros, deuda, numEmpleado);
        int idProfesor = gestorBiblioteca.agregarPersona(profesor);
        System.out.println("Profesor registrado correctamente con ID: " + idProfesor);

    } else {
        System.out.println("Opción no válida.");
    }
    break;

            
        

                case 3: 
                    Prints.printGestionPrestamo();
                    int sub3 = sc.nextInt();
                    sc.nextLine();
                    switch (sub3) {
                        case 1: System.out.println("Editar código..."); break;
                        case 2: System.out.println("Editar id..."); break;
                        case 3: System.out.println("Editar fecha salida..."); break;
                        case 4: System.out.println("Editar fecha regreso..."); break;
                        case 5: System.out.println("Volviendo al menú principal..."); break;
                        default: System.out.println("Opción no válida.");
                    }
                    break;

                case 4: 
                    Prints.printGestionPersonas();
                    int sub4 = sc.nextInt();
                    sc.nextLine();
                    switch (sub4) {
                        case 1: System.out.println("Editar tipo..."); break;
                        case 2: System.out.println("Editar nombre..."); break;
                        case 3: System.out.println("Editar apellido..."); break;
                        case 4: System.out.println("Editar correo..."); break;
                        case 5: System.out.println("Editar teléfono..."); break;
                        case 6: System.out.println("Editar número de libros..."); break;
                        case 7: System.out.println("Editar deuda..."); break;
                        default: System.out.println("Opción no válida.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }

        } while (opcion != 5);

        sc.close();
    }
}


