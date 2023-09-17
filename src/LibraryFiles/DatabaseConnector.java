package LibraryFiles;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.*;

public class DatabaseConnector {
    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;

    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarytest", "root","");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Wystąpił błąd: \\n" + e.getClass() + "\\n" + e.getMessage());
        }
    }
    public static void fetchTable(JTable table){
        try {
            connect();
            resultSet = connection.createStatement().executeQuery("select * from stafflist");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
