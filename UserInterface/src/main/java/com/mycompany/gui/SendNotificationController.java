package com.mycompany.gui;

import com.masa.domain.Notification;
import com.masa.domain.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import logic.GUILogic;

/**
 * FXML Controller class
 *
 * @author jjavi
 */
public class SendNotificationController {

    @FXML
    private Button btnSend;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtMessage;
    @FXML
    private TextField txtTo;
    
    @FXML
    private void clickBtnBack(MouseEvent event) throws IOException {
        GUIController.show("Faceboot");
    }
    
    @FXML
    private void clickBtnSend(MouseEvent event) throws IOException {
        User receptor = new User();       
        receptor.setPhone(txtTo.getText());
        
        Notification notification = new Notification();
        notification.setReceptor(receptor);
        notification.setMessage(txtMessage.getText());
        
        GUILogic.getLogic().sendNotification(notification, "sms");
        
        txtTo.setText("");
        txtMessage.setText("");
    }
}
