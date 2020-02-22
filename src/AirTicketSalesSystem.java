import controller.LoginController;
import gui.LoginForm;
import gui.OfficeManagerForm;
import gui.SystemAdminForm;
import gui.TravelAdvisorForm;

public class AirTicketSalesSystem  {
    public AirTicketSalesSystem() {

    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        //remove when finishing updating gui
        OfficeManagerForm officeManagerForm = new OfficeManagerForm();
        officeManagerForm.setVisible(true);
        SystemAdminForm systemAdminForm = new SystemAdminForm();
        systemAdminForm.setVisible(true);
        TravelAdvisorForm travelAdvisorForm = new TravelAdvisorForm();
        travelAdvisorForm.setVisible(true);

    }
}
