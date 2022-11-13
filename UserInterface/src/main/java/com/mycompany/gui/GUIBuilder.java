/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

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

}
