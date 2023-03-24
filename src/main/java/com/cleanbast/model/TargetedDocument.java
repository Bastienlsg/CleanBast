package com.cleanbast.model;

import com.cleanbast.controller.CleanerController;

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
        // Vérification que le document est valide et que l'utilisateur a les droits d'accès nécessaires
        if (validTargetedDocument(document) && rightsOnDocument(document)) {
            deleteEmptyDocuments(document);
        }
        return this.deletedDocuments;
    }

    // Vérification qu'il s'agit bien d'un document valide au parcours d'une suppression
    public boolean validTargetedDocument(File document) {
        if (!document.exists() || !document.isDirectory() || !rightsOnDocument(document)) {
            this.cleanerController.setNotValidDocumentLabel("Veuillez choisir un dossier valide");
            return false;
        } else {
            this.cleanerController.setNotValidDocumentLabel("");
            return true;
        }
    }

    // Vérification que l'utilisateur a les droits nécessaires sur le document
    public boolean rightsOnDocument(File document) {
        return document.canWrite() && !document.getName().startsWith(".") && !document.isHidden();
    }

    public void deleteEmptyDocuments(File document) {
        for (File file : document.listFiles()) {
            // Le fichier actuel est un dossier et l'utilisateur a les droits nécessaires ? On fait un appel récursif
            if (file.isDirectory() && rightsOnDocument(file)) {
                deleteEmptyDocuments(file);
                // Le fichier est vide et l'utilisateur a les droits nécessaires ? On le supprime
            } else if (file.length() == 0 && rightsOnDocument(file)) {
                this.deletedDocuments.add(file);
                getDesktop().moveToTrash(file);
            }
        }

        // Le dossier donné est maintenant vide et l'utilisateur a les droits nécessaires ? On le supprime
        if (document.listFiles().length == 0 & rightsOnDocument(document)) {
            this.deletedDocuments.add(document);
            getDesktop().moveToTrash(document);
        }
    }
}
