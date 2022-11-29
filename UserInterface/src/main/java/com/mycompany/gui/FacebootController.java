package com.mycompany.gui;

import com.masa.domain.Log;
import com.masa.domain.Post;
import com.masa.domain.User;
import com.masa.utils.IObserver;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import logic.GUILogic;

/**
 * FXML Controller class
 *
 * @author Andrea
 */
public class FacebootController implements Initializable, IObserver {

    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnPublish;
    @FXML
    private Button btnSearch;
    @FXML
    private Pane paneUsers;
    @FXML
    private ListView<String> listOnlineUsers;
    @FXML
    private ListView<String> listOfflineUsers;
    @FXML
    private Pane paneUser;
    @FXML
    private Button btnNotifications;
    @FXML
    private GridPane postPane;

//    private GUIUpdates updatesNotifier;
    @FXML
    private Label lblUser;

    private User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblUser.setText(GUILogic.getLogic().getUserLogged().getName());
        addOnlineUser("Andrea");
        addOnlineUser("Luis");
        addOfflineUser("Diego");

//        updatesNotifier = new GUIUpdates(this);
        GUILogic.getLogic().subscribePostNotifications(this);

        updatePosts();
    }

    public void updatePosts(){
        List<Post> posts = GUILogic.getLogic().getAllPost();
        GUIBuilder builder = new GUIBuilder();
        for(Post post:posts){
            try {
                addPost(builder.buildPost(post));
            } catch (IOException ex) {
                Logger.getLogger(FacebootController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public void addPost(Parent post) {
        for (Node child : postPane.getChildren()) {//moves the posts one row down
            Integer rowIndex = GridPane.getRowIndex(child);
            if (rowIndex != null) {
                GridPane.setRowIndex(child, rowIndex + 1);
            }
        }

        postPane.add(post, 0, 0);
    }

    @FXML

    private void clickBtnPublish(MouseEvent event) {
        try {
            GUIController.show("CreatePost");
        } catch (IOException ex) {
            Logger.getLogger(FacebootController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @FXML
    private void clickBtnViewProfile(MouseEvent event) throws IOException {
        GUIController.showProfile();
    }

    public void addOnlineUser(String user) {
        listOnlineUsers.getItems().add(user);
    }

    public void addOfflineUser(String user) {
        listOfflineUsers.getItems().add(user);
    }
    
    @Override
    public void update(Object post) {
        GUIBuilder builder = new GUIBuilder();
        
        // Avoid throwing IllegalStateException by running from a non-JavaFX thread.
        Platform.runLater(
                () -> {
                    try {
                        this.addPost(builder.buildPost((Post) post));
                    } catch (IOException ex) {
                        Logger.getLogger(GUIUpdates.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );
    }
    
    @FXML
    private void clickBtnSendNotification(MouseEvent event) {
        try {
            GUIController.show("SendNotification");
            List<Log> logs = GUILogic.getLogic().getAllLogs();
            for(Log log:logs){
                System.out.println(log.getDate()+" "+log.getLevel()+" "+log.getMessage()+" ");
            }
        } catch (IOException ex) {
             Logger.getLogger(GUIUpdates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
