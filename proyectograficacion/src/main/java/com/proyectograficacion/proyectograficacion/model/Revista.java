package com.proyectograficacion.proyectograficacion.model;

public class Revista extends Material {

    public Revista(String tipoMaterial, String codigo, String autor, String titulo, int anio, String status) {
        super(tipoMaterial, codigo, autor, titulo, anio, status);
    }

    
    public void imprimirPortada() {
        System.out.println("Portada de la revista: " + getTitulo() + " de " + getAutor());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
