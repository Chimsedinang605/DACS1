package database_java.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import database_java.Model.customerdata;
import database_java.Model.getData;
import database_java.Model.petdata;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class dashboardController implements Initializable {

    @FXML
    private Label home_totalC;

    @FXML
    private Label home_total;

    @FXML
    private Label home_avallable;

    @FXML
    private TextField addpet_price;

    @FXML
    private TextField addpet_age;

    @FXML
    private TextField addpet_breed;

    @FXML
    private Button addpet_btn;

    @FXML
    private AnchorPane addpet_form;

    @FXML
    private TextField addpet_petid;

    @FXML
    private ComboBox<?> addpet_sex;

    @FXML
    private Button addprt_add;

    @FXML
    private Button addprt_clear;

    @FXML
    private TableColumn<petdata, String> addprt_coi_age;

    @FXML
    private TableColumn<petdata, String> addprt_coi_breed;

    @FXML
    private TableColumn<petdata, String> addprt_coi_petid;

    @FXML
    private TableColumn<petdata, String> addprt_coi_sex;

    @FXML
    private TableColumn<petdata, String> addprt_coi_price;

    @FXML
    private Button addprt_delete;

    @FXML
    private TextField addprt_search;

    @FXML
    private TableView<petdata> addprt_tableview;

    @FXML
    private Button addprt_update;

    @FXML
    private Button close_dasboard;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button min_dashboard;

    @FXML
    private Button purchase_add;

    @FXML
    private TextField purchase_amount;

    @FXML
    private Label purchase_balance;

    @FXML
    private ComboBox<?> purchase_breed;

    @FXML
    private Button purchase_btn;

    @FXML
    private TableColumn<customerdata, String> purchase_coi_age;

    @FXML
    private TableColumn<customerdata, String> purchase_coi_breed;

    @FXML
    private TableColumn<customerdata, String> purchase_coi_petid;

    @FXML
    private TableColumn<customerdata, String> purchase_coi_price;

    @FXML
    private TableColumn<customerdata, String> purchase_coi_quanity;

    @FXML
    private TableColumn<customerdata, String> purchase_coi_sex;

    @FXML
    private AnchorPane purchase_form;

    @FXML
    private Button purchase_pay;

    @FXML
    private ComboBox<?> purchase_petid;

    @FXML
    private Spinner<Integer> purchase_quanity;

    @FXML
    private TableView<customerdata> purchase_tableview;

    @FXML
    private Label purchase_total;

    @FXML
    private Label username;

    private Connection connect;
    private PreparedStatement prepare;
    private java.sql.Statement statement;
    private ResultSet result;

    public void homeDisplayAP() {
        String sql = "SELECT COUNT(id) FROM pet";
        connect = database.connectDb();
        int countAP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countAP = result.getInt("COUNT(id)");
            }
            home_avallable.setText(String.valueOf(countAP));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void homeDisplayTt() {
        String sql = "SELECT SUM(total) FROM customer_info";
        connect = database.connectDb();
        double sumTt = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                sumTt = result.getDouble("SUM(total)");
            }

            home_total.setText("$" + String.valueOf(sumTt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void homeDisplayTC() {
        String sql = "SELECT COUNT(id) FROM customer_info";
        connect = database.connectDb();
        int countTC = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countTC = result.getInt("COUNT(id)");
            }

            home_totalC.setText(String.valueOf(countTC));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addPetAdd() {
        String sql = "INSERT INTO pet (pet_id, breed, sex, age, ngay, price) VALUES (?, ?, ? , ?, ?,?)";

        connect = database.connectDb();

        try {

            Alert alert;
            if (addpet_petid.getText().isEmpty()
                    || addpet_breed.getText().isEmpty()
                    || addpet_sex.getSelectionModel().getSelectedItem() == null
                    || addpet_age.getText().isEmpty()
                    || addpet_price.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Lỗi!");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền tất cả thông tin");
                alert.showAndWait();

            } else {

                String checkData = "SELECT pet_id FROM pet WHERE pet_id = ' " + addpet_petid.getText() + " ' ";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Pet Id: " + addpet_petid.getText() + " Đã tồn tại!");
                    alert.showAndWait();

                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addpet_petid.getText());
                    prepare.setString(2, addpet_breed.getText());
                    prepare.setString(3, (String) addpet_sex.getSelectionModel().getSelectedItem());
                    prepare.setString(4, addpet_age.getText());
                    prepare.setString(6, addpet_price.getText());

                    Date ngay = new Date(0);
                    java.sql.Date sqlngay = new java.sql.Date(ngay.getTime());
                    prepare.setString(5, String.valueOf(sqlngay));

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText(null);
                    alert.setContentText("Đã Hoàn tất!");
                    alert.showAndWait();

                    // tableview
                    addPetshowlistdata();
                    //
                    addPetClear();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addPetUpdate() {

        String sql = "UPDATE  pet SET breed = '"
                + addpet_breed.getText() + "', sex = '"
                + addpet_sex.getSelectionModel().getSelectedItem() + "', age = '"
                + addpet_age.getText() + "', price = '"
                + addpet_price.getText() + "' WHERE pet_id = '"
                + addpet_petid.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;
            if (addpet_petid.getText().isEmpty()
                    || addpet_breed.getText().isEmpty()
                    || addpet_sex.getSelectionModel().getSelectedItem() == null
                    || addpet_age.getText().isEmpty()
                    || addpet_price.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Lỗi!");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền tất cả thông tin");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Lỗi!");
                alert.setHeaderText(null);
                alert.setContentText("Bạn chắc chắn muốn cập nhật Pet ID: " + addpet_petid.getText() + "?");
                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText(null);
                    alert.setContentText("Cập nhật thành công!");
                    alert.showAndWait();

                    // tableview
                    addPetshowlistdata();
                    //
                    addPetClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addPetDelete() {
        String sql = "DELETE FROM pet WHERE pet_id = '" + addpet_petid.getText() + "'";
        connect = database.connectDb();

        try {
            Alert alert;
            if (addpet_petid.getText().isEmpty()
                    || addpet_breed.getText().isEmpty()
                    || addpet_sex.getSelectionModel().getSelectedItem() == null
                    || addpet_age.getText().isEmpty()
                    || addpet_price.getText().isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Lỗi!");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền tất cả thông tin");
                alert.showAndWait();

            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Lỗi!");
                alert.setHeaderText(null);
                alert.setContentText("Bạn chắc chắn muốn xóa Pet ID: " + addpet_petid.getText() + "?");
                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText(null);
                    alert.setContentText("Xóa thành công!");
                    alert.showAndWait();

                    // tableview
                    addPetshowlistdata();
                    //
                    addPetClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPetClear() {
        addpet_petid.setText(" ");
        addpet_breed.setText(" ");
        addpet_sex.getSelectionModel().clearSelection();
        addpet_age.setText(" ");
        addpet_price.setText(" ");
    }

    private String[] sexlist = { "Male", "Female" };

    public void addPetSexList() {
        List<String> listS = new ArrayList();

        for (String data : sexlist) {
            listS.add(data);
        }

        ObservableList listD = FXCollections.observableArrayList(listS);
        addpet_sex.setItems(listD);

    }

    public ObservableList<petdata> addPetlistdata() {

        ObservableList<petdata> listdata = FXCollections.observableArrayList();

        String sqp = "SELECT * FROM pet";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sqp);
            result = prepare.executeQuery();
            petdata petD;

            while (result.next()) {
                petD = new petdata(result.getInt("pet_id"), result.getString("breed"), result.getString("sex"),
                        result.getInt("age"), result.getFloat("price"));
                listdata.add(petD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listdata;
    }

    private ObservableList<petdata> addPetList;

    public void addPetshowlistdata() {
        addPetList = addPetlistdata();

        addprt_coi_petid.setCellValueFactory(new PropertyValueFactory<>("pet_id"));
        addprt_coi_breed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        addprt_coi_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        addprt_coi_sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        addprt_coi_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        addprt_tableview.setItems(addPetList);

    }

    public void addPetSelect() {
        petdata petD = addprt_tableview.getSelectionModel().getSelectedItem();
        int num = addprt_tableview.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addpet_petid.setText(String.valueOf(petD.getPet_id()));
        addpet_breed.setText((petD.getBreed()));
        addpet_age.setText(String.valueOf(petD.getAge()));
        addpet_price.setText(String.valueOf(petD.getPrice()));
    }

    @FXML
    void close(ActionEvent event) {
        System.exit(0);

    }

    public void defaultNav() {
        home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right,#FFE4E1 ,#FFB6C1, #FF69B4  ) ;");
    }

    public void displayUsername() {
       String user = getData.username; 

       username.setText(user.substring(0, 1).toUpperCase() + user.substring(1));

    }

    @FXML
    void logout(ActionEvent event) {
        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cảnh báo!");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn thoát?");

            Optional<ButtonType> optional = alert.showAndWait();

            if (optional.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/database_java/login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void minimize(ActionEvent event) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    private double totalP = 0;

    public void purchaseAdd() {
        purchaseCustomerID();
        purchaseQty();

        String sql = "INSERT INTO customer "
                + "(customer_id, pet_id, breed, sex, age, quantity, price, ngay)"
                + "VALUES (?,?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;

            if (purchase_petid.getSelectionModel().getSelectedItem() == null
                    || purchase_breed.getSelectionModel().getSelectedItem() == null
                    || qty == 0) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Lỗi!");
                alert.setHeaderText(null);
                alert.setContentText("Không hợp lệ!");
                alert.showAndWait();
            } else {

                String sexinfo = "";
                String ageinfo = "";
                double price = 0;

                String checkdata = "SELECT * FROM pet WHERE pet_id = '"
                        + purchase_petid.getSelectionModel().getSelectedItem() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkdata);

                if (result.next()) {
                    sexinfo = result.getString("sex");
                    ageinfo = result.getString("age");
                    price = result.getDouble("price");
                }

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, String.valueOf(customer_id));
                prepare.setString(2, (String) purchase_petid.getSelectionModel().getSelectedItem());
                prepare.setString(3, (String) purchase_breed.getSelectionModel().getSelectedItem());
                prepare.setString(4, sexinfo);
                prepare.setString(5, ageinfo);
                prepare.setString(6, String.valueOf(qty));

                totalP = (price * qty);

                prepare.setString(7, String.valueOf(totalP));

                Date ngay = new Date(0);
                java.sql.Date sqlngay = new java.sql.Date(ngay.getTime());

                prepare.setString(8, String.valueOf(sqlngay));
                prepare.executeUpdate();
                // show data once clicked the add b
                purchaseShowList();
                purchaseDisplayTotal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double totalDisplay;

    public void purchaseDisplayTotal() {
        purchaseCustomerID();
        String sql = "SELECT SUM(price) FROM customer WHERE  customer_id = '" + customer_id + "'";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                totalDisplay = result.getDouble("SUM(price)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        purchase_total.setText("$" + totalDisplay);
    }

    private double amount;
    private double balance;

    public void purchaseAmount() {

        Alert alert;
        if (totalDisplay == 0 || purchase_amount.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi!");
            alert.setHeaderText(null);
            alert.setContentText(" Số tiền không hợp lệ!");
            alert.showAndWait();
        } else {
            amount = Double.parseDouble(purchase_amount.getText());
            if (amount < totalDisplay) {
                purchase_amount.setText("");
                balance = 0;
            } else {

                balance = (amount - totalDisplay);

            }
        }
        purchase_balance.setText("$" + balance);
    }

    public void purchasePay() {
        String sql = "INSERT INTO customer_info (customer_id, total, ngay)"
                + "VALUES (?, ?, ?)";

        connect = database.connectDb();
        try {
            Alert alert;
            if (purchase_amount.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Lỗi!");
                alert.setHeaderText(null);
                alert.setContentText("vui lòng chọn sản phẩm trước");
                alert.showAndWait();
            } else {

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Thông báo!");
                alert.setHeaderText(null);
                alert.setContentText("Bạn chắc chắn?");
                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, String.valueOf(customer_id));
                    prepare.setString(2, String.valueOf(totalDisplay));

                    Date ngay = new Date(0);
                    java.sql.Date sqlngay = new java.sql.Date(ngay.getTime());
                    prepare.setString(3, String.valueOf(sqlngay));
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText(null);
                    alert.setContentText("Thành công!");
                    alert.showAndWait();

                    purchase_amount.setText("");
                    purchase_balance.setText("$0.0");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void purchasePetId() {
        String sql = "SELECT * FROM pet";
        connect = database.connectDb();
        try {
            ObservableList listdata = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                listdata.add(result.getString("pet_id"));
            }

            purchase_petid.setItems(listdata);

            purchaseBreed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void purchaseBreed() {
        String sql = "SELECT * FROM pet WHERE pet_id = '" + purchase_petid.getSelectionModel().getSelectedItem() + "'";
        connect = database.connectDb();
        try {
            ObservableList listdata = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                listdata.add(result.getString("breed"));
            }
            purchase_breed.setItems(listdata);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int qty;
    private SpinnerValueFactory<Integer> spinner;

    public void purchaseSpinner() {
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        purchase_quanity.setValueFactory(spinner);
    }

    public void purchaseQty() {
        qty = purchase_quanity.getValue();
    }

    public ObservableList<customerdata> purchaseListData() {

        purchaseCustomerID();
        ObservableList<customerdata> listdata = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer WHERE customer_id = '" + customer_id + "'";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            customerdata customerD;

            while (result.next()) {
                customerD = new customerdata(result.getInt("customer_id"), result.getInt("pet_id"),
                        result.getString("breed"), result.getString("sex"), result.getInt("age"),
                        result.getInt("quantity"), result.getDouble("price"), result.getDate("ngay"));
                listdata.add(customerD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listdata;
    }

    private ObservableList<customerdata> purchaseList;

    public void purchaseShowList() {
        purchaseList = purchaseListData();

        purchase_coi_petid.setCellValueFactory(new PropertyValueFactory<>("pet_id"));
        purchase_coi_breed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        purchase_coi_sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        purchase_coi_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        purchase_coi_quanity.setCellValueFactory(new PropertyValueFactory<>("quanity"));
        purchase_coi_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        purchase_tableview.setItems(purchaseList);
    }

    private int customer_id;

    public void purchaseCustomerID() {
        String sql = "SELECT customer_id  FROM customer";
        connect = database.connectDb();
        int checkCID = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                customer_id = result.getInt("customer_id");
            }

            String checkdata = "SELECT * FROM customer_info";
            statement = connect.createStatement();
            result = statement.executeQuery(checkdata);

            while (result.next()) {
                checkCID = result.getInt("customer_id");
            }

            if (customer_id == 0) {
                customer_id += 1;
            } else if (customer_id == checkCID) {
                customer_id += 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchForm(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addpet_form.setVisible(false);
            purchase_form.setVisible(false);

            // khi nhấn chuột
            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right,#FFE4E1 ,#FFB6C1, #FF69B4  ) ;");
            addpet_btn.setStyle("-fx-background-color:  #FFFFFF;");
            purchase_btn.setStyle("-fx-background-color:  #FFFFFF;");

            homeDisplayAP();
            homeDisplayTC();
            homeDisplayTt();


        } else if (event.getSource() == addpet_btn) {
            home_form.setVisible(false);
            addpet_form.setVisible(true);
            purchase_form.setVisible(false);

            addpet_btn.setStyle("-fx-background-color:linear-gradient(to bottom right,#FFE4E1 ,#FFB6C1, #FF69B4) ;");
            home_btn.setStyle("-fx-background-color:  #FFFFFF;");
            purchase_btn.setStyle("-fx-background-color:  #FFFFFF;");

            //
            addPetshowlistdata();
            addPetSexList();

        } else if (event.getSource() == purchase_btn) {
            home_form.setVisible(false);
            addpet_form.setVisible(false);
            purchase_form.setVisible(true);

            purchase_btn.setStyle("-fx-background-color:linear-gradient(to bottom right,#FFE4E1 ,#FFB6C1, #FF69B4) ;");
            addpet_btn.setStyle("-fx-background-color:  #FFFFFF;");
            home_btn.setStyle("-fx-background-color:  #FFFFFF;");

            purchasePetId();
            purchaseBreed();
            purchaseSpinner();
            purchaseShowList();
            purchaseDisplayTotal();

        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        displayUsername();
        defaultNav();

        homeDisplayAP();
        homeDisplayTC();
        homeDisplayTt();

        addPetSexList();
        addPetshowlistdata();

        purchasePetId();
        purchaseBreed();
        purchaseSpinner();
        purchaseShowList();
        purchaseDisplayTotal();
    }
}
