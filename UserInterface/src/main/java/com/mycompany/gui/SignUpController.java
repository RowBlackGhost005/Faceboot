package com.mycompany.gui;

import entities.User;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignUpController {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private ComboBox<String> cmbGender;
    @FXML
    private Button btnBack;

    @FXML
    private DatePicker datePicker;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnSignUp;

    @FXML
    private void clickCmbGender(MouseEvent event) {
        cmbGender.setItems(FXCollections.observableArrayList(
                "Male", "Female", "Other"));
    }

    @FXML
    private void clickBtnBack(MouseEvent event) throws IOException {
        GUIController.show("Login");
    }

    @FXML
    private void clickBtnCancel(MouseEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtPhoneNumber.setText("");
        txtName.setText("");
        txtEmail.setText("");
        cmbGender.setValue("Gender");
        datePicker.setValue(null);
        txtPassword.setText("");
    }

    @FXML
    private void clickBtnSignUp(MouseEvent event) throws IOException {
        SignUp();
    }

    private void SignUp() throws IOException {
        User user = new User();
        user.setName(txtName.getText());
        user.setEmail(txtEmail.getText());
        user.setPhone(txtPhoneNumber.getText());
        user.setGender(cmbGender.getSelectionModel().getSelectedItem());
        user.setBirthDate(String.valueOf(datePicker.getValue()));
        user.setPassword(txtPassword.getText());

        try {
            User registeredUser = GUIController.registerUser(user);
            if (registeredUser != null) {
                GUIController.showDialog("Success!", "Your account has been successfuly created", 0);
            }
        } catch (Exception ex) {
            GUIController.showDialog("Error", ex.getMessage(), 0);
        }

    }

}
