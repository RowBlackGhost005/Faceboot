/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Andrea
 */
public class GUIBuilder {


    public GUIBuilder() {
    }
    
    public Stage buildDialog(String title, String message,int type) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("Dialog.fxml"));
        Parent root = (Parent)fxmlLoader.load();  
        DialogController controller = fxmlLoader.<DialogController>getController();
        controller.setTitle(title);
        controller.setMessage(message);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
            stage.show();
//        switch(type){
//            case 0:{
//                Image image= new Image(GUIBuilder.class.getResource("arrow.png").getPath());
//                controller.setImage(image);
//            }
//        }
        return stage;
    }
    
    
}
