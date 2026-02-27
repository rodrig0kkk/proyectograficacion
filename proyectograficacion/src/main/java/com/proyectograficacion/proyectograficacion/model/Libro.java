package com.proyectograficacion.proyectograficacion.model;

public class Libro extends Material {

    private String editorial;

    public Libro(String tipoMaterial, String codigo, String autor, String titulo, int anio, String status, String editorial) {
        super(tipoMaterial, codigo, autor, titulo, anio, status);
        this.editorial = editorial;
    }


    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return super.toString() + ", editorial='" + editorial + "'";
    }
}
