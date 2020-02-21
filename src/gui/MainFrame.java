package gui;

import gui.OfficeManagerForm;
import gui.SystemAdminForm;
import gui.TravelAdvisorForm;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        JLayeredPane layeredPane = new JLayeredPane();
        OfficeManagerForm officeManagerForm = new OfficeManagerForm();
//        officeManagerForm.setVisible(false);
        SystemAdminForm systemAdminForm = new SystemAdminForm();
//        systemAdminForm.setVisible(false);
        TravelAdvisorForm travelAdvisorForm = new TravelAdvisorForm();
//        travelAdvisorForm.setVisible(false);
        layeredPane.add(officeManagerForm);
        layeredPane.add(systemAdminForm);
        layeredPane.add(travelAdvisorForm);
        add(layeredPane);

    }
    public void setForms(){

    }
}
