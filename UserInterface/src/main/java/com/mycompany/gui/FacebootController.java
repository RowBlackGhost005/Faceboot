package com.mycompany.gui;

import com.masa.domain.Post;
import com.masa.utils.IObserver;
import java.io.IOException;
import java.net.URL;
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

<<<<<<< HEAD
//    private GUIUpdates updatesNotifier;
    @FXML
    private Label lblUser;
=======
    private User user;
>>>>>>> origin/feature

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblUser.setText(GUILogic.getLogic().getUserLogged().getName());
        addOnlineUser("Andrea");
        addOnlineUser("Luis");
        addOfflineUser("Diego");

<<<<<<< HEAD
=======
        GUIBuilder builder = new GUIBuilder();

        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag("hopeThisWork"));
        tags.add(new Tag("Why?"));

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("jose"));
        users.add(new User("andrea"));

        Post post = new Post("This is a test", null, users, tags, null, new User("andrea"));
        Post post2 = new Post("This is a test", null, users, tags, null, new User("jose"));

>>>>>>> origin/feature
        try {
            updatePosts();
        } catch (IOException ex) {
            Logger.getLogger(FacebootController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
//        updatesNotifier = new GUIUpdates(this);
        GUILogic.getLogic().subscribePostNotifications(this);

    }

    public void updatePosts() throws IOException{
        List<Post> posts = GUILogic.getLogic().getAllPost();
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

<<<<<<< HEAD
=======
    public void addPost(Parent post) {
        postPane.add(post, 0, postPane.getRowCount() + 1);
    }

    @FXML
    private void clickBtnPublish(MouseEvent event) throws IOException {
        GUIController.show("CreatePost");
    }

    @FXML
    private void clickBtnViewProfile(MouseEvent event) throws IOException {
        GUIController.showProfile(user);
    }

>>>>>>> origin/feature
    public void addOnlineUser(String user) {
        listOnlineUsers.getItems().add(user);
    }

    public void addOfflineUser(String user) {
        listOfflineUsers.getItems().add(user);
    }

<<<<<<< HEAD
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

    
    @FXML
    private void clickBtnSendNotification(MouseEvent event) {
        try {
            GUIController.show("SendNotification");
        } catch (IOException ex) {
            Logger.getLogger(FacebootController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
=======
    public void setUser(User user) {
        this.user = user;
    }

>>>>>>> origin/feature
}
