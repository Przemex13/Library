package LibraryFiles;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class WithdrawBook {
    private JPanel withdrawBookPanel;
    private JTable tableBook;
    private JButton withdrawBookButton;
    private JButton cancelButton;
    private JTextField searchTextField;
    String idCopyOfDeletedBook;

    public WithdrawBook() {
        JFrame frame  = new JFrame("Withdraw Book");
        frame.setContentPane(withdrawBookPanel);
        frame.setSize(450, 400);
        frame.setVisible(true);

        DatabaseConnector databaseConnector = new DatabaseConnector("Select inventory.id_copy, " +
                "booktable.title, " +
                "booktable.author, " +
                "inventory.enter_data " +
                "FROM inventory INNER JOIN booktable ON inventory.id_book = booktable.id_book;", tableBook);
        try {
            databaseConnector.resultSet.close();
            databaseConnector.statement.close();
            databaseConnector.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        TableRowSorter rowSorter = new TableRowSorter(tableBook.getModel());
        tableBook.setRowSorter(rowSorter);
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchTextField.getText();
                if (!text.trim().equals(0)) {
                    rowSorter.setRowFilter(RowFilter.regexFilter(String.format(".*%s.*$", text)));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchTextField.getText();
                if (!text.trim().equals(0)) {
                    rowSorter.setRowFilter(RowFilter.regexFilter(String.format(".*%s.*$", text)));
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        withdrawBookButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("withdraw");

                DatabaseConnector databaseConnector5 = new DatabaseConnector(String.format("delete from inventory where id_copy = '%s' ", idCopyOfDeletedBook));
                fetchBooktable();
            }
        });
        tableBook.addMouseListener(new MouseAdapter() {



            @Override
            public void mouseClicked(MouseEvent e) {

                int selectedRow = tableBook.getSelectedRow();
                TableModel tableBookTableModel = tableBook.getModel();
                System.out.println(tableBookTableModel.getRowCount());
                idCopyOfDeletedBook = (String) tableBookTableModel.getValueAt(selectedRow, 0);
                System.out.println(idCopyOfDeletedBook);
                System.out.println(tableBook.getSelectedRow());
            }
        });
    }
    void fetchBooktable() {

        try {
            DatabaseConnector databaseConnector = new DatabaseConnector();
            String str = "Select inventory.id_copy, " +
                    "booktable.title, " +
                    "booktable.author, " +
                    "inventory.enter_data " +
                    "FROM inventory INNER JOIN booktable ON inventory.id_book = booktable.id_book;";
            databaseConnector.preparedStatement = databaseConnector.connection.prepareStatement(str, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = databaseConnector.preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numbColumns = resultSetMetaData.getColumnCount();
            DefaultTableModel defaultTableModel = (DefaultTableModel) tableBook.getModel();
            defaultTableModel.setRowCount(0);
            while (resultSet.next()){
                Vector vectorRow = new Vector<>();
                for(int i = 0 ; i <= numbColumns ; i++){
                    vectorRow.add(resultSet.getString("id_copy"));
                    vectorRow.add(resultSet.getString("title"));
                    vectorRow.add(resultSet.getString("author"));
                    vectorRow.add(resultSet.getString("enter_data"));
                }
                defaultTableModel.addRow(vectorRow);
            }
            databaseConnector.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new WithdrawBook();
    }
}
