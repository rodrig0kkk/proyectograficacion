
package com.biblioteca.controller;

import java.util.Map;
import com.biblioteca.model.*;

public class GestorPersona {

    private Biblioteca biblioteca;

    public GestorPersona(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public int registrarAlumno(String nombre, String apellido, String correo, int telefono, int matricula) {
        Alumno a = new Alumno("Alumno", nombre, apellido, correo, telefono, 0, 0.0, matricula);
        return biblioteca.agregarPersona(a);
    }

    public int registrarProfesor(String nombre, String apellido, String correo, int telefono, int empleados) {
        Profesor p = new Profesor("Profesor", nombre, apellido, correo, telefono, 0, 0.0, empleados);
        return biblioteca.agregarPersona(p);
    }

    public Map<Integer, Persona> getPersonas() {
        return biblioteca.getPersonas();
    }

    public Persona getPersona(int id) {
        return biblioteca.getPersona(id);
    }
    
    public String listarPersonas() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Persona> entry : biblioteca.getPersonas().entrySet()) {
            sb.append("ID [").append(entry.getKey()).append("] ").append(entry.getValue().toString()).append("\n");
        }
        return sb.toString();
    }

   
    public boolean eliminarPersona(int id) {
        Persona p = biblioteca.getPersona(id);
        if (p != null) {
            biblioteca.eliminarPersona(id); 
            return true;
        }
        return false; 
    }
}
