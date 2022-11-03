/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.masa.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Andrea
 */
public class DialogController implements Initializable {

    @FXML
    private Button btnOk;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblMessage;
    @FXML
    private ImageView imgView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    

    @FXML
    private void clickBtnOk(MouseEvent event) {
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }
    
    public void setTitle(String title){
        lblTitle.setText(title);
    }
    
    public void setMessage(String message){
        lblMessage.setText(message);
    }
    
     public void setImage(Image image){
       imgView.setImage(image);
    }
}
