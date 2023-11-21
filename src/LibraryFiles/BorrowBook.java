package LibraryFiles;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BorrowBook {
    private JTextField readerTextField;
    private JPanel borrowBookPanel;
    private JTextField bookCopyNumberTextField;
    private JButton borrowBookButton;
    private JLabel readerLabel;
    private JButton cancelButton;


    public BorrowBook() {
        JFrame frame = new JFrame("borrow book");
        frame.setContentPane(borrowBookPanel);
        frame.setSize(450, 200);
        frame.setVisible(true);
        borrowBookButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String book = bookCopyNumberTextField.getText();
                String reader = readerTextField.getText();

                LocalDate date = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String data = date.format(formatter);
                System.out.println(data);

                String SQLQuery = String.format("UPDATE inventory SET inventory.reader = '%s', inventory.rent_date = '%s' WHERE inventory.id_copy = '%s';",reader, data, book);
                DatabaseConnector databaseConnector = new DatabaseConnector();
                try {
                    PreparedStatement preparedStatement = databaseConnector.connection.prepareStatement(SQLQuery);
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                String SQLQuery1 = String.format("insert into librarytest.%s (`id_reader`, `borrow_book_date`) VALUES ('%s', '%s');",book, reader, data );
                System.out.println(SQLQuery1);

                DatabaseConnector databaseConnector1 = new DatabaseConnector();
                try {
                    PreparedStatement preparedStatement1 = databaseConnector1.connection.prepareStatement(SQLQuery1);
                    preparedStatement1.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    public static void main(String[] args) {
        new BorrowBook();
    }
}
