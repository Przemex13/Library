package LibraryFiles;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReturnBook {

    private JTextField readerTextField;
    private JPanel borrowBookPanel;
    private JTextField bookCopyNumberTextField;
    private JButton returnBookButton;
    private JLabel readerLabel;
    private JButton cancelButton;
    private JPanel returnPanel;


    public ReturnBook() {
        JFrame frame = new JFrame("return book");
        frame.setContentPane(returnPanel);
        frame.setSize(450, 200);
        frame.setVisible(true);
        returnBookButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String book = bookCopyNumberTextField.getText();
                String reader = readerTextField.getText();


                LocalDate date = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String data = date.format(formatter);
                System.out.println(data);

                String SQLQuery = String.format("UPDATE inventory SET inventory.reader = NULL, inventory.rent_date = NULL WHERE inventory.id_copy = '%s';",book);
                DatabaseConnector databaseConnector = new DatabaseConnector();
                try {
                    PreparedStatement preparedStatement = databaseConnector.connection.prepareStatement(SQLQuery);
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String SQLQuery1 = String.format("UPDATE librarytest.%s SET %s.give_back_date = '%s' WHERE %s.id_reader = '%s';",book, book, data, book, reader );
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
        new ReturnBook();
    }

}
