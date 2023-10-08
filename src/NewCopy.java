import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewCopy {
    private JPanel panel1;
    private JTextField authorTextField;
    private JTextField titleTextField;
    private JButton searchButton;
    private JTable bookTable;
    private JButton addBookButton;
    private JTextField idBookTextField;
    static int selectRow = Integer.parseInt(null);


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


    }

    public static void main(String[] args) {
        new NewCopy();
    }
}
