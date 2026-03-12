
package com.biblioteca.controller;

import java.util.Map;
import com.biblioteca.model.*;

public class GestorMaterial {

    private Biblioteca biblioteca;

    public GestorMaterial(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void registrarLibro(String codigo, String autor, String titulo, int anio, String editorial) {
        Libro libro = new Libro("Libro", codigo, autor, titulo, anio, "Disponible", editorial);
        biblioteca.agregarMaterial(libro);
    }

    public void registrarRevista(String codigo, String autor, String titulo, int anio) {
        Revista revista = new Revista("Revista", codigo, autor, titulo, anio, "Disponible");
        biblioteca.agregarMaterial(revista);
    }

    public Map<String, Material> getMateriales() {
        return biblioteca.getMateriales();
    }

    public Material getMaterial(String codigo) {
        return biblioteca.getMaterial(codigo);
    }

   
    public String listarMateriales() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Material> entry : biblioteca.getMateriales().entrySet()) {
            Material m = entry.getValue();
            sb.append("COD [").append(entry.getKey()).append("] ")
              .append(m.getTipoMaterial()).append(" | ")
              .append(m.getTitulo()).append(" | ")
              .append(m.getAutor()).append(" | ")
              .append("Estado: ").append(m.getStatus()).append("\n");
        }
        return sb.toString();
    }

    
    public boolean eliminarMaterial(String codigo) {
        Material m = biblioteca.getMaterial(codigo);
        if (m != null) {
            biblioteca.getMateriales().remove(codigo);
            return true;
        }
        return false; 
    }
}
