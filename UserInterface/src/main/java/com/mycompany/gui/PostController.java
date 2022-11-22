package com.mycompany.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Andrea
 */
public class PostController implements Initializable {

    @FXML
    private ImageView userPhoto;
    @FXML
    private Label lblUser;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblPostText;
    @FXML
    private Button btnEdit;
    @FXML
    private GridPane commentsPane;
    @FXML
    private AnchorPane PostPane;
    @FXML
    private ImageView postPhoto;
    @FXML
    private Label lblTags;
    @FXML
    private Label lblTaggedUsers;
    @FXML
    private Button btnAddComment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setUserPhoto(Image userPhoto) {
        this.userPhoto.setImage(userPhoto);
    }

    public void setUser(String user) {
        this.lblUser.setText(user);
    }

    public void setDate(String date) {
        this.lblDate.setText(date);
    }
    
    public void setTags(String tags){
        this.lblTags.setText(tags);
    }

    public void setTaggedUsers(String taggedUsers){
        this.lblTaggedUsers.setText(taggedUsers);
    }
    
    public void setPhotoPost(Image image){
        this.postPhoto.setImage(image);
    }
    
    public void setPostText(String postText) {
        this.lblPostText.setText(postText);
    }

    public void enableBtnEdit() {
        this.btnEdit.setVisible(true);
    }

    public void addComment(AnchorPane comment) {

        this.commentsPane.add(comment, 0, commentsPane.getRowCount()+1);
    }
    
    
    public void deleteComment(){
        
    }
}
