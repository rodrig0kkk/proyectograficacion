package com.proyectograficacion.proyectograficacion.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Biblioteca {

    // Personas: clave = id autoasignado
    private HashMap<Integer, Persona> personas = new HashMap<>();
    private int contadorPersonas = 1;

    // Materiales: clave = código único
    private HashMap<String, Material> materiales = new HashMap<>();

    // Préstamos
    private ArrayList<Prestamo> prestamos = new ArrayList<>();
    private int contadorPrestamos = 1;

    // ---------------- PERSONAS ----------------
    public int agregarPersona(Persona p) {
        int id = contadorPersonas++;
        personas.put(id, p);
        return id;
    }

    public Persona getPersona(int id) {
        return personas.get(id);
    }

    public void eliminarPersona(int id) {
        personas.remove(id);
    }

    public HashMap<Integer, Persona> getPersonas() {
        return personas;
    }

    // ---------------- MATERIALES ----------------
    public void agregarMaterial(Material m) {
        materiales.put(m.getCodigo(), m);
    }

    public Material getMaterial(String codigo) {
        return materiales.get(codigo);
    }

    public void eliminarMaterial(String codigo) {
        materiales.remove(codigo);
    }

    public HashMap<String, Material> getMateriales() {
        return materiales;
    }

    // ---------------- PRÉSTAMOS ----------------
    public void agregarPrestamo(Prestamo p) {
        p.setId(contadorPrestamos++);
        prestamos.add(p);
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }
}
