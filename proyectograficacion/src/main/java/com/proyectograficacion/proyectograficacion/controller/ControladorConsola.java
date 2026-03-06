package com.proyectograficacion.proyectograficacion.controller;

import java.util.Scanner;

import com.proyectograficacion.proyectograficacion.view.ConsolaView;

public class ControladorConsola {
    private GestorBiblioteca gestor = new GestorBiblioteca();
    private ConsolaView vista = new ConsolaView();
    private Scanner sc = new Scanner(System.in);

    public void iniciar() {
        int opc;
        do {
            vista.mostrarMenuPrincipal();
            opc = sc.hasNextInt() ? sc.nextInt() : -1; 
            sc.nextLine(); 
            switch (opc) {
                case 1: procesarMaterial(); break;
                case 2: procesarPersona(); break;
                case 3: procesarPrestamo(); break;
                case 4: vista.listarPersonas(gestor.getPersonas()); break;
                case 5: vista.listarMateriales(gestor.getMateriales()); break;
                case 6: vista.listarPrestamos(gestor.getPrestamos()); break;
                case 7: vista.mostrarDespedida(); break;
                default: vista.mostrarErrorOpcion();
            }
        } while (opc != 7);
    }

    private void procesarPersona() {
        try {
            vista.pedirTipoPersona(); int tipo = Integer.parseInt(sc.nextLine());
            vista.pedirNombre(); String n = sc.nextLine();
            vista.pedirApellido(); String a = sc.nextLine();
            vista.pedirCorreo(); String c = sc.nextLine();
            vista.pedirTelefono(); int tel = Integer.parseInt(sc.nextLine());
            
            if (tipo == 1) {
                vista.pedirMatricula();
                vista.mostrarExitoPersona(gestor.registrarAlumno(n, a, c, tel, Integer.parseInt(sc.nextLine())));
            } else if (tipo == 2) {
                vista.pedirNumEmpleado();
                vista.mostrarExitoPersona(gestor.registrarProfesor(n, a, c, tel, Integer.parseInt(sc.nextLine())));
            } else vista.mostrarErrorOpcion();
        } catch (Exception e) { vista.mostrarErrorFormato(); }
    }

    private void procesarMaterial() {
        try {
            vista.pedirTipoMaterial(); int tipo = Integer.parseInt(sc.nextLine());
            vista.pedirCodigo(); String cod = sc.nextLine();
            vista.pedirTitulo(); String tit = sc.nextLine();
            vista.pedirAutor(); String aut = sc.nextLine();
            vista.pedirAnio(); int anio = Integer.parseInt(sc.nextLine());
            
            if (tipo == 1) {
                vista.pedirEditorial();
                gestor.registrarLibro(cod, aut, tit, anio, sc.nextLine());
                vista.mostrarExitoMaterial();
            } else if (tipo == 2) {
                gestor.registrarRevista(cod, aut, tit, anio);
                vista.mostrarExitoMaterial();
            } else vista.mostrarErrorOpcion();
        } catch (Exception e) { vista.mostrarErrorFormato(); }
    }

    private void procesarPrestamo() {
        try {
            vista.pedirIdPersona(); int id = Integer.parseInt(sc.nextLine());
            vista.pedirCodigoMaterial(); String cod = sc.nextLine();
            vista.mostrarExitoPrestamo(gestor.registrarPrestamo(id, cod));
        } catch (IllegalArgumentException e) { 
            vista.mostrarErrorValidacion(e.getMessage());
        } catch (Exception e) { 
            vista.mostrarErrorFormato(); 
        }
    }
}