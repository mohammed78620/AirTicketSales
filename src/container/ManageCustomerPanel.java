package container;

import javax.swing.*;
import java.awt.*;

public class ManageCustomerPanel extends JPanel {
    public JButton viewUpdateButton;
    public JButton manageDiscountButton;
    public JButton backButton;

    public ManageCustomerPanel(){
        setVisible(false);
        setBounds(0,0,150,450);
        viewUpdateButton = new JButton("update customer");
        manageDiscountButton = new JButton("manage discount");
        backButton = new JButton("back");
        add(viewUpdateButton);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(manageDiscountButton);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(backButton);
        add(Box.createRigidArea(new Dimension(0,350)));
    }
}

