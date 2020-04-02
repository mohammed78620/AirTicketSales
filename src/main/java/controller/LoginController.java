package controller;

import database.DatabaseHelper;
import gui.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    String username;
    String password;

    DatabaseHelper db = new DatabaseHelper();
    Connection con = db.getConnection();

    public LoginController(String username, String password){
        this.username = username;
        this.password = password;
    }
    public boolean loginAuthenticated(){
        try {

            //get a connection to database
            Connection con = db.getConnection();
            // create a statement

            Statement stm =  con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT Username, Password, Type, ID FROM staff WHERE Username ='" + username + "'");
            if(rs.next()) {
                if (username.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
                    String staffType = rs.getString(3);
                    switch (staffType) {
                        case "sa":
                            SystemAdminForm systemAdminForm = new SystemAdminForm(rs.getInt(4));
                            systemAdminForm.setVisible(true);
                            break;
                        case "om":
                            OfficeManagerForm officeManagerForm = new OfficeManagerForm(rs.getInt(4));
                            officeManagerForm.setVisible(true);
                            break;
                        case "ta":
                            TravelAdvisorForm travelAdvisorForm = new TravelAdvisorForm(rs.getInt(4));
                            travelAdvisorForm.setVisible(true);
                            break;
                        default:
                            return false;
                    }
                } else {
                    return false;
                }
            }
            else{
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
