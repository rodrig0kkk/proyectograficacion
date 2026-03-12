package com.biblioteca.controller;

import java.time.LocalDate;
import java.util.Map;

import com.biblioteca.model.Biblioteca;
import com.biblioteca.model.Material;
import com.biblioteca.model.Persona;
import com.biblioteca.model.Prestamo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class BibliotecaController {
    private final Biblioteca biblioteca = Biblioteca.cargar();
   private final GestorPersona gestorPersona = new GestorPersona(biblioteca);
   private final GestorMaterial gestorMaterial = new GestorMaterial(biblioteca);
   private final GestorPrestamo gestorPrestamo = new GestorPrestamo(biblioteca);

    @FXML private VBox panelMenuPrincipal, panelPersona, panelMaterial, panelPrestamo, panelListados;
    @FXML private VBox panelGestorPersona;
    @FXML private VBox panelNuevaPersona;
    @FXML private ComboBox<String> cmbTipoPersona;
    @FXML private TextField txtNombre, txtApellido, txtCorreo, txtTelefono, txtDatoExtraPersona;
    @FXML private Button btnGuardarPersona, btnVolverPersona;
    @FXML private ComboBox<String> cmbTipoMaterial;
    @FXML private TextField txtCodigoMaterial, txtTitulo, txtAutor, txtAnio, txtEditorial;
    @FXML private Button btnGuardarMaterial, btnVolverMaterial;
    @FXML private TextField txtIdPersonaPrestamo, txtCodigoMaterialPrestamo;
    @FXML private Button btnRegistrarPrestamo, btnVolverPrestamo;
    @FXML private ListView<String> listViewDatos;
    @FXML private Button btnVerPersonas, btnVerMateriales, btnVerPrestamos, btnVolverListados;
    @FXML private Button btnNuevoMaterial, btnNuevaPersona, btnPrestarMaterial, btnVerListados, btnSalir;

    @FXML
    public void initialize() {
        cmbTipoPersona.getItems().addAll("Alumno", "Profesor");
        cmbTipoMaterial.getItems().addAll("Libro", "Revista");
        btnNuevoMaterial.setOnAction(e -> mostrarPanelMaterial()); 
        btnPrestarMaterial.setOnAction(e -> mostrarPanelPrestamo());
        btnVerListados.setOnAction(e -> mostrarPanelListados());
        btnSalir.setOnAction(e -> System.exit(0));
        btnGuardarPersona.setOnAction(e -> guardarPersona());
        btnVolverPersona.setOnAction(e -> volverMenu());
        btnGuardarMaterial.setOnAction(e -> guardarMaterial());
        btnVolverMaterial.setOnAction(e -> volverMenu());
        btnRegistrarPrestamo.setOnAction(e -> registrarPrestamo());
        btnVolverPrestamo.setOnAction(e -> volverMenu());
        btnVerPersonas.setOnAction(e -> verPersonas());
        btnVerMateriales.setOnAction(e -> verMateriales());
        btnVerPrestamos.setOnAction(e -> verPrestamos());
        btnVolverListados.setOnAction(e -> volverMenu());
    }

    private void ocultarPaneles() {
        panelMenuPrincipal.setVisible(false);
        panelPersona.setVisible(false);
        panelMaterial.setVisible(false);
        panelPrestamo.setVisible(false);
        panelListados.setVisible(false);
    }

    private void volverMenu() {
        ocultarPaneles();
        panelMenuPrincipal.setVisible(true);
        panelMenuPrincipal.toFront();
    }

    private void mostrarPanelPersona() { ocultarPaneles(); panelPersona.setVisible(true); panelPersona.toFront(); }
    private void mostrarPanelMaterial() { ocultarPaneles(); panelMaterial.setVisible(true); panelMaterial.toFront(); }
    private void mostrarPanelPrestamo() { ocultarPaneles(); panelPrestamo.setVisible(true); panelPrestamo.toFront(); }
    private void mostrarPanelListados() { ocultarPaneles(); listViewDatos.getItems().clear(); panelListados.setVisible(true); panelListados.toFront(); }


    private void guardarPersona() {
        try {
            String tipo = cmbTipoPersona.getValue();
            if (tipo == null) throw new Exception("Selecciona un tipo de persona.");

            int tel = Integer.parseInt(txtTelefono.getText());
            int extra = Integer.parseInt(txtDatoExtraPersona.getText());

            int id;
            if (tipo.equals("Alumno")) {
                id = gestorPersona.registrarAlumno(txtNombre.getText(), txtApellido.getText(), txtCorreo.getText(), tel, extra);
            } else {
                id = gestorPersona.registrarProfesor(txtNombre.getText(), txtApellido.getText(), txtCorreo.getText(), tel, extra);
            }

            mostrarAlerta("Éxito", "Persona guardada. ID: " + id);
            txtNombre.clear(); txtApellido.clear(); txtCorreo.clear(); txtTelefono.clear(); txtDatoExtraPersona.clear();
            volverMenu();
        } catch (Exception e) {
            mostrarAlerta("Error", "Revise los datos numéricos o vacíos.");
        }
    }

    private void guardarMaterial() {
        try {
            String tipo = cmbTipoMaterial.getValue();
            if (tipo == null) throw new Exception("Selecciona un tipo.");

            int anio = Integer.parseInt(txtAnio.getText());

            if (tipo.equals("Libro")) {
                gestorMaterial.registrarLibro(txtCodigoMaterial.getText(), txtAutor.getText(), txtTitulo.getText(), anio, txtEditorial.getText());
            } else {
                gestorMaterial.registrarRevista(txtCodigoMaterial.getText(), txtAutor.getText(), txtTitulo.getText(), anio);
            }

            mostrarAlerta("Éxito", "Material guardado.");
            txtCodigoMaterial.clear(); txtTitulo.clear(); txtAutor.clear(); txtAnio.clear(); txtEditorial.clear();
            volverMenu();
        } catch (Exception e) {
            mostrarAlerta("Error", "Revise los datos numéricos.");
        }
    }

    private void registrarPrestamo() {
        try {
            int idPersona = Integer.parseInt(txtIdPersonaPrestamo.getText());
            String codigoMaterial = txtCodigoMaterialPrestamo.getText();

            Persona persona = gestorPersona.getPersona(idPersona);
            Material material = gestorMaterial.getMaterial(codigoMaterial);

            LocalDate fecha = gestorPrestamo.registrarPrestamo(persona, material);

            mostrarAlerta("Éxito", "Préstamo registrado. Vence: " + fecha);
            txtIdPersonaPrestamo.clear(); txtCodigoMaterialPrestamo.clear();
            volverMenu();
        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage());
        }
    }

    private void verPersonas() {
        listViewDatos.getItems().clear();
        for (Map.Entry<Integer, Persona> entry : gestorPersona.getPersonas().entrySet()) {
            listViewDatos.getItems().add("ID [" + entry.getKey() + "] " + entry.getValue().toString());
        }
    }

    private void verMateriales() {
        listViewDatos.getItems().clear();
        for (Map.Entry<String, Material> entry : gestorMaterial.getMateriales().entrySet()) {
            listViewDatos.getItems().add("COD [" + entry.getKey() + "] " + entry.getValue().toString());
        }
    }

    private void verPrestamos() {
        listViewDatos.getItems().clear();
        for (Prestamo p : gestorPrestamo.getPrestamos()) {
            listViewDatos.getItems().add("Préstamo #" + p.getId() + " | Mat: " + p.getCodigo() + " | Vence: " + p.getFechaRegreso());
        }
    }

    private void mostrarAlerta(String titulo, String msj) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(msj);
        a.showAndWait();
    }
}
