package com.biblioteca.controller;

import java.time.LocalDate;
import java.util.List;
import com.biblioteca.model.*;

public class GestorPrestamo {

    private Biblioteca biblioteca;

    public GestorPrestamo(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public LocalDate registrarPrestamo(Persona persona, Material material) throws IllegalArgumentException {
        if (persona == null) throw new IllegalArgumentException("La persona no existe en el sistema.");
        if (material == null) throw new IllegalArgumentException("El material no existe.");
        if (!material.getStatus().equals("Disponible")) throw new IllegalArgumentException("Material ya prestado.");

        material.setStatus("Prestado");
        LocalDate fechaRegreso = LocalDate.now().plusDays(7);
        Prestamo prestamo = new Prestamo(material.getCodigo(), persona, material, fechaRegreso, 0);
        biblioteca.agregarPrestamo(prestamo);

        return fechaRegreso;
    }

    public List<Prestamo> getPrestamos() {
        return biblioteca.getPrestamos();
    }
      public String listarPrestamos() {
        StringBuilder sb = new StringBuilder();
        for (Prestamo p : biblioteca.getPrestamos()) {
            sb.append("Préstamo #")
              .append(p.getId())
              .append(" | Mat: ")
              .append(p.getCodigo())
              .append(" | Persona: ")
              .append(p.getPersona().getNombre())
              .append(" ")
              .append(p.getPersona().getApellido())
              .append(" | Vence: ")
              .append(p.getFechaRegreso())
              .append("\n");
        }
        return sb.toString();
    }

    
    public boolean eliminarPrestamo(int id) {
        List<Prestamo> prestamos = biblioteca.getPrestamos();
        for (Prestamo p : prestamos) {
            if (p.getId() == id) {
            
                p.getMaterial().setStatus("Disponible");
                prestamos.remove(p);
                return true;
            }
        }
        return false; 
    }
}


