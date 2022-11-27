package com.mycompany.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller of the comment gui component.
 *
 * @author Andrea
 */
public class CommentController implements Initializable {

    @FXML
    private ImageView userPhoto;
    @FXML
    private Label lblUser;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblCommentText;
    @FXML
    private Button btnEdit;

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
    
      public void setCommentText(String postText) {
        this.lblCommentText.setText(postText);
    }
    
}
