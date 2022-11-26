/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.masa.domain.Comment;
import com.masa.domain.Post;
import com.masa.domain.Tag;
import com.masa.domain.User;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Andrea
 */
public class GUIBuilder {

    public GUIBuilder() {
    }

    /**
     * Creates a new Dialog
     *
     * @param title Dialog title
     * @param message Dialog message
     * @param type Dialog type
     * @return
     * @throws IOException
     */
    public Stage buildDialog(String title, String message, int type) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("Dialog.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        DialogController controller = fxmlLoader.<DialogController>getController();
        controller.setTitle(title);
        controller.setMessage(message);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        switch (type) {
            case 0: {
                controller.setImage(new Image(getClass().getResourceAsStream("/img/valid.png")));
                break;
            }
            case 1: {
                controller.setImage(new Image(getClass().getResourceAsStream("/img/error.png")));
            }
        }
        return stage;
    }

    public Stage buildTagUsersDialog(CreatePostController cpc) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("TagUser.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        TagUserController controller = fxmlLoader.<TagUserController>getController();
        controller.setCreatePostController(cpc);
        controller.fillTableView();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(root, 800, 540));
        stage.show();

        return stage;
    }

    public Stage buildFaceboot(User user, Scene scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("Faceboot.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FacebootController controller = fxmlLoader.<FacebootController>getController();
        controller.setUser(user);
        Stage stage = new Stage();
        stage.setResizable(false);
        scene.setRoot(root);

        return stage;
    }

    public Stage buildViewProfile(User user, Scene scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("ViewProfile.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        ViewProfileController controller = fxmlLoader.<ViewProfileController>getController();
        controller.setUser(user);
        controller.fillProfile();
        Stage stage = new Stage();
        stage.setResizable(false);
        scene.setRoot(root);

        return stage;
    }

    public Stage buildEditProfile(User user, Scene scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("EditProfile.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        EditProfileController controller = fxmlLoader.<EditProfileController>getController();
        controller.setUser(user);
        controller.fillFields();
        Stage stage = new Stage();
        stage.setResizable(false);
        scene.setRoot(root);

        return stage;
    }

    public AnchorPane buildComment(Comment comment) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("Comment.fxml"));
        AnchorPane commentTamplate = (AnchorPane) fxmlLoader.load();
        CommentController controller = fxmlLoader.<CommentController>getController();

        controller.setCommentText(comment.getMessage());
        controller.setUser(comment.getUser().getName());

        StringBuffer tags = new StringBuffer();
        if (comment.getTags() != null) {
            for (Tag tag : comment.getTags()) {
                tags.append("#").append(tag).append(" ");
            }
        }

        StringBuffer users = new StringBuffer();
        if (comment.getTags() != null) {
            for (Tag tag : comment.getTags()) {
                tags.append("@").append(tag).append(" ");
            }
        }
        return commentTamplate;

    }

    public Parent buildPost(Post post) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("Post.fxml"));
        Parent postTamplate = (Parent) fxmlLoader.load();
        PostController controller = fxmlLoader.<PostController>getController();

        controller.setPostText(post.getMessage());
        controller.setUser(post.getUser().getName());

        if (post.getImagePath() != null) {
            controller.setPhotoPost(new Image(post.getImagePath()));
        }

        if (post.getComments() != null) {
            for (Comment comment : post.getComments()) {
                controller.addComment(buildComment(comment));
            }

        }

        StringBuilder tags = new StringBuilder();
        if (post.getTags() != null) {
            for (Tag tag : post.getTags()) {
                tags.append("#").append(tag.getName()).append(" ");
            }
            controller.setTags(tags.toString());
        }

        StringBuilder users = new StringBuilder();
        if (post.getTags() != null) {
            for (User user : post.getUsers()) {
                users.append("@").append(user.getName()).append(" ");
            }
            controller.setTaggedUsers(users.toString());
        }
        return postTamplate;

    }

}
