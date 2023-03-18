package com.cleanbast;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CleanBastApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(CleanBastApp.class.getResource("/com/cleanbast/cleaner-view.fxml")));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Image iconPath = new Image(Objects.requireNonNull(CleanBastApp.class.getResourceAsStream("/assets/icon/icon-balai.png")));
        stage.getIcons().add(iconPath);
        stage.setTitle("CleanBast");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}