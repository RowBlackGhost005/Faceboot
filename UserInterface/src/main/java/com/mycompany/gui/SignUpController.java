package com.mycompany.gui;

import com.masa.domain.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String phone = txtPhoneNumber.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String gender = cmbGender.getValue();
        String date = datePicker.getValue().toString();
        String pssword = txtPassword.getText();

        User user = new User(name, email, phone, gender, date, pssword);

//        GUILogic.registerUser(user);
        
        try {
            GUIController.registerUser(user);
        } catch (Exception ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }

        GUIController.showDialog("Success!", "Your account has been successfuly created", 0);

        GUIController.show("Faceboot");
    }

}
