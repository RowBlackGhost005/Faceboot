package com.mycompany.gui;

import com.masa.businesslogic.BusinessLogic;
import com.masa.businesslogic.IBusinessLogic;
import com.masa.domain.Notification;
import com.masa.domain.Post;
import com.masa.domain.Tag;
import com.masa.domain.User;
import com.masa.utils.IObserver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import logic.GUILogic;

/**
 * Controller of the Java FX GUI.
 * 
 * This class contains methos to show the multiple windows of the app.
 * 
 * @author Andrea Abigail
 */
public class GUIController extends Application {

    private static GUIBuilder guiBuilder;
    private static Scene scene;

   

    
    /**
     * Starts the application of JavaFX and shows the main window of faceboot.
     * 
     * @param stage Stage to load.
     * @throws IOException Exception while trying to access FXML files of GUI.
     */
    @Override
    public void start(Stage stage) throws IOException {

        GUILogic.getLogic();
        
        guiBuilder = new GUIBuilder();
        scene = new Scene(loadFXML("Login"), 960, 540);
        stage.setMinWidth(700);
        stage.setMinHeight(400);
        stage.setScene(scene);
        stage.setTitle("Faceboot");
        stage.show();
    }

    /**
     * Launch the application.
     * 
     * @param args Java args.
     */
    public static void main(String[] args) {
        launch();

    }
    
    /**
     * Loads the FXML file whose name is the same as given as parameter to display
     * it to the user.
     * 
     * The FXML file should exist in this project.
     * @param fxml FXML name to load and show.
     * @throws IOException Exception while trying to find the FXML file.
     */
    static void show(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Shows a dialog window above the current GUI.
     * 
     * @param title Title of the dialog.
     * @param message Message to show in the dialog.
     * @param type Type of dialog.
     * @throws IOException Excepcion while trying to access FXML file of dialog.
     */
    static void showDialog(String title, String message, int type) throws IOException {
        Stage dialogo = guiBuilder.buildDialog(title, message, type);
        dialogo.show();
    }

    static void showFaceboot() throws IOException {
        guiBuilder.buildFaceboot(scene);
    }

    static void showProfile() throws IOException {
        guiBuilder.buildViewProfile(scene);
    }

    static void showEditProfile() throws IOException {
        guiBuilder.buildEditProfile(scene);
    }

    /**
     * Shows a dialog that contains a table with multiple users to tag.
     * 
     * @param cpc Controller of Create Post.
     * @throws IOException Excepcion while trying to access FXML file of dialog.
     */
    static void showTagUsersDialog(CreatePostController cpc) throws IOException {
        Stage dialog = guiBuilder.buildTagUsersDialog(cpc);
        dialog.show();
    }

    //TODO DOCUMENTATION
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}
