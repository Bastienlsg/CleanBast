package com.cleanbast.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

import static com.cleanbast.constants.Constants.*;

public class CleanBastApp extends Application {

    // This method is called when the application is launched.
    // It loads the FXML file for the GUI and creates a scene for the application.
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Scene scene;
        try {
            loader.setLocation(this.getClass().getResource(FXML_PATH));
            Parent root = loader.load();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            // Handle the error if the FXML file cannot be loaded
            System.err.println("Error: Unable to load the FXML file.");
            e.printStackTrace();
            return;
        }

        // Set the application language
        Locale currentLanguage = Locale.getDefault();


        // Set the application icon image
        try {
            Image iconPath = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(ICON_PATH)));
            stage.getIcons().add(iconPath);
        } catch (NullPointerException e) {
            // Handle the error if the icon file is not found
            System.err.println("Error: Unable to load the application icon.");
        }

        // Set the CSS
        try {
            String css = Objects.requireNonNull(this.getClass().getResource(CSS_PATH)).toExternalForm();
            scene.getStylesheets().add(css);
        } catch (NullPointerException e) {
            // Handle the error if the CSS file is not found
            System.err.println("Error: Unable to load the CSS stylesheet.");
        }

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