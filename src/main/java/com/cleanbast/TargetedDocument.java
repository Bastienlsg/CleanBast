package com.cleanbast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Desktop.getDesktop;

public class TargetedDocument {
    private final ArrayList<File> deletedDocuments;
    private final CleanerController cleanerController;

    public TargetedDocument(CleanerController cleanerController) throws IOException {
        this.deletedDocuments = new ArrayList<>();
        this.cleanerController = cleanerController;
    }

    public ArrayList<File> processForDocumentDeletion(File document) throws IOException {
        if (validTargetedDocument(document)) {
            //methode "haveAllrights" qui vérifie si on a les droits sur le chemin donné et qui sera aussi appelé
            // dans le del à chaque parcours du dossier, elle verif les droits, si le dossier est un dossier caché ( isHidden() )
            // si le dossier a un . devant son nom
            deleteEmptyDocuments(document);
        }

        return this.deletedDocuments;
    }

    public boolean validTargetedDocument(File document) {
        if (!document.exists() || !document.isDirectory()) {
            this.cleanerController.setAlertLabel("Veuillez choisir un dossier avec un chemin valide");
            return false;
        } else {
            this.cleanerController.setAlertLabel("");
            return true;
        }
    }

    public void deleteEmptyDocuments(File document) throws IOException {
        for (File file : document.listFiles()) {
            if (file.isDirectory()) {
                deleteEmptyDocuments(file);
            } else if (file.length() == 0) {
                this.deletedDocuments.add(file);
                getDesktop().moveToTrash(file);
            }
        }

        if (document.listFiles().length == 0) {
            this.deletedDocuments.add(document);
            getDesktop().moveToTrash(document);
        }
    }
}
