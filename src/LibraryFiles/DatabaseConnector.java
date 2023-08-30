package LibraryFiles;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

Connection connection;
Statement statement;

    public DatabaseConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", "root","");
            this.statement = connection.createStatement();
            System.out.println(this.statement);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Wystąpił błąd: \\n" + e.getClass() + "\\n" + e.getMessage());
        }
    }
}
