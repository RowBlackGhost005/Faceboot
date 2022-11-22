package com.mycompany.gui;

import com.masa.domain.Post;
import com.masa.domain.Tag;
import com.masa.domain.User;
import com.masa.utils.IObserver;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   
        addOnlineUser("Andrea");
        addOnlineUser("Luis");
        addOfflineUser("Diego");

        GUIBuilder builder = new GUIBuilder();


        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag(null,"HopeThisWorks"));
        tags.add(new Tag(null,"Why?"));
        tags.add(new Tag(null,"Why?"));

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("jose"));
        users.add(new User("andrea"));

        Post post = new Post("This is a test", null, users, tags, null, new User("andrea"), LocalDateTime.now());
        Post post2 = new Post("This is a test", null, users, tags, null, new User("jose"), LocalDateTime.now());
        Post pos3 = new Post("This is a test", null, users, tags, null, new User("jose"), LocalDateTime.now());

        try {
            addPost(builder.builPost(post));
            addPost(builder.builPost(post2));
             addPost(builder.builPost(pos3));

        } catch (IOException ex) {
            Logger.getLogger(FacebootController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        updatesNotifier = new GUIUpdates(this);
        GUIController.subscribeGUIUpdate(updatesNotifier);

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
