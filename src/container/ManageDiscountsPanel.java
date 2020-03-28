package container;

import component.PlaceholderTextField;

import javax.swing.*;
import java.awt.*;

public class ManageDiscountsPanel extends JPanel {
    public JButton backButton = new JButton("back");
    public JButton setDiscount = new JButton("set discount");
    public JLabel IDLabel = new JLabel("customer ID: ");
    public PlaceholderTextField IDField = new PlaceholderTextField();
    public JLabel typeLabel = new JLabel("discount type: ");
    public JLabel discountLabel = new JLabel("discount: ");
    public PlaceholderTextField discountTextField = new PlaceholderTextField();
    public JComboBox typeBox = new JComboBox();

    public ManageDiscountsPanel(){
        setVisible(false);
        setBounds(0,0,150,450);
        add(IDLabel);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(IDField);
        add(Box.createRigidArea(new Dimension(0,15)));
        typeBox.addItem("fixed");
        typeBox.addItem("flexible");
        add(typeLabel);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(typeBox);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(discountLabel);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(discountTextField);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(setDiscount);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(backButton);
        add(Box.createRigidArea(new Dimension(0,15)));

    }
}
