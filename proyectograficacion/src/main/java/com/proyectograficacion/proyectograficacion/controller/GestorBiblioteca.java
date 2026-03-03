package com.proyectograficacion.proyectograficacion.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.proyectograficacion.proyectograficacion.model.Alumno;
import com.proyectograficacion.proyectograficacion.model.Biblioteca;
import com.proyectograficacion.proyectograficacion.model.Libro;
import com.proyectograficacion.proyectograficacion.model.Material;
import com.proyectograficacion.proyectograficacion.model.Persona;
import com.proyectograficacion.proyectograficacion.model.Prestamo;
import com.proyectograficacion.proyectograficacion.model.Profesor;
import com.proyectograficacion.proyectograficacion.model.Revista;

public class GestorBiblioteca {

    private Biblioteca biblioteca;

    public GestorBiblioteca() {
        biblioteca = Biblioteca.cargar();
    }

    public int registrarAlumno(String tipo, String nombre, String apellido, String correo, int tel, int libros, double deuda, int matricula) {
        Alumno a = new Alumno(tipo, nombre, apellido, correo, tel, libros, deuda, matricula);
        return biblioteca.agregarPersona(a);
    }

    public int registrarProfesor(String tipo, String nombre, String apellido, String correo, int tel, int libros, double deuda, int numEmp) {
        Profesor p = new Profesor(tipo, nombre, apellido, correo, tel, libros, deuda, numEmp);
        return biblioteca.agregarPersona(p);
    }

    public void registrarLibro(String tipo, String codigo, String autor, String titulo, int anio, String status, String editorial) {
        Libro l = new Libro(tipo, codigo, autor, titulo, anio, status, editorial);
        biblioteca.agregarMaterial(l);
    }

    public void registrarRevista(String tipo, String codigo, String autor, String titulo, int anio, String status) {
        Revista r = new Revista(tipo, codigo, autor, titulo, anio, status);
        biblioteca.agregarMaterial(r);
    }

    public String registrarPrestamo(int idPersona, String codigoMaterial) {
        Persona persona = biblioteca.getPersona(idPersona);
        Material material = biblioteca.getMaterial(codigoMaterial);

        if (persona == null) return "Error: Persona no encontrada.";
        if (material == null) return "Error: Material no encontrado.";
        if (!material.getStatus().equals("Disponible")) {
            return "Error: El material ya se encuentra prestado.";
        }
        material.setStatus("Prestado");

        LocalDate fechaRegreso = LocalDate.now().plusDays(7);
        Prestamo prestamo = new Prestamo(codigoMaterial, persona, material, fechaRegreso, 0);
        biblioteca.agregarPrestamo(prestamo);
        
        return "Exito. Prestamo registrado hasta: " + fechaRegreso.toString();
    }

    public Map<Integer, Persona> obtenerTodasLasPersonas() {
        return biblioteca.getPersonas();
    }

    public Map<String, Material> obtenerTodosLosMateriales() {
        return biblioteca.getMateriales();
    }

    public List<Prestamo> obtenerTodosLosPrestamos() {
        return biblioteca.getPrestamos();
    }
}