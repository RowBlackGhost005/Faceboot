package com.mycompany.gui;

import com.masa.domain.Notification;
import com.masa.domain.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import logic.GUILogic;

/**
 * FXML Controller class
 *
 * @author Andrea
 */
public class NotificationHistoryController implements Initializable {

    @FXML
    private GridPane notificationPanel;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        GUIBuilder builder = new GUIBuilder();
        
        User currentUser = GUILogic.getLogic().getUserLogged();
        List<Notification> notifications = GUILogic.getLogic().getNotificationsByUser(currentUser.getId());
        
        for(Notification notification : notifications){
            try {
                addNotification(builder.buildNotificationHistory(notification));
            } catch (IOException ex) {
                Logger.getLogger(NotificationHistoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    

    @FXML
    private void clickBtnBack(MouseEvent event) {
        try {
            GUIController.showFaceboot();
        } catch (IOException ex) {
            Logger.getLogger(NotificationHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addNotification(AnchorPane notification){
        notificationPanel.add(notification, 0, notificationPanel.getRowCount()+1);
    }
    
}
