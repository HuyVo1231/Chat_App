package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ketnoi {
    public static Connection getConnection() {
        // Tạo kết nối nè.
            Connection conn = null;
        
            try {
                String url = "jdbc:mysql://localhost:3306/chat1";
                String username = "root";
                String password = "";          

                conn = DriverManager.getConnection(url,username,password);
                
        } catch (Exception e) {
            e.printStackTrace();
        }
            return conn;
    }
    
    public static void closeConnection(Connection c) {
        // Close kết nối nè
        try {
            if(c!=null) {
                c.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
