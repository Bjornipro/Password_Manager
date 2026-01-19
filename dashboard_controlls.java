package org.example.passwordmanager;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.control.Alert.AlertType;

public class dashboard_controlls {

    private Stage stage;
    private TableView<Items> table;
    private Safe_items vault;
    private Button logoutBtn, addBtn, deleteBtn, editBtn, copyBtn;

    public dashboard_controlls(Stage stage, TableView<Items> table, Safe_items vault,
                               Button logoutBtn, Button addBtn, Button deleteBtn,
                               Button editBtn, Button copyBtn) {
        this.stage = stage;
        this.table = table;
        this.vault = vault;
        this.logoutBtn = logoutBtn;
        this.addBtn = addBtn;
        this.deleteBtn = deleteBtn;
        this.editBtn = editBtn;
        this.copyBtn = copyBtn;

        handle_actions();
    }

    private void handle_actions() {
        // Logout
        logoutBtn.setOnAction(e -> {
            Session.logout();
            try {
                LogInAPP login = new LogInAPP();
                stage.setScene(login.getScene(stage));
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Add new item
        addBtn.setOnAction(e -> {
            Dialog<Items> dialog = new Dialog<>();
            dialog.setTitle("Add Password");

            // Buttons
            ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

            // Fields
            TextField websiteField = new TextField();
            websiteField.setPromptText("Website");

            TextField usernameField = new TextField();
            usernameField.setPromptText("Username");

            TextField passwordField = new TextField();
            passwordField.setPromptText("Password");

            VBox vbox = new VBox(10, websiteField, usernameField, passwordField);
            dialog.getDialogPane().setContent(vbox);

            // Convert result
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButtonType) {
                    return new Items(websiteField.getText(), usernameField.getText(), passwordField.getText());
                }
                return null;
            });

            Items newItem = dialog.showAndWait().orElse(null);
            if (newItem != null) {
                vault.add(newItem.getWebsite(), newItem.getUsername(), newItem.getPassword());
                System.out.println("DEBUG: Added item to vault. Total items: " + vault.getItems().size());
                System.out.println("DEBUG: Website: " + newItem.getWebsite());
                System.out.println("DEBUG: Username: " + newItem.getUsername());
                System.out.println("DEBUG: Password: " + newItem.getPassword());
                Safe_storage.save_data(vault);
                table.setItems(vault.getItems()); // Refresh table
            }
        });

        // Edit selected item
        editBtn.setOnAction(e -> {
            Items selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("No Selection", "Please select an item to edit.");
                return;
            }

            Dialog<Items> dialog = new Dialog<>();
            dialog.setTitle("Edit Password");

            // Buttons
            ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

            // Fields with current values
            TextField websiteField = new TextField(selected.getWebsite());
            websiteField.setPromptText("Website");

            TextField usernameField = new TextField(selected.getUsername());
            usernameField.setPromptText("Username");

            TextField passwordField = new TextField(selected.getPassword());
            passwordField.setPromptText("Password");

            VBox vbox = new VBox(10, websiteField, usernameField, passwordField);
            dialog.getDialogPane().setContent(vbox);

            // Convert result
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    return new Items(websiteField.getText(), usernameField.getText(), passwordField.getText());
                }
                return null;
            });

            Items editedItem = dialog.showAndWait().orElse(null);
            if (editedItem != null) {
                // Remove old item and add edited one
                vault.remove(selected);
                vault.add(editedItem.getWebsite(), editedItem.getUsername(), editedItem.getPassword());
                System.out.println("DEBUG: Edited item. Total items: " + vault.getItems().size());
                Safe_storage.save_data(vault);
                table.setItems(vault.getItems()); // Refresh table
            }
        });

        // Delete selected item
        deleteBtn.setOnAction(e -> {
            Items selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                System.out.println("DEBUG: Deleting item: " + selected.getWebsite());
                vault.remove(selected);
                System.out.println("DEBUG: After delete. Total items: " + vault.getItems().size());
                Safe_storage.save_data(vault);
                table.setItems(vault.getItems()); // Refresh table
            }
        });

        // Copy password to clipboard
        copyBtn.setOnAction(e -> {
            Items selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(selected.getPassword());
                clipboard.setContent(content);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Password Copied");
                alert.setHeaderText(null);
                alert.setContentText("Password for " + selected.getWebsite() + " copied to clipboard.");
                alert.showAndWait();
            } else {
                showAlert("No Selection", "Please select an item to copy.");
            }
        });

        // Copy password to clipboard on double click
        table.setRowFactory(tv -> {
            TableRow<Items> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Items rowData = row.getItem();
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putString(rowData.getPassword());
                    clipboard.setContent(content);

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Password Copied");
                    alert.setHeaderText(null);
                    alert.setContentText("Password for " + rowData.getWebsite() + " copied to clipboard.");
                    alert.showAndWait();
                }
            });
            return row;
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}