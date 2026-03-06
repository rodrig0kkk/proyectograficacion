package com.proyectograficacion.proyectograficacion.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.proyectograficacion.proyectograficacion.model.Material;
import com.proyectograficacion.proyectograficacion.model.Persona;
import com.proyectograficacion.proyectograficacion.model.Prestamo;

public class ConsolaView {

    public void mostrarMenuPrincipal() {
        System.out.println("\n-- Menu Biblioteca --");
        System.out.println("1. Nuevo material");
        System.out.println("2. Nueva persona");
        System.out.println("3. Prestar material");
        System.out.println("4. Ver personas");
        System.out.println("5. Ver materiales");
        System.out.println("6. Ver prestamos");
        System.out.println("7. Salir");
        System.out.print("Opcion: ");
    }
    
    public void pedirTipoPersona() { System.out.print("\n- Agregar Persona -\nTipo (1 = Alumno, 2 = Profesor): "); }
    public void pedirNombre() { System.out.print("Nombre: "); }
    public void pedirApellido() { System.out.print("Apellido: "); }
    public void pedirCorreo() { System.out.print("Correo: "); }
    public void pedirTelefono() { System.out.print("Telefono: "); }
    public void pedirMatricula() { System.out.print("Matricula: "); }
    public void pedirNumEmpleado() { System.out.print("Num Empleado: "); }

    public void pedirTipoMaterial() { System.out.print("\n- Agregar Material -\nTipo (1 = Libro, 2 = Revista): "); }
    public void pedirCodigo() { System.out.print("Codigo: "); }
    public void pedirTitulo() { System.out.print("Titulo: "); }
    public void pedirAutor() { System.out.print("Autor: "); }
    public void pedirAnio() { System.out.print("Anio: "); }
    public void pedirEditorial() { System.out.print("Editorial: "); }

    public void pedirIdPersona() { System.out.print("\n- Nuevo Prestamo -\nID persona: "); }
    public void pedirCodigoMaterial() { System.out.print("Codigo material: "); }


    public void mostrarExitoPersona(int id) {
        System.out.println("Listo, persona guardada. Su ID es: " + id);
    }
    public void mostrarExitoMaterial() {
        System.out.println("Material guardado correctamente.");
    }
    public void mostrarExitoPrestamo(LocalDate fecha) {
        System.out.println("Prestamo registrado. Se tiene que devolver el: " + fecha.toString());
    }
    public void mostrarErrorValidacion(String mensajeError) {
        System.out.println("No se pudo: " + mensajeError);
    }
    public void mostrarErrorFormato() {
        System.out.println("Error de formato (pusiste letras en vez de numeros o algo asi).");
    }
    public void mostrarErrorOpcion() {
        System.out.println("Esa opcion no existe.");
    }
    public void mostrarDespedida() {
        System.out.println("Saliendo...");
    }


    public void listarPersonas(Map<Integer, Persona> personas) {
        System.out.println("\n--- Personas ---");
        if (personas.isEmpty()) {
            System.out.println("No hay nada registrado todavia.");
        } else {
            for (Map.Entry<Integer, Persona> entry : personas.entrySet()) {
                System.out.println("[" + entry.getKey() + "] " + entry.getValue().toString());
            }
        }
    }

    public void listarMateriales(Map<String, Material> materiales) {
        System.out.println("\n--- Materiales ---");
        if (materiales.isEmpty()) {
            System.out.println("No hay nada registrado todavia.");
        } else {
            for (Map.Entry<String, Material> entry : materiales.entrySet()) {
                System.out.println("[" + entry.getKey() + "] " + entry.getValue().toString());
            }
        }
    }

    public void listarPrestamos(List<Prestamo> prestamos) {
        System.out.println("\n--- Prestamos ---");
        if (prestamos.isEmpty()) {
            System.out.println("Nadie ha pedido nada.");
        } else {
            for (Prestamo p : prestamos) {
                System.out.println("ID " + p.getId() + " | Mat: " + p.getCodigo() + " | Regresa: " + p.getFechaRegreso());
            }
        }
    }
}