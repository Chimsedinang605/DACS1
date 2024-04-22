package database_java.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public static Connection connectDb() {
        Connection c = null;

        try {

            // Đăng ký driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Sử dụng địa chỉ IPv4 và đặt tên cơ sở dữ liệu
            String url = "jdbc:mysql://127.0.0.1:3306/petshop";
            String user = "root";
            String pass = "";

            // Kết nối đến cơ sở dữ liệu
            c = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return c; // Trả về kết nối
    }
}
