package com.mycompany.gui;

import com.masa.domain.User;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.GUILogic;
import logic.TableUser;

public class TagUserController {

    @FXML
    private Button btnTag;
    @FXML
    private Button btnBack;
    @FXML
    private TableView<TableUser> tblUsers;
    @FXML
    private TableColumn<TableUser, SimpleStringProperty> clId;
    @FXML
    private TableColumn<TableUser, SimpleStringProperty> clName;
    @FXML
    private TableColumn<TableUser, SimpleStringProperty> clEmail;
    @FXML
    private TableColumn<TableUser, SimpleStringProperty> clPhone;

    private List<User> users;

    private List<User> taggedUsers;

    private CreatePostController createPostController;
    
    private CreateCommentController createCommentController;

    @FXML
    private void clickBtnTag(MouseEvent event) {
        addTagUsers();
        Stage stage = (Stage) btnTag.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickBtnBack(MouseEvent event) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    public void fillTableView() {
        tblUsers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        clId = new TableColumn("Id");
        clName = new TableColumn("Name");
        clEmail = new TableColumn("Email");
        clPhone = new TableColumn("Phone");
        tblUsers.getColumns().addAll(clId, clName, clEmail, clPhone);
        users = GUILogic.getLogic().getAllUsers();
        List<TableUser> tableUsers = new ArrayList<>();

        for (User user : users) {
            SimpleStringProperty id = new SimpleStringProperty(user.getId());
            SimpleStringProperty name = new SimpleStringProperty(user.getName());
            SimpleStringProperty email = new SimpleStringProperty(user.getEmail());
            SimpleStringProperty phone = new SimpleStringProperty(user.getPhone());
            TableUser tableUser = new TableUser(id, name, email, phone);
            tableUsers.add(tableUser);
        }

        ObservableList<TableUser> data = FXCollections.observableArrayList(tableUsers);
        clId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        ObservableList<TableUser> otableUsers = FXCollections.observableArrayList(tableUsers);
        tblUsers.setItems(otableUsers);
        clId.setVisible(false);
    }

    private void addTagUsers() {
        List<TableUser> tableUsersList = tblUsers.getSelectionModel().getSelectedItems();
        List<User> usersList = new ArrayList<>();

        for (TableUser user : tableUsersList) {
            User searchedUser = GUILogic.getLogic().getUser(user.getId());
            usersList.add(searchedUser);
        }

        taggedUsers = new ArrayList<>(usersList);
        createPostController.setTaggedUsers(taggedUsers);
        createPostController.enableNotifyBy();
    }

    public void setCreatePostController(CreatePostController cpc) {
        createPostController = cpc;
    }
    
    public void setCreateCommentController(CreateCommentController ccm){
        createCommentController = ccm;
    }
    
}