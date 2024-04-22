package database_java.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class ChatModel {
   private ObservableList<String> messageList = FXCollections.observableArrayList();

    public ObservableList<String> getMessageList() {
        return messageList;
    }

    public void addMessage(String message) {
        messageList.add(message);
    }
}