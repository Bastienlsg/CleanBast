package com.cleanbast;

import java.io.File;
import java.util.ArrayList;

import static java.awt.Desktop.getDesktop;

public class TargetedDocument {
    private final ArrayList<File> deletedDocuments;
    private final CleanerController cleanerController;

    public TargetedDocument(CleanerController cleanerController) {
        this.deletedDocuments = new ArrayList<>();
        this.cleanerController = cleanerController;
    }

    public ArrayList<File> processForDocumentDeletion(File document) {
        if (validTargetedDocument(document) && rightsOnDocument(document)) {
            deleteEmptyDocuments(document);
        }
        return this.deletedDocuments;
    }

    public boolean validTargetedDocument(File document) {
        if (!document.exists() || !document.isDirectory() || !rightsOnDocument(document)) {
            this.cleanerController.setNotValidDocumentLabel("Veuillez choisir un dossier valide");
            return false;
        } else {
            this.cleanerController.setNotValidDocumentLabel("");
            return true;
        }
    }
    public boolean rightsOnDocument(File document) {
        return document.canWrite() && !document.getName().startsWith(".") && !document.isHidden();
    }

    public void deleteEmptyDocuments(File document) {
        for (File file : document.listFiles()) {
            if (file.isDirectory() && rightsOnDocument(file)) {
                deleteEmptyDocuments(file);
            } else if (file.length() == 0 && rightsOnDocument(file)) {
                this.deletedDocuments.add(file);
                getDesktop().moveToTrash(file);
            }
        }

        if (document.listFiles().length == 0 & rightsOnDocument(document)) {
            this.deletedDocuments.add(document);
            getDesktop().moveToTrash(document);
        }
    }
}
