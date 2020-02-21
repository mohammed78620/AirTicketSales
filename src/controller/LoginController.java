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
        try {
        //get a connection to database
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airticketsales","akmal","]WCgDKEN69Wf>zE.");
        // create a statement
        Statement stm =  con.createStatement();
        //execute sql query
        ResultSet rs = stm.executeQuery("SELECT username, password,staffType FROM staff WHERE username='" + username + "'");
        //process the result set

        rs.next();
        if (password.equals(rs.getString("password"))){
            String staffType = rs.getString("staffType");
            switch(staffType){
                case "sa":
                    SystemAdminForm systemAdminForm = new SystemAdminForm();
                    systemAdminForm.setVisible(true);
                    break;
                case "om":
                    OfficeManagerForm officeManagerForm = new OfficeManagerForm();
                    officeManagerForm.setVisible(true);
                    break;
                case "ta":
                    TravelAdvisorForm travelAdvisorForm = new TravelAdvisorForm();
                    travelAdvisorForm.setVisible(true);
                    break;
                }
            }else {
                System.out.println("wrong password");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
//        MainFrame mainFrame = new MainFrame();
    }
    public boolean loginAuthenticated(){
        try {
            //get a connection to database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airticketsales","akmal","]WCgDKEN69Wf>zE.");
            // create a statement
            Statement stm =  con.createStatement();
            //execute sql query
            ResultSet rs = stm.executeQuery("SELECT username, password,staffType FROM staff WHERE username='" + username + "'");
            //process the result set

            rs.next();
            if (password.equals(rs.getString("password"))){
                String staffType = rs.getString("staffType");
                switch(staffType){
                    case "sa":
                        SystemAdminForm systemAdminForm = new SystemAdminForm();
                        systemAdminForm.setVisible(true);
                        break;
                    case "om":
                        OfficeManagerForm officeManagerForm = new OfficeManagerForm();
                        officeManagerForm.setVisible(true);
                        break;
                    case "ta":
                        TravelAdvisorForm travelAdvisorForm = new TravelAdvisorForm();
                        travelAdvisorForm.setVisible(true);
                        break;
                    default:
                        return false;
                }
            }else {
                System.out.println("wrong password");
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
