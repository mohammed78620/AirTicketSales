package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper {
    private Connection con;
    private String url = "jdbc:mysql://localhost:3306/airticketsales";
    private String name = "akmal";
    private String password = "]WCgDKEN69Wf>zE.";
    public DatabaseHelper(){

    }
    public Connection getConnection(){
        try{
            con = DriverManager.getConnection(url,name,password);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        finally {
            return con;
        }
    }
}
