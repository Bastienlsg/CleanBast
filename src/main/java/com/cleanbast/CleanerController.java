package com.cleanbast;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CleanerController {

    private final TargetedDocument targetedDocument;

    @FXML
    private TextField entryPathField;
    @FXML
    private Label alertLabel;
    @FXML
    private ListView<File> deletedDocumentsView;

    public CleanerController() throws IOException {
        this.targetedDocument = new TargetedDocument(this);
    }

    public void requestDeleteDocuments(ActionEvent e) throws IOException {
        File entryPath = new File(this.entryPathField.getText());
        ArrayList<File> deletedDocuments = this.targetedDocument.processForDocumentDeletion(entryPath);
        this.deletedDocumentsView.getItems().addAll(deletedDocuments);
    }

    public void setAlertLabel(String alertLabel) {
        this.alertLabel.setText(alertLabel);
    }
}