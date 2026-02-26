package com.proyectograficacion.proyectograficacion.model;

public class Profesor extends Persona {

    private int numEmpleado;

    public Profesor(String tipoPersona, String nombre, String apellido, String correo,
                    int telefono, int numLibros, double deuda, int numEmpleado) {
        super(tipoPersona, nombre, apellido, correo, telefono, numLibros, deuda);
        this.numEmpleado = numEmpleado;
    }

    public int getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    @Override
    public String toString() {
        return super.toString() + ", numEmpleado=" + numEmpleado;
    }
}
