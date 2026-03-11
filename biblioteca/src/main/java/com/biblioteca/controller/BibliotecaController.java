package com.biblioteca.controller;

import java.time.LocalDate;
import java.util.Map;

import com.biblioteca.model.Material;
import com.biblioteca.model.Persona;
import com.biblioteca.model.Prestamo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class BibliotecaController {

    private final GestorBiblioteca gestor = new GestorBiblioteca();

    @FXML private VBox panelMenuPrincipal, panelPersona, panelMaterial, panelPrestamo, panelListados;
    @FXML private ComboBox<String> cmbTipoPersona, cmbTipoMaterial;
    @FXML private TextField txtNombre, txtApellido, txtCorreo, txtTelefono, txtDatoExtraPersona;
    @FXML private TextField txtCodigoMaterial, txtTitulo, txtAutor, txtAnio, txtEditorial;
    @FXML private TextField txtIdPersonaPrestamo, txtCodigoMaterialPrestamo;
    @FXML private ListView<String> listViewDatos;

    @FXML
    public void initialize() {
        cmbTipoPersona.getItems().addAll("Alumno", "Profesor");
        cmbTipoMaterial.getItems().addAll("Libro", "Revista");
    }

    private void ocultarPaneles() {
        panelMenuPrincipal.setVisible(false); panelPersona.setVisible(false);
        panelMaterial.setVisible(false); panelPrestamo.setVisible(false);
        panelListados.setVisible(false);
    }

    @FXML public void volverMenu() { ocultarPaneles(); panelMenuPrincipal.setVisible(true); panelMenuPrincipal.toFront(); }
    @FXML public void mostrarPanelPersona() { ocultarPaneles(); panelPersona.setVisible(true); panelPersona.toFront(); }
    @FXML public void mostrarPanelMaterial() { ocultarPaneles(); panelMaterial.setVisible(true); panelMaterial.toFront(); }
    @FXML public void mostrarPanelPrestamo() { ocultarPaneles(); panelPrestamo.setVisible(true); panelPrestamo.toFront(); }
    @FXML public void mostrarPanelListados() { 
        ocultarPaneles(); listViewDatos.getItems().clear(); 
        panelListados.setVisible(true); panelListados.toFront(); 
    }
    @FXML public void clicSalir() { System.exit(0); }

    @FXML public void guardarPersona() {
        try {
            String tipo = cmbTipoPersona.getValue();
            if (tipo == null) throw new Exception("Selecciona un tipo de persona.");
            int tel = Integer.parseInt(txtTelefono.getText());
            int extra = Integer.parseInt(txtDatoExtraPersona.getText());
            int id = tipo.equals("Alumno") ? gestor.registrarAlumno(txtNombre.getText(), txtApellido.getText(), txtCorreo.getText(), tel, extra)
                                           : gestor.registrarProfesor(txtNombre.getText(), txtApellido.getText(), txtCorreo.getText(), tel, extra);
            mostrarAlerta("Éxito", "Persona guardada. ID: " + id);
            txtNombre.clear(); txtApellido.clear(); txtCorreo.clear(); txtTelefono.clear(); txtDatoExtraPersona.clear();
            volverMenu();
        } catch (Exception e) { mostrarAlerta("Error", "Revise los datos numéricos o vacíos."); }
    }

    @FXML public void guardarMaterial() {
        try {
            String tipo = cmbTipoMaterial.getValue();
            if (tipo == null) throw new Exception("Selecciona un tipo.");
            int anio = Integer.parseInt(txtAnio.getText());
            if (tipo.equals("Libro")) gestor.registrarLibro(txtCodigoMaterial.getText(), txtAutor.getText(), txtTitulo.getText(), anio, txtEditorial.getText());
            else gestor.registrarRevista(txtCodigoMaterial.getText(), txtAutor.getText(), txtTitulo.getText(), anio);
            mostrarAlerta("Éxito", "Material guardado.");
            txtCodigoMaterial.clear(); txtTitulo.clear(); txtAutor.clear(); txtAnio.clear(); txtEditorial.clear();
            volverMenu();
        } catch (Exception e) { mostrarAlerta("Error", "Revise los datos numéricos."); }
    }

    @FXML public void registrarPrestamo() {
        try {
            LocalDate fecha = gestor.registrarPrestamo(Integer.parseInt(txtIdPersonaPrestamo.getText()), txtCodigoMaterialPrestamo.getText());
            mostrarAlerta("Éxito", "Préstamo registrado. Vence: " + fecha);
            txtIdPersonaPrestamo.clear(); txtCodigoMaterialPrestamo.clear();
            volverMenu();
        } catch (Exception e) { mostrarAlerta("Error", e.getMessage()); }
    }

    @FXML public void verPersonas() {
        listViewDatos.getItems().clear();
        for (Map.Entry<Integer, Persona> entry : gestor.getPersonas().entrySet())
            listViewDatos.getItems().add("ID [" + entry.getKey() + "] " + entry.getValue().toString());
    }

    @FXML public void verMateriales() {
        listViewDatos.getItems().clear();
        for (Map.Entry<String, Material> entry : gestor.getMateriales().entrySet())
            listViewDatos.getItems().add("COD [" + entry.getKey() + "] " + entry.getValue().toString());
    }

    @FXML public void verPrestamos() {
        listViewDatos.getItems().clear();
        for (Prestamo p : gestor.getPrestamos())
            listViewDatos.getItems().add("Préstamo #" + p.getId() + " | Mat: " + p.getCodigo() + " | Vence: " + p.getFechaRegreso());
    }

    private void mostrarAlerta(String titulo, String msj) {
        Alert a = new Alert(Alert.AlertType.INFORMATION); a.setTitle(titulo); a.setHeaderText(null); a.setContentText(msj); a.showAndWait();
    }
}