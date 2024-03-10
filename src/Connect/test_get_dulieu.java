package Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test_get_dulieu {
    public static void main(String[] args) throws SQLException {
        // Mở kết nối.
        Connection conn = ketnoi.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pre = null;
        // Insert
        String insert = "INSERT INTO user (username, password) VALUES (?, ?)";
        pre = conn.prepareStatement(insert);
        pre.setString(1,"Viet");
        pre.setString(2, "55555555");
        pre.executeUpdate();
        
        // Select
        String select = "select * from user";
        statement = conn.createStatement();
        resultSet = statement.executeQuery(select);
        while(resultSet.next()) {
             String username = resultSet.getString("username");
             String password = resultSet.getString("password");
             
             System.out.println("Tên tài khoản: " + username + " || Mật khẩu: " +password);
         }
         
        ketnoi.closeConnection(conn);
        
    }
}

