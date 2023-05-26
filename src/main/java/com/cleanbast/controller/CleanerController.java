package com.cleanbast.controller;

import com.cleanbast.service.TargetedDocument;
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

import static com.cleanbast.constants.Constants.IMAGE_PATH;

public class CleanerController {
    private final TargetedDocument targetedDocument;

    // Reference to the principal image view in the FXML file
    @FXML
    public ImageView principalImageView;

    // Reference to the text field where the user enters the path of the folder to clean
    @FXML
    private TextField entryPathField;

    // Reference to the label that displays a message when the entered path is not valid
    @FXML
    private Label notValidDocumentLabel;

    // Reference to the list view that displays the deleted documents
    @FXML
    private ListView<File> deletedDocumentsView;

    // Constructor of the controller, initializes the targetedDocument object
    public CleanerController() {
        this.targetedDocument = new TargetedDocument(this);
    }

    // Method called after the FXML file is loaded, sets the principal image view
    @FXML
    public void initialize() {
        try {
            Image image = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(IMAGE_PATH)));
            this.principalImageView.setImage(image);
        } catch (NullPointerException e) {
            // Handle the error if the image file is not found
            System.err.println("Error: Unable to load the principal image.");
        }
    }

    // Method called when the user clicks on the "Clean" button, processes the selected folder and displays the deleted files
    public void requestDeleteDocuments(ActionEvent e) {
        try {
            File entryPath = new File(this.entryPathField.getText());
            // Process the folder using the targetedDocument object to retrieve the deleted files
            ArrayList<File> deletedDocuments = this.targetedDocument.processForDocumentDeletion(entryPath);
            this.deletedDocumentsView.getItems().addAll(deletedDocuments);
        } catch (NullPointerException ex) {
            // Handle the error if the entry path is not valid
            System.err.println("Error: Invalid entry path.");
        }
    }

    // Method called when the user clicks on the "Browse" button, opens a directory chooser and sets the selected directory in the text field
    public void openDirectoryChooser(ActionEvent e) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choisis un dossier");
        try {
            File selectedDirectory = directoryChooser.showDialog(null);
            if (selectedDirectory != null) {
                this.entryPathField.setText(selectedDirectory.getAbsolutePath());
            }
        } catch (NullPointerException ex) {
            // Handle the error if the directory chooser encounters an issue
            System.err.println("Error: Unable to open the directory chooser.");
        }
    }

    // Method called by the targetedDocument object to set the notValidDocumentLabel message
    public void setNotValidDocumentLabel(String notValidMessage) {
        this.notValidDocumentLabel.setText(notValidMessage);
    }
}