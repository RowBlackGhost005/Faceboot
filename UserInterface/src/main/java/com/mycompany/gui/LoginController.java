package com.mycompany.gui;

import com.masa.domain.User;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        login();
    }

    @FXML
    private void clickBtnRegister(MouseEvent event) throws IOException {
        GUIController.show("SignUp");
    }

    private void login() throws IOException {
        User user = new User(txtLogin.getText(), txtPassword.getText());

        try {
            User existingUser = GUIController.logIn(user);
            if (existingUser != null) {
                GUIController.show("Faceboot");
                //LogMessage log = new LogMessage();
            }
        } catch (Exception ex) {
            GUIController.showDialog("Error", ex.getMessage(), 0);
        }
    }

    @FXML
    private void clickBtnLoginWithGoogle(MouseEvent event) {
         try {
            GUIController.loginWith("GOOGLE");
           
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

