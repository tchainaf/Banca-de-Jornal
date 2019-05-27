package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection(){
        try{
            String url = "jdbc:sqlserver://localhost;databaseName=BANCA_DE_JORNAL;integratedSecurity=true;";
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(true);
            return conn;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
