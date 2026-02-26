package com.proyectograficacion.proyectograficacion.model;

import java.time.LocalDate;

public class Prestamo {

    private static int contador = 1;

    private int id;                // ID automático
    private String codigo;         // Código del préstamo
    private Persona persona;       // Persona que toma el préstamo
    private Material material;     // Material prestado
    private LocalDate fechaSalida; // Fecha de salida
    private LocalDate fechaRegreso;// Fecha de regreso
    private double cuota;          // Por ahora opcional / futura implementación

    public Prestamo(String codigo, Persona persona, Material material, LocalDate fechaRegreso, double cuota) {
        this.id = contador++;
        this.codigo = codigo;
        this.persona = persona;
        this.material = material;
        this.fechaSalida = LocalDate.now();
        this.fechaRegreso = fechaRegreso;
        this.cuota = cuota;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(LocalDate fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }
    
public Prestamo(Persona persona, Material material) {
    this.id = contador++;
    this.codigo = "PRE" + id;   
    this.persona = persona;
    this.material = material;
    this.fechaSalida = LocalDate.now();
    this.fechaRegreso = LocalDate.now().plusDays(7); 
    this.cuota = 0;
}

}
