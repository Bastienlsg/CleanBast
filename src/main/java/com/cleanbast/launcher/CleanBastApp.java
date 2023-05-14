package com.cleanbast.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.cleanbast.constants.Constants.*;

public class CleanBastApp extends Application {

    // This method is called when the application is launched.
    // It loads the FXML file for the GUI and creates a scene for the application.
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_PATH));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Set the application icon image
        Image iconPath = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(ICON_PATH)));
        stage.getIcons().add(iconPath);

        // Set the css
        String css = Objects.requireNonNull(this.getClass().getResource(CSS_PATH)).toExternalForm();
        scene.getStylesheets().add(css);

        // Set the title and window properties
        stage.setTitle("CleanBast");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    // This method is the main entry point of the application.
    public static void main(String[] args) {
        launch(args);
    }
}