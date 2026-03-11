package com.biblioteca.model;

public class Alumno extends Persona {

    private int numMatricula;

    public Alumno(String tipoPersona, String nombre, String apellido, String correo, int telefono, int numLibros, double deuda, int numMatricula) {
        super(tipoPersona, nombre, apellido, correo, telefono, numLibros, deuda);
        this.numMatricula = numMatricula;
    }

    public int getNumMatricula() { return numMatricula; }
    public void setNumMatricula(int numMatricula) { this.numMatricula = numMatricula; }

    @Override
    public String toString() {
        return super.toString() + " | Matrícula: " + numMatricula;
    }
}