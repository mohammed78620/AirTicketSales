package container;

import javax.swing.*;
import java.awt.*;

public class UpdateStockPanel extends JPanel {
    public JButton viewAssignBlankButton;
    public JButton viewAddBlankButton;
    public JButton viewRemoveBlankButton;
    public UpdateStockPanel(){
        setVisible(false);
        setBounds(0,0,150,450);
        viewAssignBlankButton = new JButton("assign blank");
        viewAddBlankButton = new JButton("add blank");
        viewRemoveBlankButton = new JButton("remove blank");
        add(viewAssignBlankButton);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(viewAddBlankButton);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(viewRemoveBlankButton);
    }
}
