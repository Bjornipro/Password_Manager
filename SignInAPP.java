package org.example.passwordmanager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SignInAPP {

    public Scene getScene(Stage stage) {

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 330);

        // Title
        Label title = new Label("Password Manager");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setTextFill(Color.BLACK);

        // Username
        TextField username_sign_in = new TextField();
        username_sign_in.setPromptText("Enter Username");
        username_sign_in.setPrefHeight(30);

        // Password
        PasswordField password_sign_in = new PasswordField();
        password_sign_in.setPromptText("Enter Password");
        password_sign_in.setPrefHeight(30);

        // Confirmation
        PasswordField confirmation = new PasswordField();
        confirmation.setPromptText("Confirm Password");
        confirmation.setPrefHeight(30);

        // Sign up button
        Button signup_button = new Button("Sign In");

        // Log in text
        Label log_in_text = new Label("Log In");
        log_in_text.setTextFill(Color.BLUE);
        log_in_text.setStyle("-fx-underline:true;");

        VBox vertical_box = new VBox();
        vertical_box.setSpacing(20);
        vertical_box.setPadding(new Insets(10));
        vertical_box.setAlignment(Pos.TOP_LEFT);

        HBox horizontal_box = new HBox(signup_button);
        horizontal_box.setAlignment(Pos.CENTER_LEFT);
        StackPane.setAlignment(horizontal_box, Pos.CENTER_LEFT);
        StackPane.setMargin(horizontal_box, new Insets(155,0,0,175));

        StackPane.setAlignment(log_in_text, Pos.BOTTOM_CENTER);
        StackPane.setMargin(log_in_text, new Insets(0,0,40,0));

        vertical_box.getChildren().addAll(title, username_sign_in, password_sign_in, confirmation);
        root.getChildren().addAll(vertical_box, horizontal_box, log_in_text);

        // Create controls and pass the stage
        new Sign_in_controls(stage, username_sign_in, password_sign_in, confirmation, signup_button, log_in_text);

        return scene;
    }
}
