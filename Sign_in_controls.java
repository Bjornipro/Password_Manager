package org.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Sign_in_controls {

    private Stage stage;
    private TextField usernameField;
    private PasswordField passwordField;
    private PasswordField confirmField;
    private Button signupButton;
    private Label loginLabel;

    public Sign_in_controls(Stage stage, TextField usernameField, PasswordField passwordField,
                            PasswordField confirmField, Button signupButton, Label loginLabel) {
        this.stage = stage;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.confirmField = confirmField;
        this.signupButton = signupButton;
        this.loginLabel = loginLabel;

        handleActions();
    }

    private void handleActions() {
        signupButton.setOnAction(this::Sign_in_event);
        loginLabel.setOnMouseClicked(e -> switchToLogin());
    }

    private void Sign_in_event(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirm = confirmField.getText();

        // Check if fields are empty
        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            showError("All fields are required!");
            return;
        }

        // Check if passwords match
        if (!password.equals(confirm)) {
            showError("Passwords do not match!");
            return;
        }

        // Check if user already exists
        if (Storage.find(username) != null) {
            showError("Username already exists!");
            return;
        }

        // Create new user
        String hashedPassword = Hashing.generate_hash(password);
        User newUser = new User(username, hashedPassword);

        // Save user to storage
        Storage.save_data(newUser);

        // Set session and go to dashboard
        Session.setUser(newUser);

        // Switch to dashboard
        DashBord dash = new DashBord();
        stage.setScene(dash.getScene(stage));
    }

    private void switchToLogin() {
        try {
            LogInAPP login = new LogInAPP();
            stage.setScene(login.getScene(stage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Sign Up Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}