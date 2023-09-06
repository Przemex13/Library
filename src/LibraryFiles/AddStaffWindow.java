package LibraryFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStaffWindow extends JFrame implements ActionListener {

    JLabel nameLabel, surnameLabel, addressLabel, postcodeLabel, cityLabel, userTypeLabel;
    JTextField nameTextField, surnameTextField, addressTextField1, addressTextField2, postcodeTextField, cityTextField;
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
        postcodeLabel = new JLabel("postcode");
        postcodeLabel.setBounds(89,231,64,17);
        this.add(postcodeLabel);
        postcodeTextField = new JTextField();
        postcodeTextField.setBounds(165, 227, 360, 25);
        this.add(postcodeTextField);
        cityLabel = new JLabel("city");
        postcodeLabel.setBounds(89,270,64,17);
        this.add(cityLabel);
        cityTextField = new JTextField();
        cityTextField.setBounds(165, 266, 360, 25);
        this.add(cityTextField);
        userTypeLabel = new JLabel("user type");
        userTypeLabel.setBounds(89,308,64,17);
        this.add(userTypeLabel);
        userTypeChoice = new Choice();
        userTypeChoice.setBounds(165, 304, 360, 25);
        userTypeChoice.add(" ");
        userTypeChoice.add("Admin/Menager");
        userTypeChoice.add("Staff Member");
        this.add(userTypeChoice);
        addButton = new JButton("Add Worker");
        addButton.setBounds(420,356,100,25);
        this.add(addButton);
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(300,356,103,25);
        this.add(cancelButton);
//        listeners
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton){
            String name, surname, address, postcode, city, userType;
            name = nameTextField.getText();
            surname = surnameTextField.getText();
            address = addressTextField1.getText() + " " + addressTextField2.getText();
            postcode = postcodeTextField.getText();
            city = cityTextField.getText();

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
