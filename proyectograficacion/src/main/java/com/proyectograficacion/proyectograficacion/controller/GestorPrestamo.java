package com.proyectograficacion.proyectograficacion.controller;

import com.proyectograficacion.proyectograficacion.model.*;
import java.time.LocalDate;

public class GestorPrestamo {

    private Biblioteca biblioteca;

    public GestorPrestamo(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

   
    public void registrarPrestamo(String codigoPrestamo, int idPersona, String codigoMaterial, LocalDate fechaRegreso) {

        Persona persona = biblioteca.getPersona(idPersona);
        Material material = biblioteca.getMaterial(codigoMaterial);

        if (persona != null && material != null) {
            Prestamo prestamo = new Prestamo(codigoPrestamo, persona, material, fechaRegreso, 0); // cuota = 0 por ahora
            biblioteca.agregarPrestamo(prestamo);
            System.out.println("Préstamo registrado correctamente: " + prestamo.getCodigo());
        } else {
            System.out.println("Error: Persona o material no encontrado.");
        }
    }


    public void listarPrestamos() {
        System.out.println("\n--- Lista de Préstamos ---");
        for (Prestamo p : biblioteca.getPrestamos()) {
            System.out.println("Código: " + p.getCodigo() +
                               ", Persona: " + p.getPersona().getNombre() +
                               ", Material: " + p.getMaterial().getTitulo() +
                               ", Salida: " + p.getFechaSalida() +
                               ", Regreso: " + p.getFechaRegreso());
        }
    }
}
