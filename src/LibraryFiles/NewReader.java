package LibraryFiles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewReader implements ActionListener {
    private JPanel mainNewReaderPanel;
    private JButton addReaderButton;
    private JButton addReaderAnWithdrawButton;
    private JTextField addressReadeTextField1;
    private JTextField addressReadeTextField2;
    private JTextField surnameTextField;
    private JTextField nameReaderTextFields;
    private JTextField postcodeTextField;
    private JTextField cityTextField;
    private JLabel cityLabel;
    private JLabel postcodeLabel;
    private JTextField peselTextField;
    private JPanel peselLabelPanel;
    private JLabel peselLabel;

    public NewReader() {
       JFrame frame = new JFrame("New Reader");
       frame.setContentPane(mainNewReaderPanel);
       frame.setSize(450, 400);
       frame.setVisible(true);
       addReaderButton.addActionListener(this);
       addReaderAnWithdrawButton.addActionListener(this);

    }

    public static void main(String[] args) {
        new NewReader();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameReaderTextFields.getText();
        String surname = surnameTextField.getText();
        String pesel = peselTextField.getText();
        String address = addressReadeTextField1.getText() + " " + addressReadeTextField2.getText();
        String postcode = postcodeTextField.getText();
        String city = cityTextField.getText();
        String idReader = surname + "_" + name + "_" + pesel;

        String SQLQuery = String.format("insert into readerslist(`idReader`, `name`, `surname`, `Pesel`, `address`, `postcode`, `city`) VALUES ('%s', '%s', '%s', '%s', '%s','%s', '%s')",
        idReader, name, surname, pesel, address, postcode, city);
        DatabaseConnector databaseConnector = new DatabaseConnector(SQLQuery);
        String SQLQuery1 = String.format("CREATE TABLE `librarytest`.`%s` (`id_reader` VARCHAR(20) NOT NULL , `borrow_book_date` DATE NOT NULL , `give_back_date` DATE NOT NULL ) ENGINE = InnoDB;", idReader);
        DatabaseConnector databaseConnector1 = new DatabaseConnector(SQLQuery1);
        JOptionPane.showMessageDialog(null, "Readers added");

        nameReaderTextFields.setText("");
        surnameTextField.setText("");
        peselTextField.setText("");
        addressReadeTextField1.setText("");
        addressReadeTextField2.setText("");
        postcodeTextField.setText("");
        cityTextField.setText("");


        if(e.getSource() == addReaderButton){
            System.out.println("addReaderButton");
        }else {
            System.out.println("addReaderAnWithdrawButton");
        }

    }
}
