package LibraryFiles;


import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;

public class StaffLibraryTable {
    private JPanel mainPanel, leftBottomPanel, rightPanel, leftPanel;
    private JTable staffTable;
    private JTextField idWorkerTextField, nameTextField, surnameTextField, addressTextField, postcodeTextField, cityTextField, searchTextField, loginTextField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private JPasswordField passwordTextField;

    private JScrollPane scrolPane;
    private JComboBox statusComboBox;
    private JLabel searchLabel,loginLabel, passwordLabel;

   public void fetchTable(){
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarytest","root", "");
           String str = "SELECT * from stafflist;";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(str);
           staffTable.setModel(DbUtils.resultSetToTableModel(resultSet));

           resultSet.close();
           statement.close();
           connection.close();
           System.out.println("fetch Table() invoked");
       } catch (SQLException | ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
   }
    public StaffLibraryTable() throws SQLException {
        JFrame frame = new JFrame("Staff");
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        frame.setSize(900, 800);
        fetchTable();

        frame.pack();
        TableRowSorter rowSorter = new TableRowSorter(staffTable.getModel());
        staffTable.setRowSorter(rowSorter);

        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchTextField.getText();
                if (!text.trim().equals(0)) {
                    rowSorter.setRowFilter(RowFilter.regexFilter(String.format(".*^%s.*$", text)));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchTextField.getText();
                if (!text.trim().equals(0)) {
                    rowSorter.setRowFilter(RowFilter.regexFilter(String.format(".*^%s.*$", text)));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        staffTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = staffTable.getSelectedRow();
                TableModel tableModel = staffTable.getModel();
                nameTextField.setText((String) tableModel.getValueAt(selectedRow, 1));
                surnameTextField.setText((String) tableModel.getValueAt(selectedRow, 2));
                addressTextField.setText((String) tableModel.getValueAt(selectedRow, 3));
                postcodeTextField.setText((String) tableModel.getValueAt(selectedRow, 4));
                cityTextField.setText((String) tableModel.getValueAt(selectedRow, 5));
                loginTextField.setText((String) tableModel.getValueAt(selectedRow, 6));
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (DatabaseConnector.loginChecker(loginTextField.getText())) {
                    if (idWorkerTextField.getText().equals(null) | idWorkerTextField.getText().equals("")) {
                        String name = nameTextField.getText();
                        String surname = surnameTextField.getText();
                        String address = addressTextField.getText();
                        String postcode = postcodeTextField.getText();
                        String city = cityTextField.getText();
                        String login = loginTextField.getText();
                        String status = statusComboBox.getSelectedItem().toString();

                        String sqlQuery = String.format("insert into stafflist" +
                                        "(`name`, `surname`, `address`, `postcode`, `city`,`login`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", name, surname, address, postcode,
                                city, login);
                        DatabaseConnector databaseConnector = new DatabaseConnector(sqlQuery);
                        JOptionPane.showMessageDialog(null, "Dodano pracownika");
                        TableModel tableModel = staffTable.getModel();
                        System.out.println(tableModel.getRowCount());
                        DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
                        model.addRow(new String[]{ name, surname, address, postcode, city, login});

                        idWorkerTextField.setText("");
                        nameTextField.setText("");
                        surnameTextField.setText("");
                        addressTextField.setText("");
                        postcodeTextField.setText("");
                        cityTextField.setText("");
                        loginTextField.setText("");
                        nameTextField.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(null, "Wciśnij przycisk Clear żę przygotować miejsce na nowy wpis");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Podany login jest już zajęty");

                }
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idWorkerTextField.setText("");
                nameTextField.setText("");
                surnameTextField.setText("");
                addressTextField.setText("");
                postcodeTextField.setText("");
                cityTextField.setText("");
                loginTextField.setText("");
                nameTextField.requestFocus();

            }
        });
    }



        public static void main (String[]args) throws SQLException {
            new StaffLibraryTable();
        }

    }







