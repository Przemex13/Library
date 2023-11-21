package LibraryFiles;

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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class ReadersEdition {
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel leftBottomPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton updateButton;
    private JTextField idReaderTextField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField addressTextField;
    private JTextField postcodeTextField;
    private JTextField cityTextField;
    private JPanel rightPanel;
    private JScrollPane scrolPane;
    private JTable readersTable;
    private JTextField searchTextField;
    private JLabel searchLabel;
    private JTextField peselTextField;
    private JLabel peselLabel;

    public void fetchTable() throws SQLException {
        try {
            DatabaseConnector databaseConnector = new DatabaseConnector();
            String str = "select * from readerslist;";
            databaseConnector.preparedStatement = databaseConnector.connection.prepareStatement(str, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = databaseConnector.preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numbColumns = resultSetMetaData.getColumnCount();
            DefaultTableModel defaultTableModel = (DefaultTableModel) readersTable.getModel();
            defaultTableModel.setRowCount(0);
            while (resultSet.next()){
                Vector vectorRow = new Vector<>();
                for(int i = 0 ; i <= numbColumns ; i++){
                    vectorRow.add(resultSet.getString("idReader"));
                    vectorRow.add(resultSet.getString("name"));
                    vectorRow.add(resultSet.getString("surname"));
                    vectorRow.add(resultSet.getString("Pesel"));
                    vectorRow.add(resultSet.getString("address"));
                    vectorRow.add(resultSet.getString("postcode"));
                    vectorRow.add(resultSet.getString("city"));
                }
                defaultTableModel.addRow(vectorRow);
            }
            databaseConnector.connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ReadersEdition() throws SQLException {
        JFrame frame = new JFrame("Readers");
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        frame.setSize(900, 800);
        DatabaseConnector databaseConnector = new DatabaseConnector("select * from readerslist;", readersTable);
        frame.pack();
        TableRowSorter rowSorter = new TableRowSorter(readersTable.getModel());
        readersTable.setRowSorter(rowSorter);

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
        readersTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                wrzuca dane z tabeli do JFieldów
                int selectedRow = readersTable.getSelectedRow();
                TableModel tableModel = readersTable.getModel();
                idReaderTextField.setText(String.valueOf(tableModel.getValueAt(selectedRow, 0)));
                nameTextField.setText((String) tableModel.getValueAt(selectedRow, 1));
                surnameTextField.setText((String) tableModel.getValueAt(selectedRow, 2));
                peselTextField.setText((String)tableModel.getValueAt(selectedRow, 3));
                addressTextField.setText((String) tableModel.getValueAt(selectedRow, 4));
                postcodeTextField.setText((String) tableModel.getValueAt(selectedRow, 5));
                cityTextField.setText((String) tableModel.getValueAt(selectedRow, 6));

//                addButton.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//
//
//                        String name = nameTextField.getText();
//                        String surname = surnameTextField.getText();
//                        String address = addressTextField.getText();
//                        String postcode = postcodeTextField.getText();
//                        String city = cityTextField.getText();
//

//                           wpisuje rekord do stafflist
//                        String sqlQuery3 = "insert into readerslist (`name`, `surname`, `address`, `postcode`, `city`) VALUES ('%s', '%s', '%s', '%s', '%s')";
//                        String sqlQuery3Formatted = String.format(sqlQuery3, name, surname, address, postcode, city);
//                        DatabaseConnector databaseConnector = new DatabaseConnector(sqlQuery3Formatted);
//                        JOptionPane.showMessageDialog(null, "Dodano czytelnika");
//
//                        try {
//                            fetchTable();
//                        } catch (SQLException ex) {
//                            throw new RuntimeException(ex);
//                        }
//                        idReaderTextField.setText("");
//                        nameTextField.setText("");
//                        surnameTextField.setText("");
//                        addressTextField.setText("");
//                        postcodeTextField.setText("");
//                        cityTextField.setText("");
//                        nameTextField.requestFocus();
//                    }
//                });
//                updateButton.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        int number = Integer.parseInt(idReaderTextField.getText());
//                        String name = nameTextField.getText();
//                        String surname = surnameTextField.getText();
//                        String address = addressTextField.getText();
//                        String postcode = postcodeTextField.getText();
//                        String city = cityTextField.getText();
//
//                        String sqlQuery = String.format("UPDATE `readerslist` SET " +
//                                        "`name`='%s'," +
//                                        "`surname`='%s'," +
//                                        "`address`='%s'," +
//                                        "`postcode`='%s'," +
//                                        "`city`='%s'," +
//                                        "WHERE idReader = '%s';",
//                                name, surname, address, postcode, city, number);
//                        DatabaseConnector databaseConnector1 = new DatabaseConnector(sqlQuery);
//                String sqlQuery1 = String.format("UPDATE `loggintable` SET `login`='%s' WHERE id_worker = '%d'; ", login, Integer.parseInt(idReaderTextField.getText()));
//                DatabaseConnector databaseConnector2 = new DatabaseConnector(sqlQuery1);

//                        try {
//                            fetchTable();
//                        } catch (SQLException ex) {
//                            throw new RuntimeException(ex);
//                        }
//                    }
//                });
                clearButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        idReaderTextField.setText("");
                        nameTextField.setText("");
                        surnameTextField.setText("");
                        peselTextField.setText("");
                        addressTextField.setText("");
                        postcodeTextField.setText("");
                        cityTextField.setText("");
                        nameTextField.requestFocus();
                    }
                });

            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = nameTextField.getText();
                String surname = surnameTextField.getText();
                String pesel = peselTextField.getText();
                String address = addressTextField.getText();
                String postcode = postcodeTextField.getText();
                String city = cityTextField.getText();
                String idReader = surname + "_" + name + "_" + pesel;


//                           wpisuje rekord do stafflist
                String sqlQuery3 = "insert into readerslist (`idReader`,`name`, `surname`,`Pesel`, `address`, `postcode`, `city`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')";
                String sqlQuery3Formatted = String.format(sqlQuery3, idReader, name, surname, pesel, address, postcode, city);
                DatabaseConnector databaseConnector = new DatabaseConnector(sqlQuery3Formatted);
                JOptionPane.showMessageDialog(null, "Dodano czytelnika");

                try {
                    fetchTable();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                idReaderTextField.setText("");
                nameTextField.setText("");
                surnameTextField.setText("");
                peselTextField.setText("");
                addressTextField.setText("");
                postcodeTextField.setText("");
                cityTextField.setText("");
                nameTextField.requestFocus();
            }
        });
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                        String idReader = idReaderTextField.getText();
                        String name = nameTextField.getText();
                        String surname = surnameTextField.getText();
                        String pesel = peselTextField.getText();
                        String address = addressTextField.getText();
                        String postcode = postcodeTextField.getText();
                        String city = cityTextField.getText();

                        String sqlQuery = String.format("UPDATE `readerslist` SET " +
                                        "`name`='%s'," +
                                        "`surname`='%s'," +
                                        "`Pesel`='%s'," +
                                        "`address`='%s'," +
                                        "`postcode`='%s'," +
                                        "`city`='%s'" +
                                        " WHERE idReader = '%s';",
                                name, surname, pesel, address, postcode, city, idReader);
                System.out.println(sqlQuery);
                        DatabaseConnector databaseConnector1 = new DatabaseConnector(sqlQuery);
//                String sqlQuery1 = String.format("UPDATE `loggintable` SET `login`='%s' WHERE id_worker = '%d'; ", login, Integer.parseInt(idReaderTextField.getText()));
//                DatabaseConnector databaseConnector2 = new DatabaseConnector(sqlQuery1);
//

                        try {
                            fetchTable();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
//            }
        });
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String idReader = idReaderTextField.getText();
                String SQLquery = String.format("DELETE From readerslist WHERE idReader = '%s';",idReader);
                DatabaseConnector databaseConnector1 = new DatabaseConnector(SQLquery);
                try {
                    fetchTable();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                idReaderTextField.setText("");
                nameTextField.setText("");
                surnameTextField.setText("");
                peselTextField.setText("");
                addressTextField.setText("");
                postcodeTextField.setText("");
                cityTextField.setText("");
                DatabaseConnector databaseConnector2 = new DatabaseConnector(String.format("DROP TABLE IF EXISTS %s", idReader));
            }
        });
    }

    public static void main(String[] args){
        try {
            new ReadersEdition();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}