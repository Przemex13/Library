package LibraryFiles;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddStaffWindow extends JFrame implements ActionListener {

    JLabel nameLabel, surnameLabel, addressLabel, postcodeLabel, cityLabel, userTypeLabel, loginLabel, passwordLabel;
    JTextField nameTextField, surnameTextField, addressTextField1, addressTextField2, postcodeTextField, cityTextField, loginTextField;
    JPasswordField passwordTextField;
    Choice userTypeChoice;

    JButton addButton, cancelButton;
    public AddStaffWindow() {
        super("New Staff Member");
        this.setLayout(null);
        Dimension rozdzielnosc = Toolkit.getDefaultToolkit().getScreenSize();
        int widthScreen = (int) rozdzielnosc.getWidth();
        int highScreen = (int) rozdzielnosc.getHeight();
        int widthWindow = 700;
        int hightWindow = 500;
        this.setSize(widthWindow, hightWindow);
        this.setLocation(widthScreen / 2 - widthWindow / 2, highScreen / 2 - hightWindow / 2);
        this.setVisible(true);
//        adding Jcomponents
        nameLabel = new JLabel("name");
        nameLabel.setBounds(89,75,74,17);
        this.add(nameLabel);
        nameTextField = new JTextField();
        nameTextField.setBounds(165, 71, 360, 25);
        this.add(nameTextField);
        surnameLabel = new JLabel("surname");
        surnameLabel.setBounds(89,115,64,17);
        this.add(surnameLabel);
        surnameTextField = new JTextField();
        surnameTextField.setBounds(165, 111, 360, 25);
        this.add(surnameTextField);
        addressLabel = new JLabel("address");
        addressLabel.setBounds(89,152,64,17);
        this.add(addressLabel);
        addressTextField1 = new JTextField();
        addressTextField1.setBounds(165, 148, 360, 25);
        this.add(addressTextField1);
        addressTextField2 = new JTextField();
        addressTextField2.setBounds(165, 187, 360, 25);
        this.add(addressTextField2);
        cityLabel = new JLabel("city");
        cityLabel.setBounds(89,227,64,17);
        this.add(cityLabel);
        postcodeLabel = new JLabel("postcode");
        postcodeLabel.setBounds(89,231,64,17);
        this.add(postcodeLabel);
        postcodeTextField = new JTextField();
        postcodeTextField.setBounds(165, 266, 360, 25);
        this.add(postcodeTextField);
        cityLabel = new JLabel("city");
        postcodeLabel.setBounds(89,270,64,17);
        this.add(cityLabel);
        cityTextField = new JTextField();
        cityTextField.setBounds(165, 227, 360, 25);
        this.add(cityTextField);
        userTypeLabel = new JLabel("user type");
        userTypeLabel.setBounds(89,308,64,17);
        this.add(userTypeLabel);
        userTypeChoice = new Choice();
        userTypeChoice.setBounds(165, 304, 360, 25);
        userTypeChoice.add("Staff Member");
        userTypeChoice.add("Admin/Menager");
        this.add(userTypeChoice);
        loginLabel = new JLabel("login");
        loginLabel.setBounds(89,348,64,17);
        this.add(loginLabel);
        passwordLabel = new JLabel("password");
        passwordLabel.setBounds(89, 388, 64, 17);
        this.add(passwordLabel);
        loginTextField = new JTextField();
        loginTextField.setBounds(165, 342, 360, 25);
        this.add(loginTextField);
        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(165, 380, 360, 25);
        this.add(passwordTextField);

        addButton = new JButton("Add Worker");
        addButton.setBounds(420,430,100,25);
        this.add(addButton);
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(300,430,103,25);
        this.add(cancelButton);
//        listeners
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton){
            String name, surname, address, postcode, city, userType, queryGenerator1, queryGenerator2, login, password;
            name = nameTextField.getText();
            surname = surnameTextField.getText();
            address = addressTextField1.getText() + " " + addressTextField2.getText();
            postcode = postcodeTextField.getText();
            city = cityTextField.getText();
            login = loginTextField.getText();
            password = String.valueOf(passwordTextField.getPassword());
            userType = userTypeChoice.getSelectedItem();

            if (DatabaseConnector.loginChecker(login)) {

                queryGenerator1 = String.format("insert into stafflist" +
                                "(`name`, `surname`, `address`, `postcode`, `city`) VALUES ('%s', '%s', '%s', '%s', '%s')",
                        name, surname, address, postcode, city);
                queryGenerator2 = String.format("insert into loggintable" +
                                "(`login`, `password`, `userType`) VALUES ('%s', '%s', '%s')", login, password, userType);
                DatabaseConnector databaseConnector1 = new DatabaseConnector(queryGenerator1);
                DatabaseConnector databaseConnector2 = new DatabaseConnector(queryGenerator2);

                this.setVisible(false);
            }
            else{
                loginTextField.setText("");
                passwordTextField.setText("");
                JOptionPane.showMessageDialog(null, "Login jest zajęty");
            }

        }
        else {
            nameTextField.setText("");
            surnameTextField.setText("");
            addressTextField1.setText("");
            addressTextField2.setText("");
            postcodeTextField.setText("");
            cityTextField.setText("");
        }
    }

    public static void main(String[] args) {
        new AddStaffWindow();
    }


}
