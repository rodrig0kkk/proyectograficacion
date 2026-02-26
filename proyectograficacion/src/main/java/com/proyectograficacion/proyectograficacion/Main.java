package com.proyectograficacion.proyectograficacion;

import javafx.application.Application;
import javafx.stage.Stage;
import com.proyectograficacion.proyectograficacion.controller.MenuPrincipal;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.start(stage);  
         menu.menuConsola();  
    }

    public static void main(String[] args) {
        launch(args); 
    }
}
