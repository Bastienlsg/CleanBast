package com.cleanbast.service;

import com.cleanbast.controller.CleanerController;

import java.io.File;
import java.util.ArrayList;

import static java.awt.Desktop.getDesktop;

public class TargetedDocument {
    private final ArrayList<File> deletedDocuments;
    private final CleanerController cleanerController;

    // Constructor for TargetedDocument class
    public TargetedDocument(CleanerController cleanerController) {
        // Initialize list of deleted documents and reference to the cleaner controller
        this.deletedDocuments = new ArrayList<>();
        this.cleanerController = cleanerController;
    }

    // All the process for the document deletion
    public ArrayList<File> processForDocumentDeletion(File document) {
        try {
            // If the document is valid and the user has rights to delete it
            if (validTargetedDocument(document) && rightsOnDocument(document)) {
                // Delete empty documents in the directory
                deleteEmptyDocuments(document);
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the process
            System.err.println("Error: Document deletion process failed.");
            e.printStackTrace();
        }
        // Return the list of deleted documents
        return this.deletedDocuments;
    }

    // Check if the targeted document is valid
    // Returns a boolean value indicating if the document is valid
    public boolean validTargetedDocument(File document) {
        try {
            // If the document does not exist, is not a directory, or the user does not have rights to delete it
            if (!document.exists() || !document.isDirectory() || !rightsOnDocument(document)) {
                // Set an error message on the cleaner controller and return false
                this.cleanerController.setNotValidDocumentLabel("Please choose a valid folder");
                return false;
            }
            // Clear any previous error messages on the cleaner controller and return true
            this.cleanerController.setNotValidDocumentLabel("");
            return true;
        } catch (Exception e) {
            // Handle any exceptions that occur during the process
            System.err.println("Error: Document validation failed.");
            e.printStackTrace();
            return false;
        }
    }

    // Check if the user has rights to delete a document
    // Returns a boolean value indicating if the user has rights to delete the document
    public boolean rightsOnDocument(File document) {
        return document.canWrite() && !document.getName().startsWith(".") && !document.isHidden();
    }

    // Delete empty documents in a directory recursively
    public void deleteEmptyDocuments(File document) {
        try {
            // For each file in the directory
            for (File file : document.listFiles()) {
                // If the file is a directory and the user has rights to delete it
                if (file.isDirectory() && rightsOnDocument(file)) {
                    // Recursively delete empty documents in the subdirectory
                    deleteEmptyDocuments(file);
                }
                // If the file is empty and the user has rights to delete it
                else if (file.length() == 0 && rightsOnDocument(file)) {
                    // Add the file to the list of deleted documents and move it to the trash
                    this.deletedDocuments.add(file);
                    try {
                        getDesktop().moveToTrash(file);
                    } catch (Exception e) {
                        // Handle any exceptions that occur when moving the file to the trash
                        System.err.println("Error: Failed to move file to trash.");
                        e.printStackTrace();
                    }
                }
            }

            // If the directory is empty and the user has rights to delete it
            if (document.listFiles().length == 0 && rightsOnDocument(document)) {
                // Add the directory to the list of deleted documents and move it to the trash
                this.deletedDocuments.add(document);
                try {
                    getDesktop().moveToTrash(document);
                } catch (Exception e) {
                    // Handle any exceptions that occur when moving the directory to the trash
                    System.err.println("Error: Failed to move directory to trash.");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the process
            System.err.println("Error: Failed to delete empty documents.");
            e.printStackTrace();
        }
    }
}