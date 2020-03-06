package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseHelper {
    private Connection con;
    private PreparedStatement stm;
    private String url = "jdbc:mysql://localhost:3306/airticketsales";
    private String name = "akmal";
    private String password = "]WCgDKEN69Wf>zE.";
    public DatabaseHelper(){

    }
    public void insertData(String sql){
        try {
            // 1. get a connection
            con = DriverManager.getConnection(url, name, password);

            //2. create a statement
            stm = con.prepareStatement(sql);

            // 3. execute sql statement
            stm.executeUpdate();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
