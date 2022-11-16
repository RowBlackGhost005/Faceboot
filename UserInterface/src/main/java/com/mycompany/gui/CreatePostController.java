package com.mycompany.gui;

import com.masa.domain.Post;
import com.masa.domain.Tag;
import com.masa.domain.User;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
        Post post = new Post(null, txtMessage.getText());
        if (imgView.getImage() != null) {
            String imagePath = imgView.getImage().getUrl();
            Image image = new Image(imagePath);
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
            String currentTimeStamp = dateFormat.format(new Date());
            String savingPath = "./src/main/resources/postImg/" + currentTimeStamp + ".jpg";
            File outputFile = new File(savingPath);
            ImageIO.write(bImage, "JPEG", outputFile);
            post.setImagePath(savingPath);
        }
        if (taggedUsers != null) {
            txtTaggedUsers.setText(taggedUsers.toString());
        }
        if (!txtTags.getText().isBlank()) {
            Tag tags = new Tag(null, txtTags.getText());
            GUIController.createPost(post, tags);
        } else {
            GUIController.createPost(post);
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
