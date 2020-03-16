package container;

import component.PlaceholderTextField;

import javax.swing.*;
import java.awt.*;

public class AssignBlankPanel extends JPanel {
    public JButton backButton;
    public JButton assignBlankButton;
    public PlaceholderTextField idStaffTextfield;
    public AssignBlankPanel(){
        setVisible(false);
        setBounds(0,0,150,450);
        JLabel idStaffLabel = new JLabel("staff id");
        idStaffTextfield = new PlaceholderTextField();
        idStaffTextfield.setPlaceholder("id");
        assignBlankButton = new JButton("assign blank");
        backButton = new JButton("back");

        add(idStaffLabel);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(idStaffTextfield);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(assignBlankButton);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(backButton);
        add(Box.createRigidArea(new Dimension(0,350)));
    }
}
