package com.mycompany.gui;

<<<<<<< HEAD

import java.awt.Desktop;
=======
import entities.User;
>>>>>>> origin/develop
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
    private TextField txtLogin;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnLogginWithGoogle;
    @FXML
    private Button btnLogginWithTwitter;
    @FXML
    private Pane mainBar;

    @FXML
    private void clickBtnLogin(ActionEvent event) throws IOException {
        Login();
    }

    @FXML
    private void clickBtnRegister(MouseEvent event) throws IOException {
        GUIController.show("SignUp");
    }
<<<<<<< HEAD
    
      @FXML
    private void clickBtnLoginWithGoogle(MouseEvent event) throws IOException, URISyntaxException {



=======

    private void Login() throws IOException {
        User user = new User(txtLogin.getText(), txtPassword.getText());

        try {
            User existingUser = GUIController.logIn(user);
            if (existingUser != null) {
                GUIController.showDialog("Success", "Valid login!", 0);
                GUIController.show("Faceboot");
                //LogMessage log = new LogMessage();
            }
        } catch (Exception ex) {
            GUIController.showDialog("Error", ex.getMessage(), 0);
        }
>>>>>>> origin/develop
    }
}
