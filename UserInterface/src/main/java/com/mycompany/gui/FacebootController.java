package com.mycompany.gui;

import com.masa.domain.Post;
import com.masa.domain.Tag;
import com.masa.domain.User;
import com.masa.utils.IObserver;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 *
 * @author Andrea
 */
public class FacebootController implements Initializable {

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

    private GUIUpdates updatesNotifier;
    @FXML
    private Label lblUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblUser.setText(GUIController.getLoggedUser().getName());
        addOnlineUser("Andrea");
        addOnlineUser("Luis");
        addOfflineUser("Diego");

        try {
            updatePosts();
        } catch (IOException ex) {
            Logger.getLogger(FacebootController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        updatesNotifier = new GUIUpdates(this);
        GUIController.subscribeGUIUpdate(updatesNotifier);

    }

    public void updatePosts() throws IOException{
        List<Post> posts = GUIController.getAllPosts();
        GUIBuilder builder = new GUIBuilder();
        for(Post post:posts){
            addPost(builder.buildPost(post));
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

    public void addOnlineUser(String user) {
        listOnlineUsers.getItems().add(user);
    }

    public void addOfflineUser(String user) {
        listOfflineUsers.getItems().add(user);
    }
}
