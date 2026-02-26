package com.proyectograficacion.proyectograficacion.view;

public class Prints {
    public static void printMenuInicial() {
        System.out.println("\n--- MENU INICIAL ---");
        System.out.print(" Seleccione una opción: ");
        System.out.println("1. Registrar en Biblioteca");
        System.out.println("2. Gestión de Material");
        System.out.println("3. Gestión de Prestamos");
        System.out.println("4. Gestión de Personas");
        System.out.println("5. Salir");
        
    }

    public static void printMenuRegistrar(String nombre) {
        System.out.println("\n Registrar Material [1],Registrar Persona [2]");
       
    }

    public static void printGestionMaterial() {
        System.out.println("\n--- Registro de Material ---");
        System.out.println("1. tipo");
        System.out.println("2. codigo");
        System.out.println("3. autor");
        System.out.println("4. titulo");
        System.out.println("5. anio");
        System.out.println("6. status");
        System.out.println("7. Volver al menú principal");
       
    }

    public static void printGestionPrestamo() {
        System.out.println("\n--- Administrar Prestamos ---");
        System.out.println("1. codigo");
        System.out.println("2. id");
        System.out.println("3. Fecha Inicio");
        System.out.println("4. Fceha Regreo");
        System.out.println("5. Volver al menú principal");
       
    }

    public static void printGestionPersonas() {
        System.out.println("\n--- Administrar Persona ---");
        System.out.println("1. Tipo");
        System.out.println("2. nombre");
        System.out.println("3. Apellido");
        System.out.println("4. correo");
         System.out.println("5. telefono");
          System.out.println("6. numero de libros");
        System.out.println("7. adeudo");
        System.out.print("Seleccione una opción: ");
    }
}