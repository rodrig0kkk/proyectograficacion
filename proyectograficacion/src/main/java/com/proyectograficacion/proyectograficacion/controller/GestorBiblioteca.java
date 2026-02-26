package com.proyectograficacion.proyectograficacion.controller;

import com.proyectograficacion.proyectograficacion.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GestorBiblioteca {

    private Biblioteca biblioteca;

    public GestorBiblioteca() {
        biblioteca = new Biblioteca();
    }

    // ------------------ PERSONAS ------------------
    public int agregarPersona(Persona p) {
        return biblioteca.agregarPersona(p); // devuelve ID asignado
    }

    public Persona buscarPersona(int id) {
        return biblioteca.getPersona(id);
    }

    public void eliminarPersona(int id) {
        if (biblioteca.getPersonas().containsKey(id)) {
            biblioteca.getPersonas().remove(id);
            System.out.println("Persona eliminada.");
        } else {
            System.out.println("No existe persona con ese ID.");
        }
    }

    public void listarPersonas() {
        System.out.println("\n--- Lista de Personas ---");
        HashMap<Integer, Persona> personas = biblioteca.getPersonas();
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            for (Map.Entry<Integer, Persona> entry : personas.entrySet()) {
                int id = entry.getKey();
                Persona p = entry.getValue();
                System.out.println("ID: " + id + " | " + p.getNombre() + " " + p.getApellido() +
                        " | Tipo: " + p.getTipoPersona());
            }
        }
    }

    // ------------------ MATERIALES ------------------
    public void agregarMaterial(Material m) {
        biblioteca.agregarMaterial(m);
    }

    public Material buscarMaterial(String codigo) {
        return biblioteca.getMaterial(codigo);
    }

    public void eliminarMaterial(String codigo) {
        if (biblioteca.getMateriales().containsKey(codigo)) {
            biblioteca.getMateriales().remove(codigo);
            System.out.println("Material eliminado.");
        } else {
            System.out.println("No existe material con ese código.");
        }
    }
   
    public void listarMateriales() {
        System.out.println("\n--- Lista de Materiales ---");
        HashMap<String, Material> materiales = biblioteca.getMateriales();
        if (materiales.isEmpty()) {
            System.out.println("No hay materiales registrados.");
        } else {
            for (Map.Entry<String, Material> entry : materiales.entrySet()) {
                Material m = entry.getValue();
                System.out.println("Código: " + m.getCodigo() + " | Título: " + m.getTitulo() +
                        " | Autor: " + m.getAutor() + " | Tipo: " + m.getTipoMaterial());
            }
        }
    }

    // ------------------ PRÉSTAMOS ------------------
    public void registrarPrestamo(int idPersona, String codigoMaterial) {
        Persona persona = buscarPersona(idPersona);
        Material material = buscarMaterial(codigoMaterial);

        if (persona == null) {
            System.out.println("Persona no encontrada.");
            return;
        }
        if (material == null) {
            System.out.println("Material no encontrado.");
            return;
        }

        Prestamo prestamo = new Prestamo(persona, material);
        biblioteca.agregarPrestamo(prestamo);
        System.out.println("Préstamo registrado: " + persona.getNombre() + " -> " + material.getTitulo());
    }

    public void listarPrestamos() {
        System.out.println("\n--- Lista de Préstamos ---");
        if (biblioteca.getPrestamos().isEmpty()) {
            System.out.println("No hay préstamos registrados.");
        } else {
            for (Prestamo p : biblioteca.getPrestamos()) {
                System.out.println("ID Préstamo: " + p.getId() +
                        " | Persona: " + p.getPersona().getNombre() +
                        " | Material: " + p.getMaterial().getTitulo() +
                        " | Fecha salida: " + p.getFechaSalida());
            }
        }
    }

    // ------------------ GETTER DE BIBLIOTECA ------------------
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }
    // ------------------ MATERIALES ------------------


// ------------------ PRÉSTAMOS ------------------

}

