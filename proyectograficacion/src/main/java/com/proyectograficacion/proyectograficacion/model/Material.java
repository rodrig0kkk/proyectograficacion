package com.proyectograficacion.proyectograficacion.model;

public class Material {

    private String tipoMaterial;
    private String codigo;
    private String autor;
    private String titulo;
    private int anio;
    private String status;

    public Material(String tipoMaterial, String codigo, String autor, String titulo, int anio, String status) {
        this.tipoMaterial = tipoMaterial;
        this.codigo = codigo;
        this.autor = autor;
        this.titulo = titulo;
        this.anio = anio;
        this.status = status;
    }

    public String getTipoMaterial() { return tipoMaterial; }
    public void setTipoMaterial(String tipoMaterial) { this.tipoMaterial = tipoMaterial; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Tipo: " + getTipoMaterial() + " | Titulo: " + getTitulo() + " | Autor: " + getAutor() + " | Anio: " + getAnio() + " | Estado: " + getStatus();
    }
}