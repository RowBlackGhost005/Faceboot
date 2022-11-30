/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.gui.GUIController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Andrea
 */
public class SplashScreenController implements Initializable {

    @FXML
    private ImageView imgLogo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
          
        FadeTransition transition = new FadeTransition(Duration.millis(3000));
        transition.setFromValue(1.0);
        transition.setToValue(1.0);
        transition.play();

        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage ventana = (Stage) imgLogo.getScene().getWindow();
                ventana.hide();
//                try {
//                    GUIController.show("Faceboot");
//                } catch (IOException ex) {
//                    Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        });
    }    
    
}
