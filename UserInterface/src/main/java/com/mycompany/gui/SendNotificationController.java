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
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import logic.GUILogic;


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
    private ComboBox<String> cmbNotificationMode;
    
    private User userReceptor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbNotificationMode.getItems().removeAll(cmbNotificationMode.getItems());
        cmbNotificationMode.getItems().addAll("SMS", "E-MAIL", "BOTH");
        cmbNotificationMode.getSelectionModel().select("SMS");

    }

    @FXML
    private void clickBtnBack(MouseEvent event) throws IOException {
        GUIController.show("Faceboot");
    }

    @FXML
    private void clickBtnSend(MouseEvent event) throws IOException {


        User from = new User();
        from.setEmail("jose.angulo215058@potros.itson.edu.mx");

        Notification notification = new Notification();
        notification.setTo(userReceptor);
        notification.setFrom(from);
        notification.setMessage(txtMessage.getText());

        GUILogic.getLogic().sendNotification(notification, "sms");
        switch(cmbNotificationMode.getSelectionModel().getSelectedItem()){
            case "Phone": GUILogic.getLogic().sendNotification(notification, "sms");
        }
        

        txtTo.setText("");

        String option = cmbNotificationMode.getSelectionModel().getSelectedItem();

        sendNotification(notification, option);
        clear();
    }

    private void sendNotification(Notification notification, String option) throws IOException {
         GUILogic.getLogic().sendNotification(notification, option);
        GUIController.showDialog("Notification sent!", "Your notification was sent succesfully!", 0);
    }

    private void clear() {
        txtMessage.setText("");
    }
    
    public void setUser(User user){
        this.userReceptor=user;
        txtTo.setText(userReceptor.getName());
    }
}
