package com.mycompany.gui;

import com.masa.domain.Comment;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import logic.GUILogic;

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
    
    private Comment comment;
    @FXML
    private AnchorPane mainPane;

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
    
    public void enableDelete(){
        btnEdit.setVisible(true);
    }
    
    public void setComment(Comment comment){
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    @FXML
    private void clickBtnDelete(MouseEvent event) throws IOException {
        try {
            GUILogic.getLogic().deleteComment(comment.getId(), true);
            ((GridPane) mainPane.getParent()).getChildren().remove(mainPane);
        } catch (IOException ex) {
            GUIController.showDialog("Error", ex.getMessage(), 1);
        }
    }
    
}
