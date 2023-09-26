package LibraryFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginWindow extends JFrame implements ActionListener {

    JLabel loginLabel, passwordLabel;
    JTextField loginTextField;
    JPasswordField passwordField;
    JButton loginButton, clearButton;


    public LoginWindow() {
        super("Login");
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);
//          adding components
        loginLabel = new JLabel("Login",null,SwingConstants.RIGHT);
        loginLabel.setBounds(76,62,75,17);
        this.add(loginLabel);
        passwordLabel = new JLabel("Password",null,SwingConstants.RIGHT);
        passwordLabel.setBounds(76, 95, 75, 17);
        this.add(passwordLabel);
        loginTextField = new JTextField();
        loginTextField.setBounds(160, 58, 150, 25);
        this.add(loginTextField);
        passwordField = new JPasswordField();
        passwordField.setBounds(160, 91, 150, 25);
        this.add(passwordField);
        loginButton = new JButton("Login");
        loginButton.setBounds(70, 210, 90, 25);
        this.add(loginButton);
        clearButton = new JButton("Clear");
        clearButton.setBounds(170, 210, 70, 25);
        this.add(clearButton);
//        registerButton = new JButton("Register...");
//        registerButton.setBounds(250, 210, 110, 25);
//        this.add(registerButton);
//        add action listeners
        loginButton.addActionListener(this);
        clearButton.addActionListener(this);


        Dimension rozdzielnosc = Toolkit.getDefaultToolkit().getScreenSize();
        int widthScreen = (int) rozdzielnosc.getWidth();
        int highScreen = (int) rozdzielnosc.getHeight();
        int widthWindow = 400;
        int hightWindow = 310;

        this.setLayout(new BorderLayout());
        this.setSize(widthWindow, hightWindow);
        this.setLocation(widthScreen / 2 - widthWindow / 2, highScreen / 2 - hightWindow / 2);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginWindow().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton){

            if (DatabaseConnector.logginService(loginTextField.getText(), passwordField.getText())){
                new LibraryDesktop(loginTextField.getText()).setVisible(true);
            }
            else{

            }


//        } else if (e.getSource() == registerButton) {
//            System.out.println("register");
//            new RegisterWindow().setVisible(true);
        } else if (e.getSource() == clearButton) {
            this.loginTextField.setText("");
            this.passwordField.setText("");
        }else{
            System.out.println("something went wrong");
        }
    }
}
