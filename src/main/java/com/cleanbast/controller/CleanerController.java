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

public class CleanerController {
    private final TargetedDocument targetedDocument;

    // Les quatre champs ci-dessous sont injectés par JavaFX grâce à l'annotation @FXML
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

    // Effectue des initialisations spécifiques à la vue
    @FXML
    public void initialize() {
        Image image = new Image(Objects.requireNonNull(CleanerController.class.getResourceAsStream("/assets/pictures/cleaner.jpg")));
        this.principalImageView.setImage(image);
    }

    // Méthode appelée lors du clic sur le bouton "Supprimer" dans le FXML
    public void requestDeleteDocuments(ActionEvent e) {
        File entryPath = new File(this.entryPathField.getText());
        // Traitement du dossier par TargetedDocument pour récupérer les documents supprimés
        ArrayList<File> deletedDocuments = this.targetedDocument.processForDocumentDeletion(entryPath);
        this.deletedDocumentsView.getItems().addAll(deletedDocuments);
    }

    // Méthode appelée lors du clic sur le bouton "dossier" dans le FXML
    public void openDirectoryChooser(ActionEvent e) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choisir un dossier");
        String selectedDirectory = String.valueOf(directoryChooser.showDialog(null));
        if (!Objects.equals(selectedDirectory, "null")) {
            this.entryPathField.setText(selectedDirectory);
        }
    }

    // Méthode permettant de définir le message d'erreur à afficher sur notValidDocumentLabel
    public void setNotValidDocumentLabel(String notValidMessage) {
        this.notValidDocumentLabel.setText(notValidMessage);
    }
}