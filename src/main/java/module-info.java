module com.cleanbast.cleanbast {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports com.cleanbast.service;
    opens com.cleanbast.service to javafx.fxml;
    exports com.cleanbast.launcher;
    opens com.cleanbast.launcher to javafx.fxml;
    exports com.cleanbast.controller;
    opens com.cleanbast.controller to javafx.fxml;
}