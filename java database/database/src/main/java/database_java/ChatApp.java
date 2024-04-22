package database_java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatApp extends Application{

   @Override
   public void start(Stage primaryStage) throws Exception {
      VBox root = new VBox();
      Scene scene = new Scene(root, 600, 400);
      
      // Create UI components (e.g., ListView, TextField)
      ListView<String> chatWindow = new ListView<>();
      TextField chatInput = new TextField();
      
      // Add UI components to the root layout
      root.getChildren().addAll(chatWindow, chatInput);
      
      primaryStage.setTitle("Chat Application");
      primaryStage.setScene(scene);
      primaryStage.show();
   }


   public static void main(String[] args) {
      launch(args);
   }
}
