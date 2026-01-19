package org.example.passwordmanager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LogInAPP {

    public Scene getScene(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 300);

        // Title
        Label title = new Label("Password Manager");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setTextFill(Color.BLACK);

        // Username & password
        TextField username = new TextField();
        username.setPromptText("Enter Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Enter Password");

        // Login button
        Button loginButton = new Button("Log In");

        // Sign-up label
        Label sign_in = new Label("Sign Up");
        sign_in.setTextFill(Color.BLUE);
        sign_in.setStyle("-fx-underline:true;");

        // Layout
        VBox vbox = new VBox(20, title, username, password);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        HBox hbox = new HBox(loginButton);
        hbox.setAlignment(Pos.CENTER_LEFT);
        StackPane.setAlignment(hbox, Pos.CENTER_LEFT);
        StackPane.setMargin(hbox, new Insets(100, 0, 0, 175));

        StackPane.setAlignment(sign_in, Pos.BOTTOM_CENTER);
        StackPane.setMargin(sign_in, new Insets(0, 0, 40, 0));

        root.getChildren().addAll(vbox, hbox, sign_in);

        // Attach controllers
        new Log_in_controls(primaryStage, loginButton, username, password, sign_in);

        return scene;
    }
}