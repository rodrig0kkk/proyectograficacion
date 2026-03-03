package com.proyectograficacion.proyectograficacion.model;

import java.time.LocalDate;

public class Prestamo {

    private int id;                
    private String codigo;         
    private Persona persona;       
    private Material material;     
    private LocalDate fechaSalida; 
    private LocalDate fechaRegreso;
    private double cuota;          

    public Prestamo(String codigo, Persona persona, Material material, LocalDate fechaRegreso, double cuota) {
        this.codigo = codigo;
        this.persona = persona;
        this.material = material;
        this.fechaSalida = LocalDate.now();
        this.fechaRegreso = fechaRegreso;
        this.cuota = cuota;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }
    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }
    public LocalDate getFechaRegreso() { return fechaRegreso; }
    public void setFechaRegreso(LocalDate fechaRegreso) { this.fechaRegreso = fechaRegreso; }
    public double getCuota() { return cuota; }
    public void setCuota(double cuota) { this.cuota = cuota; }

    @Override
    public String toString() {
        return "Prestamo ID: " + getId() + 
               " | Material: " + getMaterial().getTitulo() + 
               " | Persona: " + getPersona().getNombre() + " " + getPersona().getApellido() + 
               " | Fecha Regreso: " + getFechaRegreso();
    }
}