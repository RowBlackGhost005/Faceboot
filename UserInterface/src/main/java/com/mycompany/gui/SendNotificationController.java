package com.mycompany.gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import com.masa.domain.Notification;
import com.masa.domain.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author jjavi
 */
public class SendNotificationController implements Initializable {

    @FXML
    private Button btnSend;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtMessage;
    @FXML
    private TextField txtTo;
    @FXML
    private ComboBox<String> cmbProvider;
    @FXML
    private ComboBox<String> cmbTo;

    private List<User> users;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbProvider.getItems().removeAll(cmbProvider.getItems());
        cmbProvider.getItems().addAll("SMS", "E-MAIL", "BOTH");
        cmbProvider.getSelectionModel().select("SMS");

        users = GUIController.getAllUsers();
        List<String> userNames = new ArrayList();
        users.forEach(user -> {
            userNames.add(user.getName());
        });
        cmbTo.getItems().removeAll(cmbTo.getItems());
        cmbTo.getItems().addAll(userNames);
        cmbTo.getSelectionModel().select(userNames.get(0));
    }

    @FXML
    private void clickBtnBack(MouseEvent event) throws IOException {
        GUIController.show("Faceboot");
    }

    @FXML
    private void clickBtnSend(MouseEvent event) throws IOException {

        String selectedUser = cmbTo.getSelectionModel().getSelectedItem();
        User to = new User();
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(selectedUser)) {
                to = user;
            }
        }
        
        User from = new User();
        from.setEmail("jose.angulo215058@potros.itson.edu.mx");

        Notification notification = new Notification();
        notification.setTo(to);
        notification.setFrom(from);
        notification.setMessage(txtMessage.getText());

        String option = cmbProvider.getSelectionModel().getSelectedItem();

        sendNotification(notification, option);
        clear();
    }

    private void sendNotification(Notification notification, String option) throws IOException {
        GUIController.sendNotification(notification, option);
        GUIController.showDialog("Notification sent!", "Your notification was sent succesfully!", 0);
    }

    private void clear() {
        txtMessage.setText("");
    }
}
