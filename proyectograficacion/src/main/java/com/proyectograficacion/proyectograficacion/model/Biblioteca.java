package com.proyectograficacion.proyectograficacion.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

public class Biblioteca {

    private HashMap<Integer, Persona> personas = new HashMap<>();
    private int contadorPersonas = 1;

    private HashMap<String, Material> materiales = new HashMap<>();

    private ArrayList<Prestamo> prestamos = new ArrayList<>();
    private int contadorPrestamos = 1;

    private static final String FILE = "biblioteca.json";
    private transient Gson gson = crearGson();

    // Metodo que configura Gson para que entienda LocalDate
    private static Gson crearGson() {
        return new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (date, type, ctx) -> new JsonPrimitive(date.toString()))
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, ctx) -> LocalDate.parse(json.getAsString()))
            .setPrettyPrinting()
            .create();
    }

    public int agregarPersona(Persona p) {
        int id = contadorPersonas++;
        personas.put(id, p);
        guardar();
        return id;
    }

    public Persona getPersona(int id) { return personas.get(id); }
    public HashMap<Integer, Persona> getPersonas() { return personas; }

    public void agregarMaterial(Material m) {
        materiales.put(m.getCodigo(), m);
        guardar();
    }

    public Material getMaterial(String codigo) { return materiales.get(codigo); }
    public HashMap<String, Material> getMateriales() { return materiales; }

    public void agregarPrestamo(Prestamo p) {
        p.setId(contadorPrestamos++);
        prestamos.add(p);
        guardar();
    }

    public ArrayList<Prestamo> getPrestamos() { return prestamos; }

    public void guardar() {
        if (gson == null) gson = crearGson();
        try (FileWriter writer = new FileWriter(FILE)) {
            gson.toJson(this, writer);
        } catch (Exception e) {
            System.out.println("Error interno al guardar: " + e.getMessage());
        }
    }

    public static Biblioteca cargar() {
        try (FileReader reader = new FileReader(FILE)) {
            Gson gson = crearGson();
            Biblioteca b = gson.fromJson(reader, Biblioteca.class);
            if (b != null) return b;
        } catch (Exception e) {
        }
        return new Biblioteca();
    }
}