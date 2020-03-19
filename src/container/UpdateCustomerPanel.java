package container;

import javax.swing.*;
import java.awt.*;

public class UpdateCustomerPanel extends JPanel {
    public JButton backButton;
    public JButton updateButton;

    public UpdateCustomerPanel(){
        setVisible(false);
        setBounds(0,0,150,450);
        backButton = new JButton("back");
        add(backButton);
        add(Box.createRigidArea(new Dimension(0,15)));
        updateButton = new JButton("update details");
        add(updateButton);
        add(Box.createRigidArea(new Dimension(0,350)));
    }
}
