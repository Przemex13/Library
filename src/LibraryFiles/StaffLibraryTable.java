package LibraryFiles;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffLibraryTable {
    private JPanel mainPanel;
    private JPanel leftBottomPanel;
    private JPanel rightPanel;
    private JTable staffTable;
    private JTextField idWorkerTextField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField addressTextField;
    private JTextField postcodeTextField;
    private JTextField cityTextField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField loginTextField;
    private JPasswordField passwordTextField;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JTextField searchTextField;
    private JLabel searchLabel;
    private JScrollPane scrolPane;
    private JPanel leftPanel;
    private JComboBox statusComboBox;

    private int selectedRow;


    public StaffLibraryTable() {
        JFrame frame = new JFrame("Staff");
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        DatabaseConnector.fetchTable(staffTable);
        frame.setSize(900,800);
        frame.pack();

        TableRowSorter rowSorter = new TableRowSorter(staffTable.getModel());
        staffTable.setRowSorter(rowSorter);

        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchTextField.getText();
                if(!text.trim().equals(0)){
                    rowSorter.setRowFilter(RowFilter.regexFilter(String.format(".*^%s.*$",text)));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchTextField.getText();
                if(!text.trim().equals(0)){
                    rowSorter.setRowFilter(RowFilter.regexFilter(String.format(".*^%s.*$",text)));
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
//        staffTable.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                DatabaseConnector databaseConnector = new DatabaseConnector();
//                try {
//                    selectedRow = 0;
//                    selectedRow = staffTable.getSelectedRow();
//                    ResultSet resultSet = databaseConnector.statement.executeQuery("select * from stafflist where id_worker =" + staffTable.getModel().getValueAt(selectedRow, 0));
//                    if(resultSet.next()){
//                        idWorkerTextField.setText(resultSet.getString(1));
//                        nameTextField.setText(resultSet.getString(2));
//                        surnameTextField.setText(resultSet.getString(3));
//                        addressTextField.setText(resultSet.getString(4));
//                        postcodeTextField.setText(resultSet.getString(5));
//                        cityTextField.setText(resultSet.getString(6));
//                    }
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        });



        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    DatabaseConnector.connect();
                    String login = loginTextField.getText();
                    String password = String.valueOf(passwordTextField.getPassword());
                    ResultSet resultSet = DatabaseConnector.connection.createStatement().executeQuery("select * from loggintable where login = '" + login + "'");
                    if (resultSet.next()){
                        JOptionPane.showMessageDialog(null, "Podany login już istnieje");
                    }else {
                    PreparedStatement preparedStatement = null;
                    preparedStatement = DatabaseConnector.connection.prepareStatement
                            ("insert into stafflist(name, surname, address, postcode, city, login)" +
                                    "values(?,?,?,?,?,?)");
                    preparedStatement.setString(1, nameTextField.getText());
                    preparedStatement.setString(2, surnameTextField.getText());
                    preparedStatement.setString(3, addressTextField.getText());
                    preparedStatement.setString(4, postcodeTextField.getText());
                    preparedStatement.setString(5, cityTextField.getText());
                    preparedStatement.setString(6, loginTextField.getText());
//                    preparedStatement.setString(7, passwordTextField.toString());
                    preparedStatement.executeUpdate();

                    PreparedStatement preparedStatement1 = DatabaseConnector.connection.prepareStatement
                            ("insert into loggintable(login, password, userType)" +
                            "values(?,?,?)");
                    preparedStatement1.setString(1, loginTextField.getText());
                    preparedStatement1.setString(2, String.valueOf(passwordTextField.getPassword()));
                    preparedStatement1.setString(3, statusComboBox.getSelectedItem().toString());
                    preparedStatement1.executeUpdate();




                    JOptionPane.showMessageDialog(null, "Added");
                    DatabaseConnector.fetchTable(staffTable);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }



        public static void main (String[]args){
            new StaffLibraryTable();
        }

    }

// --------------wyszukiwarka------------------------

//











//
//
//        } else if (e.getSource() == deleteButton)
//         {
//            try {
//                DatabaseConnector databaseConnector = new DatabaseConnector();
//                PreparedStatement preparedStatement = databaseConnector.connection.prepareStatement("delete from stafflist where id_worker = ?");
//                preparedStatement.setString(1, String.valueOf(staffTable.getModel().getValueAt(selectedRow, 0)));
//                preparedStatement.executeUpdate();
//                tbload();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }





