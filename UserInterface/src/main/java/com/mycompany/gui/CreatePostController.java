package com.mycompany.gui;

import com.masa.domain.Post;
import com.masa.domain.Tag;
import com.masa.domain.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import logic.GUILogic;

/**
 * FXML Controller of the create post gui component.
 *
 * @author Andrea
 */
public class CreatePostController implements Initializable {

    JFileChooser chooser;
    BufferedImage img;
    String filename;
    File file;
    Boolean activeFileChooser = false;
    List<User> taggedUsers;

    private boolean editing = false;

    @FXML
    private Button btnAttach;
    @FXML
    private Button btnPost;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtMessage;
    @FXML
    private TextField txtTags;
    @FXML
    private ImageView imgView;
    @FXML
    private Button btnTag;
    @FXML
    private Label txtTaggedUsers;
    @FXML
    private ComboBox<String> cmbNotifyBy;
    @FXML
    private AnchorPane MainPane;

    private PostController postController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbNotifyBy.setItems(FXCollections.observableArrayList(
                "Email", "Phone", "All"));
    }

    @FXML
    private void clickBtnPost(MouseEvent event) throws IOException {
        post();

    }

    @FXML
    private void clickBtnDelete(MouseEvent event) throws IOException {

    }

    @FXML
    private void clickBtnBack(MouseEvent event) throws IOException {
        back();
    }

    @FXML
    private void clickBtnAttach(MouseEvent event) throws IOException {
        if (!activeFileChooser) {
            attachImage();
        }
    }

    @FXML
    private void clickBtnTag(MouseEvent event) throws IOException {
        getTaggedUsers();
    }

    private void post() throws IOException {

        Post post = new Post(null, txtMessage.getText(), GUILogic.getLogic().getUserLogged());

        post.setDateTime(new Date());

        String savingPath = null;

        if (!txtMessage.getText().isBlank()) {

            if (imgView.getImage() != null) {
                String imagePath = imgView.getImage().getUrl();
//                if (editing) {
//                    imagePath = imagePath.substring(5, imagePath.length());
//                    imagePath = "./" + imagePath;
//                }
                post.setImagePath(imagePath);
            }else{
                post.setImagePath(null);
            }
            
            if (taggedUsers != null) {
                ArrayList<User> usersList = new ArrayList<>();
                for (User user : taggedUsers) {
                    usersList.add(user);
                }
                post.setUsers(usersList);
            }
            
            if (!txtTags.getText().isBlank()) {
                String[] tagsNamesList = txtTags.getText().split(" ");
                ArrayList<Tag> tagsList = new ArrayList<>();
                for (String tag : tagsNamesList) {
                    tagsList.add(new Tag(null, tag));
                }
                post.setTags(tagsList);
            }

            if (editing) {
                GUIBuilder builder = new GUIBuilder();
                post.setId(postController.getPostObject().getId());
                GUILogic.getLogic().editPost(post, true);
                postController.setPost(post);
                postController.updatePost(post);
                btnPost.getScene().getWindow().hide();
                
            } else {
                GUILogic.getLogic().createPost(post, true);
            }

        } else {
            GUIController.showDialog("Error", "Add a description to the post", 1);
        }
    }

    private void back() throws IOException {
        GUIController.show("Faceboot");
    }

    private void attachImage() {
        activeFileChooser = true;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files",
                "*.bmp", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            Image image = new Image(filePath);
            imgView.setImage(image);
        }
        activeFileChooser = false;
    }

    private void getTaggedUsers() throws IOException {
        GUIController.showTagUsersDialog(this);
    }

    public void setTaggedUsers(List<User> taggedUsers) {
        this.taggedUsers = taggedUsers;
        txtTaggedUsers.setText(taggedUsers.toString());
    }

    public void setEditPost() {
        editing = true;
        btnDelete.setVisible(true);
        btnBack.setVisible(false);
    }

    public void setMessage(String message) {
        txtMessage.setText(message);
    }

    public void setTags(String tags) {
        txtTags.setText(tags);
    }

    public void setTaggedUsers(String users) {
        txtTaggedUsers.setText(users);
    }

    public void enableNotifyBy() {
        cmbNotifyBy.setVisible(true);
    }

    public void setImage(Image image) {
        imgView.setImage(image);
    }

    public void setPostController(PostController postController) {
        this.postController = postController;
    }
}
