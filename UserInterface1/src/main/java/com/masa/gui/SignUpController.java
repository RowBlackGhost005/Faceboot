package com.masa.gui;

import java.io.IOException;
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
        "Man","Woman","Other"));
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
        
//        IBusinessLogic logic = new BusinessLogic();
//        
        User user = new User(name, email, phone, gender, date, 0);
        
        GUIControllerLogic logic = new GUIControllerLogic();
        logic.registerUser(user);
        
        GUIController.showDialog("Success!", "Your account has been successfuly created", 0);

    }
    
    
}