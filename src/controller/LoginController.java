package controller;

import gui.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    String username;
    String password;

    public LoginController(String username, String password){
        this.username = username;
        this.password = password;


    }
    public boolean loginAuthenticated(){
        try {
            //get a connection to database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airticketsales","root","ba!E%xxd-9F7_NdQ");
            // create a statement
            Statement stm =  con.createStatement();
            //execute sql query
            ResultSet rs = stm.executeQuery("SELECT username, password,staffType,staff_id FROM staff WHERE username='" + username + "'");
            //process the result set
            if(rs.next()) {
                if (username.equals(rs.getString("username")) && password.equals(rs.getString("password"))) {
                    String staffType = rs.getString("staffType");
                    switch (staffType) {
                        case "sa":
                            SystemAdminForm systemAdminForm = new SystemAdminForm(rs.getInt("staff_id"));
                            systemAdminForm.setVisible(true);
                            break;
                        case "om":
                            OfficeManagerForm officeManagerForm = new OfficeManagerForm(rs.getInt("staff_id"));
                            officeManagerForm.setVisible(true);
                            break;
                        case "ta":
                            TravelAdvisorForm travelAdvisorForm = new TravelAdvisorForm(rs.getInt("staff_id"));
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
