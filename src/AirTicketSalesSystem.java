import javax.swing.*;

public class AirTicketSalesSystem  {
    public AirTicketSalesSystem() {

    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        OfficeManagerForm officeManagerForm = new OfficeManagerForm();
        officeManagerForm.setVisible(true);
        TravelAdvisorForm travelAdvisorForm = new TravelAdvisorForm();
        travelAdvisorForm.setVisible(true);
        SystemAdminForm systemAdminForm = new SystemAdminForm();
        systemAdminForm.setVisible(true);
    }
}
