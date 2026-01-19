package org.example.passwordmanager;
import javafx.scene.Scene;
import javafx.stage.Stage ;
import  javafx.application.Application;
public class Main extends Application {

    @Override
    public void start(Stage primarystage) throws Exception{
        LogInAPP loginapp = new LogInAPP();
        primarystage.setScene(loginapp.getScene(primarystage));
        primarystage.show();

    }
    public static void main(String[] args){
        launch();
    }
}
