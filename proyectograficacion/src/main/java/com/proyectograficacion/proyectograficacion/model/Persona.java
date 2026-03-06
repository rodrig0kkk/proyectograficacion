package com.proyectograficacion.proyectograficacion.model;

public class Persona {

    private String tipoPersona;
    private String nombre;
    private String apellido;
    private String correo;
    private int telefono;
    private int numLibros;
    private double deuda;

    public Persona(String tipoPersona, String nombre, String apellido, String correo,
                   int telefono, int numLibros, double deuda) {
        this.tipoPersona = tipoPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.numLibros = numLibros;
        this.deuda = deuda;
    }

    public String getTipoPersona() { return tipoPersona; }
    public void setTipoPersona(String tipoPersona) { this.tipoPersona = tipoPersona; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono; }
    public int getNumLibros() { return numLibros; }
    public void setNumLibros(int numLibros) { this.numLibros = numLibros; }
    public double getDeuda() { return deuda; }
    public void setDeuda(double deuda) { this.deuda = deuda; }

    @Override
    public String toString() {
        return "Tipo: " + getTipoPersona() + " | Nombre: " + getNombre() + " " + getApellido() + " | Correo: " + getCorreo() + " | Tel: " + getTelefono();
    }
}