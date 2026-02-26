package com.trustcodedev.mecanica.test;

import com.trustcodedev.mecanica.model.Empleados;
import com.trustcodedev.mecanica.controller.GestorEmpleados;
import com.trustcodedev.mecanica.controller.Administrador;
import com.trustcodedev.mecanica.model.Proyecto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmpleadosTest {

    private GestorEmpleados gestor;
    private Administrador admin;
    private Empleados e1, e2;
    private Proyecto p1, p2;

    @BeforeEach
    public void setup() {
        gestor = new GestorEmpleados();
        admin = new Administrador();

        e1 = new Empleados();
        e1.setCargo("Mecanico");
        e1.setNombre("Juan");
        e1.setApellido("Perez");
        e1.setEdad(30);
        e1.setSueldo(3500);
        e1.setNumero(1);

        e2 = new Empleados();
        e2.setCargo("Contador");
        e2.setNombre("Maria");
        e2.setApellido("Lopez");
        e2.setEdad(28);
        e2.setSueldo(3200);
        e2.setNumero(2);

        gestor.agregarEmpleado(e1);
        gestor.agregarEmpleado(e2);

        p1 = new Proyecto("Proyecto Alpha");
        p1.setEmpleados(1);
        List<Empleados> listaP1 = new ArrayList<>();
        listaP1.add(e1);
        p1.setEmpleadosAsignados(listaP1);

        p2 = new Proyecto("Proyecto Beta");
        p2.setEmpleados(1);
        List<Empleados> listaP2 = new ArrayList<>();
        listaP2.add(e2);
        p2.setEmpleadosAsignados(listaP2);

        admin.agregarProyecto(p1);
        admin.agregarProyecto(p2);
    }

    @Test
    public void testEmpleadoExiste() {
        assertTrue(gestor.existeEmpleado("Juan"));
        assertTrue(gestor.existeEmpleado("Maria"));
        assertFalse(gestor.existeEmpleado("Carlos"));
    }

    @Test
    public void testObtenerEmpleado() {
        Empleados emp = gestor.obtenerEmpleado("Juan");
        assertNotNull(emp);
        assertEquals("Perez", emp.getApellido());

        Empleados noEmp = gestor.obtenerEmpleado("Carlos");
        assertNull(noEmp);
    }

    @Test
    public void testProyectosDeEmpleado() {
        
        List<String> proyectosJuan = new ArrayList<>();
        for (Proyecto p : admin.getProyectos()) {
            if (p.getEmpleadosAsignados().contains(e1)) {
                proyectosJuan.add(p.getNombre());
            }
        }
        assertEquals(1, proyectosJuan.size());
        assertEquals("Proyecto Alpha", proyectosJuan.get(0));

        List<String> proyectosNoAsignado = new ArrayList<>();
        Empleados e3 = new Empleados();
        e3.setNombre("Carlos");
        for (Proyecto p : admin.getProyectos()) {
            if (p.getEmpleadosAsignados().contains(e3)) {
                proyectosNoAsignado.add(p.getNombre());
            }
        }
        assertTrue(proyectosNoAsignado.isEmpty());
    }

    @Test
    public void testAgregarEmpleado() {
        Empleados e4 = new Empleados();
        e4.setNombre("Ana");
        gestor.agregarEmpleado(e4);
        assertTrue(gestor.existeEmpleado("Ana"));
    }
}
