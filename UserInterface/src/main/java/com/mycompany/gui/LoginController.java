package com.mycompany.gui;

import com.masa.domain.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import logic.GUILogic;

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
        loginLocal();
    }

    @FXML
    private void clickBtnRegister(MouseEvent event) throws IOException {
        GUIController.show("SignUp");
    }

    private void loginLocal() throws IOException {
        User user = new User(txtLogin.getText(), txtPassword.getText());

        try {
            User existingUser = GUILogic.getLogic().login(user, "local");
            if (existingUser != null) {
//                GUILogic.getLogic().setUserLogged(existingUser);
                GUIController.showFaceboot();
//                GUILogic.getLogic().getAllOnlineUsers();

                //LogMessage log = new LogMessage();
            }else{
                throw new Exception("");
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
            GUIController.showDialog("Invalid Credentials", "Try agin", 1);
        }
    }

    private void loginGoogle() {
        User existingUser = null;
        
        try {
            existingUser = GUILogic.getLogic().login(new User(), "google");
            
            if(existingUser.getId() == null){
                GUIController.show("RegisterPhone");
                GUILogic.getLogic().setUserLogged(existingUser);
            }

            else if (existingUser.getEmail() != null) {
//                GUILogic.getLogic().setUserLogged(existingUser);
                GUIController.showFaceboot();
//                GUILogic.getLogic().getAllOnlineUsers();
                //LogMessage log = new LogMessage();
            }

        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickBtnLoginWithGoogle(MouseEvent event) {
        loginGoogle();
    }
}
