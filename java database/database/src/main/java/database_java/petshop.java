package database_java;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class petshop extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      // hiện cửa sổ đăng nhập
         Parent root = FXMLLoader.load(getClass().getResource("/database_java/login.fxml"));
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.initStyle(StageStyle.TRANSPARENT);
         stage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}

