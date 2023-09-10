package LibraryFiles;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StaffLibraryTable{
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel leftTopPanel;
    private JPanel leftBottomPanel;
    private JPanel rightPanel;
    private JTable staffTable;
    private JTextField idWorkerTextField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField addressTextField;
    private JTextField postcodeTextField;
    private JTextField cityTextField;
    private JButton saveButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;

    private void createUIComponents() {
//         TODO: place custom component creation code here
    }

    public StaffLibraryTable()  {
        tbload();
        this.createUIComponents();
    }
    public void tbload(){
        try {
            DatabaseConnector databaseConnector = new DatabaseConnector();
            Statement statement = databaseConnector.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from stafflist;");
            System.out.println("select * from stafflist;");
            staffTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Coś się tabela nie wczytała \n" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Library Staff");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(850,600);
        frame.setContentPane(new StaffLibraryTable().mainPanel);
        frame.setVisible(true);    }
}
