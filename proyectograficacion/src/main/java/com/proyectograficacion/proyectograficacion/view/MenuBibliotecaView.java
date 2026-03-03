package com.proyectograficacion.proyectograficacion.view;

import com.proyectograficacion.proyectograficacion.controller.GestorBiblioteca;
import com.proyectograficacion.proyectograficacion.model.Material;
import com.proyectograficacion.proyectograficacion.model.Persona;
import com.proyectograficacion.proyectograficacion.model.Prestamo;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class MenuBibliotecaView {

    private GestorBiblioteca gestor;
    private Stage stagePrincipal;

    public MenuBibliotecaView(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
        this.gestor = new GestorBiblioteca();
    }

    public void mostrar() {
        Label titulo = new Label("SISTEMA BIBLIOTECA");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button btnPersona = new Button("Añadir Persona");
        Button btnMaterial = new Button("Añadir Material");
        Button btnPrestamo = new Button("Registrar Préstamo");
        Button btnVerMaterial = new Button("Ver Materiales");
        Button btnVerPersona = new Button("Ver Personas");
        Button btnVerPrestamo = new Button("Ver Préstamos");

        btnPersona.setOnAction(e -> ventanaPersona());
        btnMaterial.setOnAction(e -> ventanaMaterial());
        btnPrestamo.setOnAction(e -> ventanaPrestamo());

        btnVerMaterial.setOnAction(e -> ventanaVerMateriales());
        btnVerPersona.setOnAction(e -> ventanaVerPersonas());
        btnVerPrestamo.setOnAction(e -> ventanaVerPrestamos());

        VBox root = new VBox(15, titulo, btnPersona, btnMaterial,
                btnPrestamo, btnVerMaterial, btnVerPersona, btnVerPrestamo);

        root.getStyleClass().add("card");

        Scene scene = new Scene(root, 400, 450);
        scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());

        stagePrincipal.setTitle("Sistema Biblioteca MVC");
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }

    private void ventanaPersona() {
        Stage stage = new Stage();
        stage.setTitle("Agregar Persona");

        Label titulo = new Label("Seleccionar Tipo de Persona");
        titulo.getStyleClass().add("titulo");

        Button btnAlumno = new Button("Alumno");
        Button btnProfesor = new Button("Profesor");
        Button btnSalir = new Button("Salir");

        btnAlumno.setOnAction(e -> { stage.close(); ventanaAlumno(); });
        btnProfesor.setOnAction(e -> { stage.close(); ventanaProfesor(); });
        btnSalir.setOnAction(e -> stage.close());

        VBox root = new VBox(15, titulo, btnAlumno, btnProfesor, btnSalir);
        root.getStyleClass().add("card");

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    private void ventanaAlumno() {
        Stage stage = new Stage();
        stage.setTitle("Registrar Alumno");

        Label titulo = new Label("Registro de Alumno");
        titulo.getStyleClass().add("titulo");

        TextField nombre = new TextField();
        nombre.setPromptText("Nombre");

        TextField apellido = new TextField();
        apellido.setPromptText("Apellido");

        TextField correo = new TextField();
        correo.setPromptText("Correo Electrónico");

        TextField telefono = new TextField();
        telefono.setPromptText("Teléfono");

        TextField matricula = new TextField();
        matricula.setPromptText("Matrícula");

        Button guardar = new Button("Guardar");
        Button salir = new Button("Salir");

        guardar.setOnAction(e -> {
            try {
                int tel = Integer.parseInt(telefono.getText());
                int mat = Integer.parseInt(matricula.getText());
                gestor.registrarAlumno("Alumno", nombre.getText(), apellido.getText(), correo.getText(), tel, 0, 0.0, mat);
                mostrarAlerta("Éxito", "Alumno guardado correctamente.");
                stage.close();
            } catch (NumberFormatException ex) {
                mostrarAlerta("Error", "El teléfono y la matrícula deben ser números válidos.");
            }
        });

        salir.setOnAction(e -> stage.close());

        VBox root = new VBox(15, titulo, nombre, apellido, correo, telefono, matricula, guardar, salir);
        root.getStyleClass().add("card");

        Scene scene = new Scene(root, 400, 550);
        scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    private void ventanaProfesor() {
        Stage stage = new Stage();
        stage.setTitle("Registrar Profesor");

        Label titulo = new Label("Registro de Profesor");
        titulo.getStyleClass().add("titulo");

        TextField nombre = new TextField();
        nombre.setPromptText("Nombre");

        TextField apellido = new TextField();
        apellido.setPromptText("Apellido");

        TextField correo = new TextField();
        correo.setPromptText("Correo Electrónico");

        TextField telefono = new TextField();
        telefono.setPromptText("Teléfono");

        TextField numEmpleado = new TextField();
        numEmpleado.setPromptText("Número Empleado");

        Button guardar = new Button("Guardar");
        Button salir = new Button("Salir");

        guardar.setOnAction(e -> {
            try {
                int tel = Integer.parseInt(telefono.getText());
                int numEmp = Integer.parseInt(numEmpleado.getText());
                gestor.registrarProfesor("Profesor", nombre.getText(), apellido.getText(), correo.getText(), tel, 0, 0.0, numEmp);
                mostrarAlerta("Éxito", "Profesor guardado correctamente.");
                stage.close();
            } catch (NumberFormatException ex) {
                mostrarAlerta("Error", "El teléfono y el número de empleado deben ser numéricos.");
            }
        });

        salir.setOnAction(e -> stage.close());

        VBox root = new VBox(15, titulo, nombre, apellido, correo, telefono, numEmpleado, guardar, salir);
        root.getStyleClass().add("card");

        Scene scene = new Scene(root, 400, 550);
        scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    private void ventanaMaterial() {
        Stage stage = new Stage();
        stage.setTitle("Agregar Material");

        Label titulo = new Label("Seleccionar Tipo de Material");
        titulo.getStyleClass().add("titulo");

        Button btnLibro = new Button("Libro");
        Button btnRevista = new Button("Revista");
        Button btnSalir = new Button("Salir");

        btnLibro.setOnAction(e -> { stage.close(); ventanaLibro(); });
        btnRevista.setOnAction(e -> { stage.close(); ventanaRevista(); });
        btnSalir.setOnAction(e -> stage.close());

        VBox root = new VBox(15, titulo, btnLibro, btnRevista, btnSalir);
        root.getStyleClass().add("card");

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    private void ventanaLibro() {
        Stage stage = new Stage();
        stage.setTitle("Registrar Libro");

        Label titulo = new Label("Registro de Libro");
        titulo.getStyleClass().add("titulo");
        
        TextField codigo = new TextField();
        codigo.setPromptText("Código");

        TextField tituloLibro = new TextField();
        tituloLibro.setPromptText("Título");

        TextField autor = new TextField();
        autor.setPromptText("Autor");
        
        TextField anio = new TextField();
        anio.setPromptText("Año");

        TextField editorial = new TextField();
        editorial.setPromptText("Editorial");

        Button guardar = new Button("Guardar");
        Button salir = new Button("Salir");

        guardar.setOnAction(e -> {
            try {
                int anioInt = Integer.parseInt(anio.getText());
                gestor.registrarLibro("Libro", codigo.getText(), autor.getText(), tituloLibro.getText(), anioInt, "Disponible", editorial.getText());
                mostrarAlerta("Éxito", "Libro guardado correctamente.");
                stage.close();
            } catch (NumberFormatException ex) {
                mostrarAlerta("Error", "El año debe ser un número válido.");
            }
        });

        salir.setOnAction(e -> stage.close());

        VBox root = new VBox(15, titulo, codigo, tituloLibro, autor, anio, editorial, guardar, salir);
        root.getStyleClass().add("card");

        Scene scene = new Scene(root, 400, 500);
        scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    private void ventanaRevista() {
        Stage stage = new Stage();
        stage.setTitle("Registrar Revista");

        Label titulo = new Label("Registro de Revista");
        titulo.getStyleClass().add("titulo");
        
        TextField codigo = new TextField();
        codigo.setPromptText("Código");

        TextField tituloRevista = new TextField();
        tituloRevista.setPromptText("Título");
        
        TextField autor = new TextField();
        autor.setPromptText("Autor");

        TextField anio = new TextField();
        anio.setPromptText("Año");

        Button guardar = new Button("Guardar");
        Button salir = new Button("Salir");

        guardar.setOnAction(e -> {
            try {
                int anioInt = Integer.parseInt(anio.getText());
                gestor.registrarRevista("Revista", codigo.getText(), autor.getText(), tituloRevista.getText(), anioInt, "Disponible");
                mostrarAlerta("Éxito", "Revista guardada correctamente.");
                stage.close();
            } catch (NumberFormatException ex) {
                mostrarAlerta("Error", "El año debe ser un número válido.");
            }
        });

        salir.setOnAction(e -> stage.close());

        VBox root = new VBox(15, titulo, codigo, tituloRevista, autor, anio, guardar, salir);
        root.getStyleClass().add("card");

        Scene scene = new Scene(root, 400, 450);
        scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    private void ventanaPrestamo() {
        Stage stage = new Stage();
        stage.setTitle("Registrar Préstamo");

        Label titulo = new Label("Registro de Préstamo");
        titulo.getStyleClass().add("titulo");

        TextField idPersona = new TextField();
        idPersona.setPromptText("ID Persona (Número)");

        TextField codigoMaterial = new TextField();
        codigoMaterial.setPromptText("Código Material");

        Button guardar = new Button("Registrar");
        Button salir = new Button("Salir");

        guardar.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idPersona.getText());
                String msj = gestor.registrarPrestamo(id, codigoMaterial.getText());
                if(msj.startsWith("Error")) {
                    mostrarAlerta("Error", msj);
                } else {
                    mostrarAlerta("Éxito", msj);
                    stage.close();
                }
            } catch (NumberFormatException ex) {
                mostrarAlerta("Error", "El ID de persona debe ser numérico.");
            }
        });

        salir.setOnAction(e -> stage.close());

        VBox root = new VBox(15, titulo, idPersona, codigoMaterial, guardar, salir);
        root.getStyleClass().add("card");

        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    private void ventanaVerPersonas() {
        Stage stage = new Stage();
        stage.setTitle("Lista de Personas");

        ListView<String> lista = new ListView<>();
        Map<Integer, Persona> personas = gestor.obtenerTodasLasPersonas();
        
        for (Map.Entry<Integer, Persona> entry : personas.entrySet()) {
            lista.getItems().add("ID: " + entry.getKey() + " - " + entry.getValue().toString());
        }

        VBox root = new VBox(15, new Label("Personas Registradas"), lista);
        root.getStyleClass().add("card");

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void ventanaVerMateriales() {
        Stage stage = new Stage();
        stage.setTitle("Lista de Materiales");

        ListView<String> lista = new ListView<>();
        Map<String, Material> materiales = gestor.obtenerTodosLosMateriales();
        
        for (Map.Entry<String, Material> entry : materiales.entrySet()) {
            lista.getItems().add("Cód: " + entry.getKey() + " - " + entry.getValue().toString());
        }

        VBox root = new VBox(15, new Label("Materiales Registrados"), lista);
        root.getStyleClass().add("card");

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void ventanaVerPrestamos() {
        Stage stage = new Stage();
        stage.setTitle("Lista de Préstamos");

        ListView<String> lista = new ListView<>();
        List<Prestamo> prestamos = gestor.obtenerTodosLosPrestamos();
        
        for (Prestamo p : prestamos) {
            lista.getItems().add(p.toString());
        }

        VBox root = new VBox(15, new Label("Préstamos Activos"), lista);
        root.getStyleClass().add("card");

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}