package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection(){
        try{
            String url = "";
            String user = "";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, user, pass);
            conn.setAutoCommit(true);
            return conn;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
