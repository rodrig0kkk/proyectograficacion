package com.proyectograficacion.proyectograficacion;

import com.proyectograficacion.proyectograficacion.view.MenuBibliotecaView;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MenuBibliotecaView menuView = new MenuBibliotecaView(primaryStage);
        menuView.mostrar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}