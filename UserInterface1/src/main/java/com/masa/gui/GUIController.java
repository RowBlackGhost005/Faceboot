package com.masa.gui;

import com.masa.businesslogic.IBusinessLogic;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX GUIController
 */
public class GUIController extends Application {
    private static GUIBuilder guiBuilder;
    private static Scene scene;

    //App dependencies
    IBusinessLogic logic;
    
//    public GUIController(){
//        
//        logic = new BusinessLogic();
//    }
    
    
    @Override
    public void start(Stage stage) throws IOException {
        guiBuilder=new GUIBuilder();
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
    
    static void showDialog(String titulo, String mensaje, int tipo) throws IOException{
        Stage dialogo = guiBuilder.buildDialog(titulo, mensaje, tipo);
        dialogo.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    //Logic methods
    
    public static void main(String[] args) {
        launch();
        
//        GUIControllerLogic logic = new GUIControllerLogic();
//        
//        User user = new User();
//        
//        logic.registerUser(user);
    }
}