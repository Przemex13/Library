package LibraryFiles;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

public class BookDatabase {
    private JPanel panel1;
    private JTable bookDatabaseTable;
    private JButton okButton;
    private JTextField searchTextField;

    public BookDatabase() {
        JFrame frame = new JFrame("Book Database");
        frame.setContentPane(panel1);
        frame.setSize(400,500);
        frame.setVisible(true);

        DatabaseConnector databaseConnector = new DatabaseConnector(" SELECT  booktable.author, booktable.title, inventory.id_copy, inventory.reader, inventory.rent_date FROM inventory INNER JOIN booktable ON inventory.id_book = booktable.id_book;", bookDatabaseTable);
//        SELECT  booktable.author, booktable.title, inventory.id_copy FROM inventory INNER JOIN booktable ON inventory.id_book = booktable.id_book;

        TableRowSorter rowSorter = new TableRowSorter(bookDatabaseTable.getModel());
        bookDatabaseTable.setRowSorter(rowSorter);

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


    }

    public static void main(String[] args) {
        new BookDatabase();
    }
}
