package container;

import javax.swing.*;
import java.awt.*;

public class DiscountPanel extends JPanel {
    public JButton backButton;

    public DiscountPanel(){
        setVisible(false);
        setBounds(0,0,150,450);
        backButton = new JButton("back");
        add(backButton);
        add(Box.createRigidArea(new Dimension(0,350)));
    }
}
