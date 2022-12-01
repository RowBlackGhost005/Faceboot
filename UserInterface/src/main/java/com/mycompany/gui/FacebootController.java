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
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
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
    private ListView<User> listOnlineUsers;
    @FXML
    private ListView<User> listOfflineUsers;
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
    
    private List<Initializable> postsControllers;
    
    PostController postTest;
    @FXML
    private ContextMenu configMenu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        postsControllers=new ArrayList<>();
        lblUser.setText(GUILogic.getLogic().getUserLogged().getName());
        addOnlineUser(new User("andrea","andrea@gmail.com","6441425218"));
        addOnlineUser(new User("Diego","diego@gmail.com","6441428956"));
        addOfflineUser(new User("luis","luis@gmail.com","6441424568"));
        
       configMenuConfigurations();
        

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
<<<<<<< HEAD
    
    public void addPost(Parent post) {
=======
    public void addPost(PostController post) {
        postsControllers.add(post);
>>>>>>> origin/develop
        for (Node child : postPane.getChildren()) {//moves the posts one row down
            Integer rowIndex = GridPane.getRowIndex(child);
            if (rowIndex != null) {
                GridPane.setRowIndex(child, rowIndex + 1);
            }
        }

        postPane.add(post.getPost(), 0, 0);
    }
    
    public void removePost(Initializable post){
          PostController controller = (PostController) post;
          postPane.getChildren().remove(controller.getPost());
          postsControllers.remove(post);
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
      //  GUIController.showProfile();
      configMenu.show(paneUser, event.getScreenX(), event.getScreenY());
    }

    public void addOnlineUser(User user) {
        listOnlineUsers.getItems().add(user);
    }

    public void addOfflineUser(User user) {
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

            List<Log> logs = GUILogic.getLogic().getAllLogs();
            for(Log log:logs){//print the logger in console
                System.out.println(log.getDate()+" "+log.getLevel()+" "+log.getMessage()+" ");
            }

    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void clickOnlineUser(MouseEvent event) throws IOException {
      User userSelected =  listOnlineUsers.getSelectionModel().getSelectedItem();
      GUIBuilder guiBuilder = new GUIBuilder();
            guiBuilder.buildSendNotification(userSelected);
      
    }

    @FXML
    private void clickOfflineUser(MouseEvent event) throws IOException {
      User userSelected =  listOfflineUsers.getSelectionModel().getSelectedItem();
      GUIBuilder guiBuilder = new GUIBuilder();
            guiBuilder.buildSendNotification(userSelected);
    }
    
    private void configMenuConfigurations(){
         MenuItem item = new MenuItem("Edit Profile");

        item.addEventHandler(EventType.ROOT, event->{
            try {
                GUIController.showProfile();
            } catch (IOException ex) {
                Logger.getLogger(FacebootController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        MenuItem item2 = new MenuItem("Logout");
        configMenu.getItems().add(item);
        configMenu.getItems().add(item2);
    }
}
