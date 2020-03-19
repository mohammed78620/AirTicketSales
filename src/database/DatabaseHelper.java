package database;

import java.sql.*;

public class DatabaseHelper {
    private Connection con;
    private String url = "jdbc:mysql://localhost:3306/airticketsales";
    private String name = "root";
    private String password = "ba!E%xxd-9F7_NdQ";
    public DatabaseHelper(){

    }
    public void getConnection() {
        try {
            con = DriverManager.getConnection(url, name, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
