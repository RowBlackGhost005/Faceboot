module com.mycompany.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.gui to javafx.fxml;
    exports com.mycompany.gui;
}
