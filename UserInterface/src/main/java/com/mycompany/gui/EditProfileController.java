package com.mycompany.gui;

import com.masa.domain.User;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EditProfileController {

    @FXML
    private Label lblPassword;
    @FXML
    private Label lblNewPassword;
    @FXML
    private TextField txtName;
    @FXML
    private PasswordField txtNewPassword;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ComboBox<String> cmbGender;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnEditPassword;

    private User user;

    @FXML
    private void clickBtnSave(MouseEvent event) throws IOException, Exception {
        editProfile();
    }

    @FXML
    private void clickBtnBack(MouseEvent event) throws IOException {
        GUIController.showEditProfile(user);
    }

    @FXML
    private void clickBtnEditPassword(MouseEvent event) {
        editPassword();
    }

    @FXML
    private void clickCmbGender(MouseEvent event) {
        cmbGender.setItems(FXCollections.observableArrayList(
                "Male", "Female", "Other"));
    }

    private void editProfile() throws IOException, Exception {
        user.setName(txtName.getText());
        user.setGender(cmbGender.getValue());
        user.setBirthDate(datePicker.getValue().toString());
        if (!btnEditPassword.isVisible()) {
            if (txtPassword.getText().isBlank() || !txtNewPassword.getText().isBlank()) {
                throw new Exception("Password fields can't be empty");
            } else if (user.getPassword().equals(txtPassword.getText())
                    && !user.getPassword().equals(txtNewPassword.getText())) {
                user.setPassword(txtNewPassword.getText());
            } else {
                throw new Exception("Actual password must match with account's password");
            }
        }
        try {
            GUIController.editUser(user);
            GUIController.showDialog("Success!", "Changes have been saved!", 0);
            //GUIController.showFaceboot(user);
        } catch (Exception ex) {
            GUIController.showDialog("Error!", ex.getMessage(), 1);
        }

    }

    public void setUser(User user) {
        this.user = user;
    }

    public void fillFields() {
        txtName.setText(user.getName());
        cmbGender.setValue(user.getGender());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US);
        LocalDate date = LocalDate.parse(user.getBirthDate(), formatter);
        datePicker.setValue(date);
        txtPassword.setText("********");
    }

    private void editPassword() {
        lblPassword.setText("ACTUAL PASSWORD");
        lblNewPassword.setVisible(true);
        txtNewPassword.setVisible(true);
        btnEditPassword.setVisible(false);
        txtPassword.setText("");
    }

}
