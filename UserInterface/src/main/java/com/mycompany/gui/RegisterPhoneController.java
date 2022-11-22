/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gui;

import com.masa.authentication.Profile;
import com.masa.domain.User;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Andrea
 */
public class RegisterPhoneController implements Initializable {

    private User user;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSignUp;
    @FXML
    private TextField txtPhone;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btncClickCancel(MouseEvent event) {
    }

    @FXML
    private void btnClickSignUp(MouseEvent event) {
        user.setPhone(this.txtPhone.getText());
        try {
            GUIController.registerExternalUser(user);
            GUIController.show("Faceboot");
            
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
            
        } catch (Exception ex) {
            Logger.getLogger(RegisterPhoneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setUser(User user){
        this.user=user;
    }
    
}
