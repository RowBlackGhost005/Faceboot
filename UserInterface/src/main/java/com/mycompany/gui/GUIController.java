package com.mycompany.gui;

import business.BusinessLogic;
import interfaces.IBusinessLogic;
import entities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.layout.StackPane;
import logic.GUILogic;

/**
 * JavaFX GUIController
 */
public class GUIController extends Application {

    private static IBusinessLogic businessLogic;
    private static GUIBuilder guiBuilder;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        
        businessLogic = new BusinessLogic();
        guiBuilder = new GUIBuilder();
        scene = new Scene(loadFXML("Login"), 960, 540);
        stage.setMinWidth(700);
        stage.setMinHeight(400);
        stage.setScene(scene);
        stage.setTitle("Faceboot");
        stage.show();
        
    }

    static void show(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void showDialog(String titulo, String mensaje, int tipo) throws IOException {
        Stage dialogo = guiBuilder.buildDialog(titulo, mensaje, tipo);
        dialogo.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        GUILogic.createBusinessLogic();
        launch();
<<<<<<< HEAD
=======

    }
    
    public static User registerUser(User user) throws Exception {
        return businessLogic.registerUser(user);
    }
    
    public static User logIn(User user) throws Exception {
            return businessLogic.login(user);
>>>>>>> origin/develop
    }

}
