package org.example.passwordmanager;
import javafx.scene.Scene ;
import javafx.stage.Stage ;
import javafx.application.Application;
public class Switching {
    private static Stage stage ;
   public static void set_scene(Stage primaryStage){
       stage =  primaryStage ;
   }
   public static void switch_scene(Scene scene){
       stage.setScene(scene);
       stage.show();
   }
}
