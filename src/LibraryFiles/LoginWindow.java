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
    JButton loginButton, clearButton, registerButton;


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
        registerButton = new JButton("Register...");
        registerButton.setBounds(250, 210, 110, 25);
        this.add(registerButton);
//        add action listeners
        loginButton.addActionListener(this);
        clearButton.addActionListener(this);
        registerButton.addActionListener(this);


        this.setLayout(new BorderLayout());
        this.setSize(400, 310);
        this.setLocation(300, 310);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginWindow().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton){

            DatabaseConnector databaseConnector = new DatabaseConnector();
            String login = loginTextField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String queryGenerator =
                    "select * from loggins where login = '" + login + "' and password = '" + password + "';";
            System.out.println(queryGenerator);
            try {
                ResultSet resultSet = databaseConnector.statement.executeQuery(queryGenerator);
//                checking email and password
                if (resultSet.next()){
                    String loginLogged = resultSet.getString("login");
                    System.out.println(loginLogged);
                    new LibraryDesktop(loginLogged).setVisible(true);
                    this.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Zły login lub hasło ty tępy chuju");

                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }



        } else if (e.getSource() == registerButton) {
            System.out.println("register");
            new RegisterWindow().setVisible(true);
        } else if (e.getSource() == clearButton) {
            this.loginTextField.setText("");
            this.passwordField.setText("");
        }else{
            System.out.println("something went wrong");
        }
    }
}
