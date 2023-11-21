package LibraryFiles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewCopy implements ActionListener {
    private JPanel panel1;
    private JTextField authorTextField;
    private JTextField titleTextField;
    private JButton searchButton;
    private JTable bookTable;
    private JButton addBookButton;
    private JTextField idBookTextField;

    public NewCopy() {
        JFrame frame = new JFrame("New copy");
        frame.setContentPane(panel1);
        frame.setVisible(true);
        frame.setSize(450, 400);
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("search ...");
                TitlesAndAuthors titlesAndAuthors = new TitlesAndAuthors();
                System.out.println(titlesAndAuthors.selectedRow);
                String id;
            }

        });

        addBookButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               String newBookNumber = DatabaseConnector.CopyNumberGenerator(titleTextField.getText());
               String idBook = idBookTextField.getText();
               String enterData;

               Date nowDate = new Date();
               long stampTime = nowDate.getTime();
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
               enterData = dateFormat.format(nowDate);
               System.out.println(enterData);
               System.out.println(enterData);
               String SQLQuery =  String.format("insert into inventory(`id_copy`, `id_book`,`enter_data`,`withdraw_data`) VALUES ('%s', '%s','%s', NULL)", newBookNumber, idBook, enterData);
               DatabaseConnector databaseConnector = new DatabaseConnector(SQLQuery);
               JOptionPane.showMessageDialog(null, "dodano książkę o numerze: " + newBookNumber + "\n"+ "tytuł: " + titleTextField.getText());
               DatabaseConnector.fetchBookTable(idBookTextField.getText(),bookTable);
               String SQLQuery1 = String.format("CREATE TABLE `librarytest`.`%s` (`id_reader` VARCHAR(50) NOT NULL , `borrow_book_date` DATE NULL , `give_back_date` DATE NULL ) ENGINE = InnoDB;",newBookNumber);
               DatabaseConnector databaseConnector1 = new DatabaseConnector(SQLQuery1);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public class TitlesAndAuthors {

            private JPanel panel1;
            JTable authorsAndTitels;
            private JButton addNewButton;
            private JButton chooseButton;
            int selectedRow;

            public static boolean ifAuthorTitleDataIsCorrect(String author, String title) {
                boolean isCorrect = true;
                DatabaseConnector databaseConnector = new DatabaseConnector();
                return isCorrect;
            }

            public JTable getAuthorsAndTitels() {
                return authorsAndTitels;
            }

            public TitlesAndAuthors() {
                JFrame frame = new JFrame("Titles and Authors");
                frame.setContentPane(panel1);
                frame.setVisible(true);
                frame.setSize(450, 400);
                DatabaseConnector databaseConnector = new DatabaseConnector("select * from booktable;", authorsAndTitels);
                addNewButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        CustomDialog customDialog = new CustomDialog();

                        int result = JOptionPane.showConfirmDialog(null, customDialog.contentPane, "", JOptionPane.OK_CANCEL_OPTION);
                        System.out.println(result);
                        if (result == JOptionPane.OK_OPTION) {
                            String author = customDialog.getAuthor();
                            String title = customDialog.getTitleText();
                            String sqlQuery = String.format("insert into booktable(`author`, `title`) VALUES ('%s', '%s')", author, title);
                            System.out.println(author);
                            System.out.println(title);
                            DatabaseConnector databaseConnector1 = new DatabaseConnector(sqlQuery);
                            DatabaseConnector databaseConnector = new DatabaseConnector("select * from booktable;", authorsAndTitels);
                        }
                    }
                });
                authorsAndTitels.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        selectedRow = authorsAndTitels.getSelectedRow();
                        System.out.println(selectedRow);
                    }
                });
                chooseButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        idBookTextField.setText(String.valueOf(authorsAndTitels.getValueAt(selectedRow, 0)));
                        authorTextField.setText(String.valueOf(authorsAndTitels.getValueAt(selectedRow, 1)));
                        titleTextField.setText(String.valueOf(authorsAndTitels.getValueAt(selectedRow, 2)));
                        DatabaseConnector.fetchBookTable(idBookTextField.getText(),bookTable);


                        frame.dispose();
//                System.out.println(titlesAndAuthors.authorsAndTitels.getValueAt(titlesAndAuthors.authorsAndTitels.getSelectedRow(),1));
                    }
                });
            }
        }

        public static void main(String[] args) {
            new NewCopy();
        }
    }
