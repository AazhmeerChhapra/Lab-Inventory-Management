import java.sql.*;
import java.util.*;

public class test {

        public static Connection getConnection() throws Exception {

        Connection conn=null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "project";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "Jkar2@19";
        try {
            conn = DriverManager.getConnection(url + dbName, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }
}
