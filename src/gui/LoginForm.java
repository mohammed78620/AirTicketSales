package gui;

import component.PlaceholderTextField;
import controller.LoginController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForm extends JFrame {
    private String username;
    private String password;
    private JLabel userIcon = new JLabel(new ImageIcon(new ImageIcon("data/user.png").getImage().getScaledInstance(70, 65, Image.SCALE_SMOOTH)));
    private PlaceholderTextField usernameText = new PlaceholderTextField(15);
    private JPasswordField passwordText = new JPasswordField();
    private JPanel panel = new JPanel();
    private JButton loginButton = new JButton("Login");
    private int spacing = 20;
    private LoginController loginController;


    public LoginForm() {
        //frame setup
        super("AirTicketSales");
        panel.setLayout(null);
        int w = 350;
        int h = 175;
        panel.setSize(w, h);
        this.add(panel);

        this.setLocationRelativeTo(null);

        panel.add(userIcon);
        panel.add(usernameText);
        panel.add(passwordText);
        panel.add(loginButton);

        userIcon.setBounds(w / 5 - 25, h / 2 - 65, 70, 65);
        usernameText.setBounds(userIcon.getX() + spacing * 5, userIcon.getY() , 150, 25);
        passwordText.setBounds(userIcon.getX() + spacing * 5, usernameText. getY() + spacing * 2, 150, 25);
        loginButton.setBounds(userIcon.getX() + spacing * 4, passwordText.getY() + spacing * 2, 100, 25);

        // Customise Login button
        loginButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.DARK_GRAY);
        loginButton.setBorder(new LineBorder(Color.BLACK));

        usernameText.setPlaceholder("Username");

        loginButton.addActionListener(e -> {
            //gets the username and password from JtextField
            username = usernameText.getText();
            password = String.valueOf(passwordText.getPassword());

            if (!(username.isEmpty() || password.isEmpty())) {
//                System.out.println("the username is: " +username +" the password is: " + password);
                LoginController loginController = new LoginController(username, password);
                //sets the login page as invisible and opens officemanagerform
                if (loginController.loginAuthenticated()) {
                    dispose();
                }
                else {
                    usernameText.setPlaceholder("Incorrect login");
                    usernameText.setText("");
                    passwordText.setText("");
                    panel.updateUI();
                }
            }
            else{
                usernameText.setPlaceholder("Please enter details");
                panel.updateUI();
            }

        });
        loginButton.setMnemonic(KeyEvent.VK_ENTER);

        panel.setBackground(Color.WHITE);
        panel.setVisible(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(w, h);
    }
}