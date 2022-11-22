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
import javafx.fxml.Initializable;
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

<<<<<<< HEAD
    public AnchorPane builComment(Comment comment) throws IOException {
=======
    public AnchorPane buildComment(Comment comment) throws IOException {
>>>>>>> origin/feature
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("Comment.fxml"));
        AnchorPane commentTamplate = (AnchorPane) fxmlLoader.load();
        CommentController controller = fxmlLoader.<CommentController>getController();

        controller.setCommentText(comment.getMessage());
        controller.setUser(comment.getUser().getName());
        controller.setDate(comment.getDateTime().toString());

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
    
    public void buildRegisterPhone(User user) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("RegisterPhone.fxml"));
        Parent registerPhone = (Parent) fxmlLoader.load();
        RegisterPhoneController controller = fxmlLoader.<RegisterPhoneController>getController();
        controller.setUser(user);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(registerPhone));
        stage.show();

    }
    
//    public Initializable buildController(String fxmlName, Initializable controllerName ) throws IOException{
//        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource(fxmlName));
//        Parent postTamplate = (Parent) fxmlLoader.load();
//        Initializable controller = fxmlLoader.<>getController();
//    }

<<<<<<< HEAD
    public Parent builPost(Post post) throws IOException {
=======
    public Parent buildPost(Post post) throws IOException {
>>>>>>> origin/feature
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("Post.fxml"));
        Parent postTamplate = (Parent) fxmlLoader.load();
        PostController controller = fxmlLoader.<PostController>getController();

        controller.setPostText(post.getMessage());

        controller.setUser(post.getUser().getName());

        controller.setDate(post.getDateTime().toString());
<<<<<<< HEAD

        if (post.getComments() != null) {
            for (Comment comment : post.getComments()) {
                controller.addComment(builComment(comment));
            }

        }

=======
        if(post.getImagePath() != null) {
        controller.setPhotoPost(new Image(post.getImagePath()));
        }

        if (post.getComments() != null) {
            for (Comment comment : post.getComments()) {
                controller.addComment(buildComment(comment));
            }

        }

>>>>>>> origin/feature
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
