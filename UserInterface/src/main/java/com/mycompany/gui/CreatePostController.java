package com.mycompany.gui;

import com.masa.domain.Post;
import com.masa.domain.Tag;
import com.masa.domain.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import logic.GUIControllerLogic;

public class CreatePostController {

    JFileChooser chooser;
    BufferedImage img;
    String filename;
    File file;
    Boolean activeFileChooser = false;
    List<User> taggedUsers;

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
        if (!txtMessage.getText().isBlank()) {
            Post post = new Post(null, txtMessage.getText());
            if (imgView.getImage() != null) {
                String imagePath = imgView.getImage().getUrl();
                post.setImagePath(imagePath);
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
            GUIController.createPost(post);
        } else {
            GUIController.showDialog("Error", "The introduced data is not valid", 1);
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

}
