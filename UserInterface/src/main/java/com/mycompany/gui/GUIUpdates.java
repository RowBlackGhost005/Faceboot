package com.mycompany.gui;

import com.masa.domain.Post;
import com.masa.utils.IObserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import logic.GUILogic;

public class GUIUpdates implements IObserver {

    private FacebootController controller;

    public GUIUpdates(FacebootController controller) {
        this.controller = controller;
    }

    public void initObserver() {
//        GUILogic.getLogic().subscribeGUINotifications(this);
    }

    public void stopObserver() {
//        GUILogic.getLogic().unSubscribeGUINotifications(this);
    }

    @Override
    public void update(Object o) {
        GUIBuilder builder = new GUIBuilder();
        
        // Avoid throwing IllegalStateException by running from a non-JavaFX thread.
        Platform.runLater(
                () -> {
                    try {
                        controller.addPost(builder.buildPost((Post) o));
                    } catch (IOException ex) {
                        Logger.getLogger(GUIUpdates.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

    }

}
