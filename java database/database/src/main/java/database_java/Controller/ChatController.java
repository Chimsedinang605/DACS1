package database_java.Controller;

import database_java.Model.ChatModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatController {
   private ListView<String> chatWindow;
   @FXML
   private TextField chatInput;

   private ChatModel chatModel;

   public void initialize() {
       chatModel = new ChatModel();
       chatWindow.setItems(chatModel.getMessageList());
   }

   @FXML
   private void sendMessage() {
       String message = chatInput.getText();
       chatModel.addMessage(message);
       chatInput.clear();
   }
}
