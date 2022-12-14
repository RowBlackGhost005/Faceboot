package com.mycompany.gui;

import com.masa.domain.Comment;
import com.masa.domain.Notification;
import com.masa.domain.Post;
import com.masa.domain.Tag;
import com.masa.domain.User;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.GUILogic;

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

    public Stage buildFaceboot(Scene scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("Faceboot.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FacebootController controller = fxmlLoader.<FacebootController>getController();
        controller.setUser(GUILogic.getLogic().getUserLogged());
        Stage stage = new Stage();
        stage.setResizable(false);
        scene.setRoot(root);

        return stage;
    }

    public Stage buildViewProfile(Scene scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("ViewProfile.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        ViewProfileController controller = fxmlLoader.<ViewProfileController>getController();
        controller.setUser(GUILogic.getLogic().getUserLogged());
        controller.fillProfile();
        Stage stage = new Stage();
        stage.setResizable(false);
        scene.setRoot(root);

        return stage;
    }

    public Stage buildEditProfile(Scene scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("EditProfile.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        EditProfileController controller = fxmlLoader.<EditProfileController>getController();
        controller.setUser(GUILogic.getLogic().getUserLogged());
        controller.fillFields();
        Stage stage = new Stage();
        stage.setResizable(false);
        scene.setRoot(root);

        return stage;
    }
    
    public void builEditPost(PostController postController) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("CreatePost.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        CreatePostController controller = fxmlLoader.<CreatePostController>getController();
        controller.setEditPost();
        Post post =postController.getPostObject();
        controller.setMessage(post.getMessage());
        controller.setTaggedUsers(post.getUsers());
        controller.setPostController(postController);
        StringBuilder tags = new StringBuilder();
         if (post.getTags() != null) {
            for (Tag tag : post.getTags()) {
                tags.append("").append(tag.getName()).append(" ");
            }
            controller.setTags(tags.toString());
        }
        controller.setTags(tags.toString());
        
        String imagePath = post.getImagePath();
        if (post.getImagePath() != null && !post.getImagePath().equalsIgnoreCase("null")) {
            if (post.getImagePath().startsWith(".")) {

                imagePath = imagePath.substring(2, imagePath.length());

                imagePath = "file:" + imagePath;

                System.out.println(imagePath);

                controller.setImage(new Image(imagePath));
            }
        }

        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Edit post");
        stage.setResizable(false);
        stage.show();

    }

    public AnchorPane buildComment(Comment comment) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("Comment.fxml"));
        AnchorPane commentTamplate = (AnchorPane) fxmlLoader.load();
        CommentController controller = fxmlLoader.<CommentController>getController();

        if (comment.getDateTime() != null) {
            controller.setDate(new SimpleDateFormat("HH:mm dd/MM/yyyy").format(comment.getDateTime()));
        }
        controller.setComment(comment);
        controller.setCommentText(comment.getMessage());
        controller.setUser(comment.getUser().getName());

        if (comment.getUser().getId().equals(GUILogic.getLogic().getUserLogged().getId())) {
            controller.enableDelete();
        }
        return commentTamplate;

    }

    public AnchorPane buildNotificationHistory(Notification notification) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("Comment.fxml"));
        AnchorPane notificationTemplate = (AnchorPane) fxmlLoader.load();
        CommentController controller = fxmlLoader.<CommentController> getController();
        
        String type = notification.getType();
        
        if(type.equalsIgnoreCase("both")){
            type = "E-MAIL/SMS";
        }
        
        controller.setCommentText(notification.getMessage() + " \nSended by " + type);
        controller.setUser(notification.getTo().getName());
        controller.setDate(new SimpleDateFormat("HH:mm dd/MM/yyyy").format(notification.getDate()));

        return notificationTemplate;
    }

    public void buildSendNotification(User receptor) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("SendNotification.fxml"));
        AnchorPane sendNotificationTamplate = (AnchorPane) fxmlLoader.load();
        SendNotificationController controller = fxmlLoader.<SendNotificationController>getController();
        controller.setUser(receptor);
        Stage stage = new Stage();
        stage.setScene(new Scene(sendNotificationTamplate));
        stage.setTitle("Send notification");
        stage.setResizable(false);
        stage.show();

    }

    public void buildRegisterPhone(User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("RegisterPhone.fxml"));
        Parent registerPhone = (Parent) fxmlLoader.load();
        RegisterPhoneController controller = fxmlLoader.<RegisterPhoneController>getController();
        controller.setUser(user);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(registerPhone,400,600));
        stage.show();
        
    }

    public PostController buildPost(Post post) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("Post.fxml"));
        Parent postTamplate = (Parent) fxmlLoader.load();
        PostController controller = fxmlLoader.<PostController>getController();

        controller.setPostText(post.getMessage());

        controller.setUser(post.getUser().getName());

        if (post.getDateTime() != null) {
            controller.setDate(new SimpleDateFormat("HH:mm dd/MM/yyyy").format(post.getDateTime()));
        }

        if (post.getUser().getId().equals(GUILogic.getLogic().getUserLogged().getId())) {
            controller.enableBtnEdit();
        }

        if (post.getImagePath() != null && !post.getImagePath().equalsIgnoreCase("null")) {

            String imagePath = post.getImagePath();

            if (post.getImagePath().startsWith(".")) {

                imagePath = imagePath.substring(2, imagePath.length());

                imagePath = "file:" + imagePath;

                System.out.println(imagePath);

                controller.setPhotoPost(new Image(imagePath));
            }
        }

        controller.setPost(post);
        controller.updatePost(post);
        if (post.getComments() != null) {
            for (Comment comment : post.getComments()) {
                controller.addComment(buildComment(comment));
            }

        }
        return controller;
    }

    public void buildViewLog() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(GUIBuilder.class.getResource("ViewLog.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Log");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
