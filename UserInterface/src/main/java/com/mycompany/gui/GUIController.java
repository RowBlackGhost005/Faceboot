package com.mycompany.gui;

import com.masa.businesslogic.BusinessLogic;
import com.masa.businesslogic.IBusinessLogic;
import com.masa.domain.Post;
import com.masa.domain.Tag;
import com.masa.domain.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import javafx.scene.layout.StackPane;
import logic.GUIControllerLogic;

/**
 * JavaFX GUIController
 */
public class GUIController extends Application {

    private static IBusinessLogic businessLogic;
    private static GUIBuilder guiBuilder;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        businessLogic = new BusinessLogic();
        guiBuilder = new GUIBuilder();
        scene = new Scene(loadFXML("Faceboot"), 960, 540);
        stage.setMinWidth(700);
        stage.setMinHeight(400);
        stage.setScene(scene);
        stage.setTitle("Faceboot");
        stage.show();

    }

    static void show(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void showDialog(String titulo, String mensaje, int tipo) throws IOException {
        Stage dialogo = guiBuilder.buildDialog(titulo, mensaje, tipo);
        dialogo.show();
    }

    static void showTagUsersDialog(CreatePostController cpc) throws IOException {
        Stage dialogo = guiBuilder.buildTagUsersDialog(cpc);
        dialogo.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();

    }

    public static User registerUser(User user) throws Exception {
        return businessLogic.registerUser(user, true);
    }

    public static User logIn(User user) throws Exception {
        return businessLogic.login(user);
    }

    public static User getUser(String userId) {
        return businessLogic.getUser(userId);
    }
    
    public static List<User> getAllUsers() {
        return businessLogic.getAllUsers();
    }

    public static void createPost(Post post) {
        businessLogic.createPost(post);
    }

    public static void createPost(Post post, Tag tags) {
        businessLogic.createPost(post, tags);
    }

}
