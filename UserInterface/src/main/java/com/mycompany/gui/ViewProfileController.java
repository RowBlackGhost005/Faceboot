package com.mycompany.gui;

import com.masa.domain.User;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ViewProfileController {

    @FXML
    private Label lblUser;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPassword;
    @FXML
    private Label lblGender;
    @FXML
    private Label lblBirthDate;
    @FXML
    private Label lblPhone;

    private User user;
    
    @FXML
    private void clickBtnEdit(MouseEvent event) throws IOException {
        GUIController.showEditProfile();
    }
    @FXML
    private void clickBtnBack(MouseEvent event) throws IOException {
        GUIController.showFaceboot();
    }
    
    public void fillProfile() {
        lblUser.setText(user.getName());
        lblEmail.setText(user.getEmail());
        lblPassword.setText("********");
        lblGender.setText(user.getGender());
        lblBirthDate.setText(user.getBirthDate());
        lblPhone.setText(user.getPhone());
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
}
