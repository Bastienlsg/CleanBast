package com.cleanbast;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class CleanerController {
    private final TargetedDocument targetedDocument;

    @FXML
    public ImageView principalImageView;
    @FXML
    private TextField entryPathField;
    @FXML
    private Label notValidDocumentLabel;
    @FXML
    private ListView<File> deletedDocumentsView;

    public CleanerController() {
        this.targetedDocument = new TargetedDocument(this);
    }

    @FXML
    public void initialize() {
        Image image = new Image(Objects.requireNonNull(CleanerController.class.getResourceAsStream("/assets/pictures/cleaner.jpg")));
        this.principalImageView.setImage(image);
    }

    public void requestDeleteDocuments(ActionEvent e) {
        File entryPath = new File(this.entryPathField.getText());
        ArrayList<File> deletedDocuments = this.targetedDocument.processForDocumentDeletion(entryPath);
        this.deletedDocumentsView.getItems().addAll(deletedDocuments);
    }

    public void setNotValidDocumentLabel(String notValidMessage) {
        this.notValidDocumentLabel.setText(notValidMessage);
    }

    public void openDirectoryChooser(ActionEvent e) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choisir un dossier");
        String selectedDirectory = String.valueOf(directoryChooser.showDialog(null));
        if (!Objects.equals(selectedDirectory, "null")) {
            this.entryPathField.setText(selectedDirectory);
        }
    }
}