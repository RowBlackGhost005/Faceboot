/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Andrea
 */
public class CreateCommentController implements Initializable {

    @FXML
    private TextField txtMessage;
    @FXML
    private TextField txtTags;
    @FXML
    private Button btnComment;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBack;

    private boolean editing;
    @FXML
    private Label lblTagUsers;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     private void getTaggedUsers() throws IOException {
       // GUIController.showTagUsersDialog(this);
    }

    @FXML
    private void clickBtnComment(MouseEvent event) {
    }

    @FXML
    private void clickBtnBack(MouseEvent event) {
    }

    @FXML
    private void clickBtnTag(MouseEvent event) {
        // getTaggedUsers();
    }
    
}
