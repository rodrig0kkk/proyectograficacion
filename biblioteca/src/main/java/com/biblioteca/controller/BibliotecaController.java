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

    @FXML private VBox panelMenuPrincipal;
    @FXML private VBox panelMenuPersonas, panelMenuMateriales, panelMenuPrestamos;
    @FXML private VBox panelPersona, panelMaterial, panelPrestamo;
    @FXML private VBox panelListadoPersonas, panelListadoMateriales, panelListadoPrestamos;

    @FXML private ComboBox<String> cmbTipoPersona, cmbTipoMaterial;
    @FXML private TextField txtNombre, txtApellido, txtCorreo, txtTelefono, txtDatoExtraPersona;
    @FXML private TextField txtCodigoMaterial, txtTitulo, txtAutor, txtAnio, txtEditorial;
    @FXML private TextField txtIdPersonaPrestamo, txtCodigoMaterialPrestamo;
    
    @FXML private ListView<String> listPersonas, listMateriales, listPrestamos;

    @FXML private Button btnMenuPersonas, btnMenuMateriales, btnMenuPrestamos, btnSalir;
    @FXML private Button btnIrAgregarPersona, btnIrListarPersonas, btnVolverPrincipalDesdePersonas;
    @FXML private Button btnIrAgregarMaterial, btnIrListarMateriales, btnVolverPrincipalDesdeMateriales;
    @FXML private Button btnIrAgregarPrestamo, btnIrListarPrestamos, btnVolverPrincipalDesdePrestamos;
    @FXML private Button btnGuardarPersona, btnVolverPersona, btnEliminarPersona, btnVolverListadoPersonas;
    @FXML private Button btnGuardarMaterial, btnVolverMaterial, btnEliminarMaterial, btnVolverListadoMateriales;
    @FXML private Button btnRegistrarPrestamo, btnVolverPrestamo, btnEliminarPrestamo, btnVolverListadoPrestamos;

    @FXML
    public void initialize() {
        cmbTipoPersona.getItems().addAll("Alumno", "Profesor");
        cmbTipoMaterial.getItems().addAll("Libro", "Revista");

        // --- NAVEGACIÓN: Menú Principal -> Submenús ---
        btnMenuPersonas.setOnAction(e -> mostrarPanel(panelMenuPersonas));
        btnMenuMateriales.setOnAction(e -> mostrarPanel(panelMenuMateriales));
        btnMenuPrestamos.setOnAction(e -> mostrarPanel(panelMenuPrestamos));
        btnSalir.setOnAction(e -> System.exit(0));

        btnVolverPrincipalDesdePersonas.setOnAction(e -> mostrarPanel(panelMenuPrincipal));
        btnVolverPrincipalDesdeMateriales.setOnAction(e -> mostrarPanel(panelMenuPrincipal));
        btnVolverPrincipalDesdePrestamos.setOnAction(e -> mostrarPanel(panelMenuPrincipal));

        // --- NAVEGACIÓN: Gestor de Personas ---
        btnIrAgregarPersona.setOnAction(e -> mostrarPanel(panelPersona));
        btnVolverPersona.setOnAction(e -> mostrarPanel(panelMenuPersonas));
        btnIrListarPersonas.setOnAction(e -> { verPersonas(); mostrarPanel(panelListadoPersonas); });
        btnVolverListadoPersonas.setOnAction(e -> mostrarPanel(panelMenuPersonas));

        // --- NAVEGACIÓN: Gestor de Materiales ---
        btnIrAgregarMaterial.setOnAction(e -> mostrarPanel(panelMaterial));
        btnVolverMaterial.setOnAction(e -> mostrarPanel(panelMenuMateriales));
        btnIrListarMateriales.setOnAction(e -> { verMateriales(); mostrarPanel(panelListadoMateriales); });
        btnVolverListadoMateriales.setOnAction(e -> mostrarPanel(panelMenuMateriales));

        // --- NAVEGACIÓN: Gestor de Préstamos ---
        btnIrAgregarPrestamo.setOnAction(e -> mostrarPanel(panelPrestamo));
        btnVolverPrestamo.setOnAction(e -> mostrarPanel(panelMenuPrestamos));
        btnIrListarPrestamos.setOnAction(e -> { verPrestamos(); mostrarPanel(panelListadoPrestamos); });
        btnVolverListadoPrestamos.setOnAction(e -> mostrarPanel(panelMenuPrestamos));

        // --- ACCIONES: Guardar ---
        btnGuardarPersona.setOnAction(e -> guardarPersona());
        btnGuardarMaterial.setOnAction(e -> guardarMaterial());
        btnRegistrarPrestamo.setOnAction(e -> registrarPrestamo());

        // --- ACCIONES: Eliminar ---
        btnEliminarPersona.setOnAction(e -> eliminarPersona());
        btnEliminarMaterial.setOnAction(e -> eliminarMaterial());
        btnEliminarPrestamo.setOnAction(e -> eliminarPrestamo());
    }

    // ==========================================
    // MÉTODO MAESTRO DE NAVEGACIÓN
    // ==========================================
    private void mostrarPanel(VBox panelDestino) {
        // Apaga todos
        panelMenuPrincipal.setVisible(false);
        panelMenuPersonas.setVisible(false); panelMenuMateriales.setVisible(false); panelMenuPrestamos.setVisible(false);
        panelPersona.setVisible(false); panelMaterial.setVisible(false); panelPrestamo.setVisible(false);
        panelListadoPersonas.setVisible(false); panelListadoMateriales.setVisible(false); panelListadoPrestamos.setVisible(false);
        
        // Enciende el solicitado
        panelDestino.setVisible(true);
        panelDestino.toFront();
    }

    // ==========================================
    // LÓGICA DE GUARDAR (Igual que antes)
    // ==========================================
    private void guardarPersona() {
        try {
            String tipo = cmbTipoPersona.getValue();
            if (tipo == null) throw new Exception("Selecciona un tipo de persona.");
            int id = tipo.equals("Alumno") ? gestorPersona.registrarAlumno(txtNombre.getText(), txtApellido.getText(), txtCorreo.getText(), Integer.parseInt(txtTelefono.getText()), Integer.parseInt(txtDatoExtraPersona.getText()))
                                           : gestorPersona.registrarProfesor(txtNombre.getText(), txtApellido.getText(), txtCorreo.getText(), Integer.parseInt(txtTelefono.getText()), Integer.parseInt(txtDatoExtraPersona.getText()));
            mostrarAlerta("Éxito", "Persona guardada. ID: " + id);
            txtNombre.clear(); txtApellido.clear(); txtCorreo.clear(); txtTelefono.clear(); txtDatoExtraPersona.clear();
            mostrarPanel(panelMenuPersonas);
        } catch (Exception e) { mostrarAlerta("Error", "Revise los datos numéricos o vacíos."); }
    }

    private void guardarMaterial() {
        try {
            String tipo = cmbTipoMaterial.getValue();
            if (tipo == null) throw new Exception("Selecciona un tipo.");
            int anio = Integer.parseInt(txtAnio.getText());
            if (tipo.equals("Libro")) gestorMaterial.registrarLibro(txtCodigoMaterial.getText(), txtAutor.getText(), txtTitulo.getText(), anio, txtEditorial.getText());
            else gestorMaterial.registrarRevista(txtCodigoMaterial.getText(), txtAutor.getText(), txtTitulo.getText(), anio);
            mostrarAlerta("Éxito", "Material guardado.");
            txtCodigoMaterial.clear(); txtTitulo.clear(); txtAutor.clear(); txtAnio.clear(); txtEditorial.clear();
            mostrarPanel(panelMenuMateriales);
        } catch (Exception e) { mostrarAlerta("Error", "Revise los datos numéricos."); }
    }

    private void registrarPrestamo() {
        try {
            LocalDate fecha = gestorPrestamo.registrarPrestamo(gestorPersona.getPersona(Integer.parseInt(txtIdPersonaPrestamo.getText())), gestorMaterial.getMaterial(txtCodigoMaterialPrestamo.getText()));
            mostrarAlerta("Éxito", "Préstamo registrado. Vence: " + fecha);
            txtIdPersonaPrestamo.clear(); txtCodigoMaterialPrestamo.clear();
            mostrarPanel(panelMenuPrestamos);
        } catch (Exception e) { mostrarAlerta("Error", e.getMessage()); }
    }

    // ==========================================
    // LÓGICA DE VER (Rellenar Listas)
    // ==========================================
    private void verPersonas() {
        listPersonas.getItems().clear();
        for (Map.Entry<Integer, Persona> entry : gestorPersona.getPersonas().entrySet())
            listPersonas.getItems().add("ID [" + entry.getKey() + "] " + entry.getValue().toString());
    }

    private void verMateriales() {
        listMateriales.getItems().clear();
        for (Map.Entry<String, Material> entry : gestorMaterial.getMateriales().entrySet())
            listMateriales.getItems().add("COD [" + entry.getKey() + "] " + entry.getValue().toString());
    }

    private void verPrestamos() {
        listPrestamos.getItems().clear();
        for (Prestamo p : gestorPrestamo.getPrestamos())
            listPrestamos.getItems().add("Préstamo #" + p.getId() + " | Mat: " + p.getCodigo() + " | Vence: " + p.getFechaRegreso());
    }

    // ==========================================
    // LÓGICA DE ELIMINAR (Interactiva)
    // ==========================================
    private void eliminarPersona() {
        String seleccion = listPersonas.getSelectionModel().getSelectedItem();
        if (seleccion == null) { mostrarAlerta("Aviso", "Selecciona una persona de la lista."); return; }
        try {
            // Extrae el ID que está entre los corchetes "ID [1] ..."
            int id = Integer.parseInt(seleccion.substring(seleccion.indexOf("[") + 1, seleccion.indexOf("]")));
            if (gestorPersona.eliminarPersona(id)) {
                mostrarAlerta("Éxito", "Persona eliminada.");
                verPersonas(); // Refresca la lista visualmente
            }
        } catch (Exception e) { mostrarAlerta("Error", "No se pudo eliminar."); }
    }

    private void eliminarMaterial() {
        String seleccion = listMateriales.getSelectionModel().getSelectedItem();
        if (seleccion == null) { mostrarAlerta("Aviso", "Selecciona un material de la lista."); return; }
        try {
            // Extrae el código que está entre los corchetes "COD [A1] ..."
            String codigo = seleccion.substring(seleccion.indexOf("[") + 1, seleccion.indexOf("]"));
            if (gestorMaterial.eliminarMaterial(codigo)) {
                mostrarAlerta("Éxito", "Material eliminado.");
                verMateriales(); // Refresca la lista visualmente
            }
        } catch (Exception e) { mostrarAlerta("Error", "No se pudo eliminar."); }
    }

    private void eliminarPrestamo() {
        String seleccion = listPrestamos.getSelectionModel().getSelectedItem();
        if (seleccion == null) { mostrarAlerta("Aviso", "Selecciona un préstamo de la lista."); return; }
        try {
            // Extrae el ID que está después del numeral "Préstamo #1 | ..."
            int id = Integer.parseInt(seleccion.substring(seleccion.indexOf("#") + 1, seleccion.indexOf(" |")));
            if (gestorPrestamo.eliminarPrestamo(id)) {
                mostrarAlerta("Éxito", "Préstamo eliminado y material devuelto.");
                verPrestamos(); // Refresca la lista visualmente
            }
        } catch (Exception e) { mostrarAlerta("Error", "No se pudo eliminar."); }
    }

    private void mostrarAlerta(String titulo, String msj) {
        Alert a = new Alert(Alert.AlertType.INFORMATION); a.setTitle(titulo); a.setHeaderText(null); a.setContentText(msj); a.showAndWait();
    }
}