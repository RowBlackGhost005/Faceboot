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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author Andrea
 */
public class FacebootController implements Initializable {


    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnPublish;
    @FXML
    private Button btnSearch;
    @FXML
    private Pane paneUsers;
    @FXML
    private ListView<String> listOnlineUsers;
    @FXML
    private ListView<String> listOfflineUsers;
    @FXML
    private Pane paneUser;
    @FXML
    private Button btnNotifications;
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
 
        addOnlineUser("Andrea");
        addOnlineUser("Luis");
        addOfflineUser("Diego");
        
    }
    
    
    @FXML
    private void clickBtnPublish(MouseEvent event) throws IOException {
        GUIController.show("CreatePost");
    }
    

    public void addOnlineUser(String user){
        listOnlineUsers.getItems().add(user);
    }
    public void addOfflineUser(String user){
        listOfflineUsers.getItems().add(user);
    }

}
