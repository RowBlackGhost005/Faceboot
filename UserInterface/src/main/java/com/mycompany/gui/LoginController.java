package com.mycompany.gui;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class LoginController {

    @FXML
    private Button btnLoggin;
    @FXML
    private TextField txtusuario;
    @FXML
    private TextField txtcontraseña;
    @FXML
    private Button btnLogginWithGoogle;
    @FXML
    private Button btnLogginWithTwitter;
    @FXML
    private Pane mainBar;

    @FXML
    private void clickBtnLogin(ActionEvent event) throws IOException {
        
        GUIController.show("Faceboot");
//        LogMessage log = new LogMessage();
    }

    @FXML
    private void clickBtnRegister(MouseEvent event) throws IOException {
        GUIController.show("SignUp");
    }
    
      @FXML
    private void clickBtnLoginWithGoogle(MouseEvent event) throws IOException, URISyntaxException {



    }
}
