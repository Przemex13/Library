package LibraryFiles;


import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;

public class StaffLibraryTable {
    private JPanel mainPanel, rightPanel;
    private JTable staffTable;
    private JTextField idWorkerTextField, nameTextField, surnameTextField, addressTextField, postcodeTextField, cityTextField, searchTextField, loginTextField;
    private JButton addButton, updateButton, clearButton;
    private JPasswordField passwordTextField;
    private JScrollPane scrollPane;
    private JComboBox statusComboBox;
    private JLabel searchLabel;
    private JPanel leftPanel;
    private JPanel leftBottomPanel;
    private JButton deleteButton;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    public void fetchTable() throws SQLException {
        try {
           DatabaseConnector databaseConnector = new DatabaseConnector();
            String str = "select * from stafflist;";
            databaseConnector.preparedStatement = databaseConnector.connection.prepareStatement(str, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = databaseConnector.preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numbColumns = resultSetMetaData.getColumnCount();
            DefaultTableModel defaultTableModel = (DefaultTableModel) staffTable.getModel();
            defaultTableModel.setRowCount(0);
            while (resultSet.next()){
                Vector vectorRow = new Vector<>();
                for(int i = 0 ; i <= numbColumns ; i++){
                    vectorRow.add(resultSet.getString("id_worker"));
                    vectorRow.add(resultSet.getString("name"));
                    vectorRow.add(resultSet.getString("surname"));
                    vectorRow.add(resultSet.getString("address"));
                    vectorRow.add(resultSet.getString("postcode"));
                    vectorRow.add(resultSet.getString("city"));
                    vectorRow.add(resultSet.getString("login"));
                }
                defaultTableModel.addRow(vectorRow);
            }
            databaseConnector.connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public StaffLibraryTable() throws SQLException {
        JFrame frame = new JFrame("Staff");
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        frame.setSize(900, 800);
        DatabaseConnector databaseConnector = new DatabaseConnector("select * from stafflist;",staffTable);
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
//                wrzuca dane z tabeli do JFieldów
                int selectedRow = staffTable.getSelectedRow();
                TableModel tableModel = staffTable.getModel();
                idWorkerTextField.setText(String.valueOf(tableModel.getValueAt(selectedRow, 0)) );
                nameTextField.setText((String) tableModel.getValueAt(selectedRow, 1));
                surnameTextField.setText((String) tableModel.getValueAt(selectedRow, 2));
                addressTextField.setText((String) tableModel.getValueAt(selectedRow, 3));
                postcodeTextField.setText((String) tableModel.getValueAt(selectedRow, 4));
                cityTextField.setText((String) tableModel.getValueAt(selectedRow, 5));
                loginTextField.setText((String) tableModel.getValueAt(selectedRow, 6));;
//
//
//              if (tableModel.getValueAt(selectedRow, 7).equals("StaffMember")) {
//                    statusComboBox.setSelectedIndex(0)
//                }else {
//                    statusComboBox.setSelectedIndex(1);
//                }

//                TableModel tableModel1 = null;
//                String sqlQuery = String.format("select * from loggintable where id_worker = '%d'", tableModel.getValueAt(selectedRow, 0));
//                DatabaseConnector databaseConnector1 = new DatabaseConnector(sqlQuery, tableModel1);
//                System.out.println(tableModel1.getValueAt(1,4));
//
                //                          geting worker status and fetching to combobox
                String sqlQuery2 = String.format("select * from loggintable where id_worker = '%s'", tableModel.getValueAt(selectedRow,0));
                DatabaseConnector databaseConnector1 = new DatabaseConnector();
                String status = null;
                try {
                    Statement statement = databaseConnector1.connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sqlQuery2);
                    if(resultSet.next()){
                        status = resultSet.getString(4);
                        System.out.println(status);
                    }
                } catch ( SQLException ex) {
                    System.out.println(ex.getMessage());
                }finally {
                    try {
                        databaseConnector1.connection.close();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (status.equals("Admin")) statusComboBox.setSelectedIndex(1);

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
                        String password = passwordTextField.getText();
                        String status = statusComboBox.getSelectedItem().toString();

//                           adding reokord do stafflist
                        String sqlQuery3 = "insert into stafflist (`name`, `surname`, `address`, `postcode`, `city`,`login`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')";
                        String sqlQuery3Formatted = String.format(sqlQuery3,name, surname, address, postcode, city, login);
                        DatabaseConnector databaseConnector = new DatabaseConnector(sqlQuery3Formatted);

//                          fetching id_worker of last record
                        int idWorker = 0;
                        String sqlQuery2 = String.format("select * from stafflist where login = '%s'", login);
                        DatabaseConnector databaseConnector1 = new DatabaseConnector();
                        try {
                            Statement statement = databaseConnector1.connection.createStatement();
                            ResultSet resultSet = statement.executeQuery(sqlQuery2);
                            if(resultSet.next()){
                                idWorker = resultSet.getInt(1);
                                System.out.println(idWorker);
                            }
                        } catch ( SQLException ex) {
                            System.out.println(ex.getMessage());
                        }finally {
                            try {
                                databaseConnector1.connection.close();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
//                       add record with login and password to logingtable
                        String sqlQuery = "insert into loggintable (`id_worker`, `login`, `password`, `userType`) VALUES ('%s', '%s', '%s', '%s')";
                        String sqlQueryFormatted = String.format(sqlQuery,idWorker, login, password, status);
                        DatabaseConnector databaseConnector2 = new DatabaseConnector(sqlQueryFormatted);
                        JOptionPane.showMessageDialog(null, "Dodano pracownika");
//                        int i =  JOptionPane.showInternalConfirmDialog(null,"Jakiś tekst");
                        System.out.println();
                        try {
                            fetchTable();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        idWorkerTextField.setText("");
                        nameTextField.setText("");
                        surnameTextField.setText("");
                        addressTextField.setText("");
                        postcodeTextField.setText("");
                        cityTextField.setText("");
                        loginTextField.setText("");
                        nameTextField.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(null, " Press Clear to prepare space for new record");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "This login is not available any more");
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int number = Integer.parseInt(idWorkerTextField.getText());
                String name = nameTextField.getText();
                String surname = surnameTextField.getText();
                String address = addressTextField.getText();
                String postcode = postcodeTextField.getText();
                String city = cityTextField.getText();
                String login = loginTextField.getText();
                String password = passwordTextField.getText();
                String status = statusComboBox.getSelectedItem().toString();

                String sqlQuery = String.format("UPDATE `stafflist` SET " +
                        "`name`='%s'," +
                        "`surname`='%s'," +
                        "`address`='%s'," +
                        "`postcode`='%s'," +
                        "`city`='%s'," +
                        "`login`='%s' " +
                        "WHERE id_worker = '%d';",
                        name,surname,address, postcode, city, login, number);
                DatabaseConnector databaseConnector1 = new DatabaseConnector(sqlQuery);
                String sqlQuery1 = String.format("UPDATE `loggintable` SET `login`='%s' WHERE id_worker = '%d'; ", login, Integer.parseInt(idWorkerTextField.getText()));
                DatabaseConnector databaseConnector2 = new DatabaseConnector(sqlQuery1);
                try {
                    fetchTable();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
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
        deleteButton.addMouseListener(new MouseAdapter() {



            @Override
            public void mouseClicked(MouseEvent e) {

                String [] yesNoArray = {"Yes, delete please", "No"};
                int deleteConfirm = JOptionPane.showOptionDialog(null, "Are you sure You want to delete?",
                        "Warning",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, yesNoArray , null);

                if (deleteConfirm == 0) {
                    String idWorker = idWorkerTextField.getText();
                    String SQLquery = String.format("DELETE From stafflist WHERE id_worker = '%s';", idWorker);
                    DatabaseConnector databaseConnector = new DatabaseConnector(SQLquery);
                    try {
                        fetchTable();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    idWorkerTextField.setText("");
                    nameTextField.setText("");
                    surnameTextField.setText("");
                    addressTextField.setText("");
                    postcodeTextField.setText("");
                    cityTextField.setText("");
                    loginTextField.setText("");
                    nameTextField.requestFocus();
                }

                try {
                    fetchTable();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
        public static void main (String[]args) throws SQLException {
            new StaffLibraryTable();
        }
    }







