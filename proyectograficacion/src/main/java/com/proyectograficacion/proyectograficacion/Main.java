package com.proyectograficacion.proyectograficacion;

import com.proyectograficacion.proyectograficacion.controller.GestorBiblioteca;
import com.proyectograficacion.proyectograficacion.view.ConsolaView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private GestorBiblioteca gestorBiblioteca;

    @Override
    public void start(Stage stage) {
        gestorBiblioteca = new GestorBiblioteca();

        new Thread(() -> {
            ConsolaView consola = new ConsolaView(gestorBiblioteca);
            consola.iniciar();
        }).start();

        Button btnPersona = new Button("Agregar Alumno de prueba");
        btnPersona.setOnAction(e -> {
            int id = gestorBiblioteca.registrarAlumno("Alumno", "UI Nombre", "UI Apellido", "ui@correo.com", 123, 0, 0, 999);
            System.out.println("[GUI] Alumno agregado mediante JavaFX con ID: " + id);
        });

        VBox root = new VBox(10, btnPersona);
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Sistema Biblioteca MVC");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args); 
    }
}