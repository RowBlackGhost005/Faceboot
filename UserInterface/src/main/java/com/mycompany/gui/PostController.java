package com.mycompany.gui;

import com.masa.domain.Comment;
import com.masa.domain.Post;
import com.masa.domain.Tag;
import com.masa.domain.User;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import logic.GUILogic;

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
    @FXML
    private GridPane MainPane;
    @FXML
    private TextField txtComment;

    private String idPost;
    
    private Post post;
    
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
    
    public GridPane getPost(){
        return MainPane;
    }
    public void setPostPane(GridPane post){
        MainPane=post;
    }
    public void deleteComment(){
        
    }

    public void setIdPost(String idPost){
        this.idPost=idPost;
    }
    @FXML
    private void btnAddComment(MouseEvent event) throws IOException {
        comment();
      
    }
    private void comment() throws IOException{
        if(!txtComment.getText().equals("")){
        Comment comment = new Comment(GUILogic.getLogic().getUserLogged(),new Date(),txtComment.getText(),idPost);
        Comment newComment = GUILogic.getLogic().createComment(comment, true);
        comment.setId(newComment.getId());
        GUIBuilder builder = new GUIBuilder();
        this.addComment(builder.buildComment(comment));
        txtComment.setText("");
        
        }
    }
    
    public void setPost(Post post){
        this.post = post;
    }
    
    public void updatePost(Post post){
        
        setPostText(post.getMessage());

        setUser(post.getUser().getName());
        
        setIdPost(post.getId());

        if (post.getDateTime() != null) {
            setDate(new SimpleDateFormat("HH:mm dd/MM/yyyy").format(post.getDateTime()));
        }
        
        if(post.getUser().getId().equals(GUILogic.getLogic().getUserLogged().getId())){
            enableBtnEdit();
        }
        
        if (post.getImagePath() != null && !post.getImagePath().equalsIgnoreCase("null")) {

            String imagePath = post.getImagePath();

            if (post.getImagePath().startsWith(".")) {

                imagePath = imagePath.substring(2, imagePath.length());

                imagePath = "file:" + imagePath;

                System.out.println(imagePath);

                this.setPhotoPost(new Image(imagePath));
            }
        }
        
         StringBuilder tags = new StringBuilder();
        if (post.getTags() != null) {
            for (Tag tag : post.getTags()) {
                tags.append("#").append(tag.getName()).append(" ");
            }
            setTags(tags.toString());
        }

        StringBuilder users = new StringBuilder();
        if (post.getUsers() != null) {
            for (User user : post.getUsers()) {
                users.append("@").append(user.getName()).append(" ");
            }
            setTaggedUsers(users.toString());
        }
    }
    
    public Post getPostObject(){
        return post;
    }

    @FXML
    private void clickBtnEdit(MouseEvent event) throws IOException {
        GUIBuilder builder = new GUIBuilder();
        GUIController.showEditPost(this);
    }
}
