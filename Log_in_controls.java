package org.example.passwordmanager;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Log_in_controls{
    private Stage stage;
    private Button loginButton;
    private TextField username_text;
    private PasswordField password_text;
    private Label sign_in_button;
    //constructor
    public Log_in_controls(Stage stage, Button loginButton, TextField username_text,
                           PasswordField password_text, Label sign_in_button){
        this.stage = stage;
        this.loginButton = loginButton;
        this.username_text = username_text;
        this.password_text = password_text;
        this.sign_in_button = sign_in_button;
        handle_actions();
    }
    //calls the events
    private void handle_actions(){
        loginButton.setOnMouseClicked(e -> Log_in_events());
        sign_in_button.setOnMouseClicked(e -> sign_in_label());
    }

    private void Log_in_events(){
        boolean empty = false;

        //missing password or username
        if(username_text.getText().isEmpty()){
            username_text.setText("Enter username");
            username_text.setStyle("-fx-text-fill: red; -fx-border-color: red;");
            username_text.setOnMouseClicked(e->{ username_text.clear(); username_text.setStyle("");});
            empty = true ;

        } else {
            username_text.setStyle("");
        }

        if(password_text.getText().isEmpty()){
            password_text.setPromptText("Enter password");
            password_text.setStyle("-fx-text-fill: red; -fx-border-color: red;");
            password_text.setOnMouseClicked(e -> { password_text.clear(); password_text.clear();});
            empty = true;
        } else {
            password_text.setStyle("");
        }
        if(empty) return;
        //checking if there exist an account
        User user =  Storage.find(username_text.getText());
        if(user== null){
            username_text.clear();
            username_text.setPromptText("User does not exist!");
            username_text.setStyle("-fx-text-fill: red; -fx-border-color: red;");
            return ;
        }
        //checks password
        String input =  Hashing.generate_hash(password_text.getText());
        if(!input.equals(user.getHashedpassword())){
            password_text.clear();
            password_text.setPromptText("Wrong password!");
            password_text.setStyle("-fx-text-fill: red; -fx-border-color: red;");
            return;

        }

        // ==== ADD THIS LINE ====
        Session.setUser(user); // This sets the user session!
        // =======================

        DashBord dashBord = new DashBord();
        stage.setScene(dashBord.getScene(stage));
        stage.show();


    }

    private void sign_in_label(){
        try{
            SignInAPP signin_app = new SignInAPP();
            stage.setScene(signin_app.getScene(stage));
            stage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}