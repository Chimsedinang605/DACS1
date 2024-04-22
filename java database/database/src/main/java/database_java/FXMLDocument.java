package database_java;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database_java.Controller.database;
import database_java.Model.getData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLDocument implements Initializable {
    @FXML
    private Button close;

    @FXML
    private Button login;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField username;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    void Adminlogin(ActionEvent event) {
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = database.connectDb();
        try {
            Alert alert;

            if (username.getText().isEmpty() || pass.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("lỗi!");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền tất cả thông tin");
                alert.show();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, username.getText());
                prepare.setString(2, pass.getText());

                result = prepare.executeQuery();

                if (result.next()) {
                    getData.username = username.getText();
                    // dashboard
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText(null);
                    alert.setContentText("Đăng nhập thành công!");
                    alert.showAndWait();

                    // Ẩn form login
                    login.getScene().getWindow().hide();
                    // link dashboard
                    Parent root = FXMLLoader.load(getClass().getResource("/database_java/dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.initStyle(StageStyle.TRANSPARENT);

                    stage.setScene(scene);
                    stage.show();

                } else {
                    // error
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Lỗi đăng nhập");
                    alert.setHeaderText(null);
                    alert.setContentText("Sai mật khẩu/tên người dùng");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

}
