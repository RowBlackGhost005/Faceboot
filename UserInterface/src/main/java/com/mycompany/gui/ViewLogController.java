/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gui;

import com.masa.domain.Log;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import logic.GUILogic;

/**
 * FXML Controller class
 *
 * @author Andrea
 */
public class ViewLogController implements Initializable {

    @FXML
    private ListView<String> listLogs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
        List<Log> logs = GUILogic.getLogic().getAllLogs();
        for (Log log : logs) {
            listLogs.getItems().add(log.getDate()+" "+log.getLevel()+" "+log.getMessage());
        }

        // TODO
    }
    
}
