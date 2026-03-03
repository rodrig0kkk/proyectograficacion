package com.proyectograficacion.proyectograficacion;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

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

        btnVerMaterial.setOnAction(e -> System.out.println("Mostrar materiales"));
        btnVerPersona.setOnAction(e -> System.out.println("Mostrar personas"));
        btnVerPrestamo.setOnAction(e -> System.out.println("Mostrar préstamos"));

        VBox root = new VBox(15, titulo, btnPersona, btnMaterial,
        btnPrestamo, btnVerMaterial, btnVerPersona, btnVerPrestamo);

root.getStyleClass().add("card");

        Scene scene = new Scene(root, 400, 450);
        scene.getStylesheets().add(
    getClass().getResource(
        "/com/proyectograficacion/proyectograficacion/view/style.css"
    ).toExternalForm()
);

        stage.setTitle("Sistema Biblioteca MVC");
        stage.setScene(scene);
        stage.show();
    }


private void ventanaPersona() {

    Stage stage = new Stage();
    stage.setTitle("Agregar Persona");

    Label titulo = new Label("Seleccionar Tipo de Persona");
    titulo.getStyleClass().add("titulo");

    Button btnAlumno = new Button("Alumno");
    Button btnProfesor = new Button("Profesor");
    Button btnSalir = new Button("Salir");

    btnAlumno.setOnAction(e -> ventanaAlumno());
    btnProfesor.setOnAction(e -> ventanaProfesor());
    btnSalir.setOnAction(e -> stage.close());

    VBox root = new VBox(15, titulo, btnAlumno, btnProfesor, btnSalir);
    root.getStyleClass().add("card");

    Scene scene = new Scene(root, 400, 300);
    scene.getStylesheets().add(getClass().getResource("/com/proyectograficacion/proyectograficacion/view/style.css").toExternalForm());

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

    TextField matricula = new TextField();
    matricula.setPromptText("Matrícula");

    Button guardar = new Button("Guardar");
    Button salir = new Button("Salir");

    guardar.setOnAction(e -> {
        System.out.println("Alumno guardado: " + nombre.getText());
    });

    salir.setOnAction(e -> stage.close());

    VBox root = new VBox(15, titulo, nombre, apellido, matricula, guardar, salir);
    root.getStyleClass().add("card");

    Scene scene = new Scene(root, 400, 450);
    scene.getStylesheets().add(getClass().getResource("/com/proyectograficacion/proyectograficacion/view/style.css").toExternalForm());

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

    TextField numEmpleado = new TextField();
    numEmpleado.setPromptText("Número Empleado");

    Button guardar = new Button("Guardar");
    Button salir = new Button("Salir");

    guardar.setOnAction(e -> {
        System.out.println("Profesor guardado: " + nombre.getText());
    });

    salir.setOnAction(e -> stage.close());

    VBox root = new VBox(15, titulo, nombre, apellido, numEmpleado, guardar, salir);
    root.getStyleClass().add("card");

    Scene scene = new Scene(root, 400, 450);
    scene.getStylesheets().add(getClass().getResource("/com/proyectograficacion/proyectograficacion/view/style.css").toExternalForm());

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

    btnLibro.setOnAction(e -> ventanaLibro());
    btnRevista.setOnAction(e -> ventanaRevista());
    btnSalir.setOnAction(e -> stage.close());

    VBox root = new VBox(15, titulo, btnLibro, btnRevista, btnSalir);
    root.getStyleClass().add("card");

    Scene scene = new Scene(root, 400, 300);
    scene.getStylesheets().add(getClass().getResource("/com/proyectograficacion/proyectograficacion/view/style.css").toExternalForm());

    stage.setScene(scene);
    stage.show();
}



private void ventanaLibro() {

    Stage stage = new Stage();
    stage.setTitle("Registrar Libro");

    Label titulo = new Label("Registro de Libro");
    titulo.getStyleClass().add("titulo");

    TextField tituloLibro = new TextField();
    tituloLibro.setPromptText("Título");

    TextField autor = new TextField();
    autor.setPromptText("Autor");

    TextField editorial = new TextField();
    editorial.setPromptText("Editorial");

    Button guardar = new Button("Guardar");
    Button salir = new Button("Salir");

    guardar.setOnAction(e -> {
        System.out.println("Libro guardado: " + tituloLibro.getText());
    });

    salir.setOnAction(e -> stage.close());

    VBox root = new VBox(15, titulo, tituloLibro, autor, editorial, guardar, salir);
    root.getStyleClass().add("card");

    Scene scene = new Scene(root, 400, 450);
    scene.getStylesheets().add(getClass().getResource("/com/proyectograficacion/proyectograficacion/view/style.css").toExternalForm());

    stage.setScene(scene);
    stage.show();
}



private void ventanaRevista() {

    Stage stage = new Stage();
    stage.setTitle("Registrar Revista");

    Label titulo = new Label("Registro de Revista");
    titulo.getStyleClass().add("titulo");

    TextField tituloRevista = new TextField();
    tituloRevista.setPromptText("Título");

    TextField anio = new TextField();
    anio.setPromptText("Año");

    Button guardar = new Button("Guardar");
    Button salir = new Button("Salir");

    guardar.setOnAction(e -> {
        System.out.println("Revista guardada: " + tituloRevista.getText());
    });

    salir.setOnAction(e -> stage.close());

    VBox root = new VBox(15, titulo, tituloRevista, anio, guardar, salir);
    root.getStyleClass().add("card");

    Scene scene = new Scene(root, 400, 400);
    scene.getStylesheets().add(getClass().getResource("/com/proyectograficacion/proyectograficacion/view/style.css").toExternalForm());

    stage.setScene(scene);
    stage.show();
}


private void ventanaPrestamo() {

    Stage stage = new Stage();
    stage.setTitle("Registrar Préstamo");

    Label titulo = new Label("Registro de Préstamo");
    titulo.getStyleClass().add("titulo");

    TextField idPersona = new TextField();
    idPersona.setPromptText("ID Persona");

    TextField codigoMaterial = new TextField();
    codigoMaterial.setPromptText("Código Material");

    Button guardar = new Button("Registrar");
    Button salir = new Button("Salir");

    guardar.setOnAction(e -> {
        System.out.println("Préstamo registrado");
    });

    salir.setOnAction(e -> stage.close());

    VBox root = new VBox(15, titulo, idPersona, codigoMaterial, guardar, salir);
    root.getStyleClass().add("card");

    Scene scene = new Scene(root, 400, 400);
    scene.getStylesheets().add(getClass().getResource("/com/proyectograficacion/proyectograficacion/view/style.css").toExternalForm());

    stage.setScene(scene);
    stage.show();
}



    public static void main(String[] args) {
        launch(args);
    }
}
