package com.biblioteca.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import com.biblioteca.model.*;

public class GestorBiblioteca {
    
    private Biblioteca biblioteca = Biblioteca.cargar();

    public int registrarAlumno(String n, String a, String c, int tel, int mat) {
        return biblioteca.agregarPersona(new Alumno("Alumno", n, a, c, tel, 0, 0.0, mat));
    }

    public int registrarProfesor(String n, String a, String c, int tel, int emp) {
        return biblioteca.agregarPersona(new Profesor("Profesor", n, a, c, tel, 0, 0.0, emp));
    }

    public void registrarLibro(String cod, String aut, String tit, int anio, String ed) {
        biblioteca.agregarMaterial(new Libro("Libro", cod, aut, tit, anio, "Disponible", ed));
    }

    public void registrarRevista(String cod, String aut, String tit, int anio) {
        biblioteca.agregarMaterial(new Revista("Revista", cod, aut, tit, anio, "Disponible"));
    }

    public LocalDate registrarPrestamo(int idPersona, String codigoMaterial) throws IllegalArgumentException {
        Persona p = biblioteca.getPersona(idPersona);
        Material m = biblioteca.getMaterial(codigoMaterial);

        if (p == null) throw new IllegalArgumentException("La persona no existe en el sistema.");
        if (m == null) throw new IllegalArgumentException("El material no existe.");
        if (!m.getStatus().equals("Disponible")) throw new IllegalArgumentException("Material ya prestado.");
        
        m.setStatus("Prestado");
        LocalDate fechaRegreso = LocalDate.now().plusDays(7);
        biblioteca.agregarPrestamo(new Prestamo(codigoMaterial, p, m, fechaRegreso, 0));
        
        return fechaRegreso; 
    }

    public Map<Integer, Persona> getPersonas() { return biblioteca.getPersonas(); }
    public Map<String, Material> getMateriales() { return biblioteca.getMateriales(); }
    public List<Prestamo> getPrestamos() { return biblioteca.getPrestamos(); }

}
