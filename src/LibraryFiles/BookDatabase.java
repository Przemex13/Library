package LibraryFiles;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookDatabase {
    private JPanel BookDatabasePanel;
    private JTable bookDatabaseTable;
    private JButton okButton;
    private JTextField searchTextField;

    public BookDatabase() {
        JFrame frame = new JFrame("Book Database");
        frame.setContentPane(BookDatabasePanel);
        frame.setSize(700,500);
        frame.setVisible(true);
        DatabaseConnector databaseConnector = new DatabaseConnector(" SELECT  booktable.author, booktable.title, " +
                "inventory.id_copy, inventory.reader, " +
                "inventory.rent_date FROM inventory INNER JOIN booktable ON inventory.id_book = booktable.id_book;",
                bookDatabaseTable);
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
        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        new BookDatabase();
    }
}
