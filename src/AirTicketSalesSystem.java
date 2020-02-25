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
        SystemAdminForm systemAdminForm = new SystemAdminForm();
        systemAdminForm.setVisible(true);

    }
}
