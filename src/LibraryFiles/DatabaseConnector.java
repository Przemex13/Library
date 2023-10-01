package LibraryFiles;


import net.proteanit.sql.DbUtils;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.sql.*;
public class DatabaseConnector {
    String url = "jdbc:mysql://localhost:3306/librarytest";
    String uname = "root";
    String password = "";
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    TableModel tableModel;

    public DatabaseConnector(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, uname, password);
            this.statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error in DatabaseConnector()- first part");
            System.out.println(e.getMessage());
        }
    }
    public DatabaseConnector(String sqlQuery) {
        this();
        try {
            this.preparedStatement = connection.prepareStatement(sqlQuery);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error in DatabaseConnector(String sqlQuery)-first part");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        } finally {
            try {
                if (this.connection != null) {
                    this.connection.close();
                    this.statement.close();
                }
            } catch (SQLException e) {
                System.out.println("error in DatabaseConnector(String sqlQuery)- second part");
                System.out.println(e.getMessage());
            }
        }

    }
    public DatabaseConnector(String sqlQuery, TableModel tableModel) {
        this();
        try{
            resultSet = statement.executeQuery(sqlQuery);
            int i = 0;
            while(resultSet.next()) i++;
            System.out.println("DatabaseConnector(String sqlQuery, TableModel tableModel) Rwault Set rows : " + i);
            this.tableModel = DbUtils.resultSetToTableModel(resultSet);
        } catch (SQLException e) {
            System.out.println("error in DatabaseConnector(String sqlQuery, TableModel tableModel)- first part");
            System.out.println(e.getMessage());
        }
    }



    public DatabaseConnector(String sqlQuery, JTable table) {
        this();
        try{
            resultSet = statement.executeQuery(sqlQuery);
            tableModel = DbUtils.resultSetToTableModel(resultSet);
            table.setModel(tableModel);
        } catch (SQLException e) {
            System.out.println("error in DatabaseConnector(String sqlQuery, JTable table) - first part");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    resultSet.close();
                    connection.close();
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("error in DatabaseConnector(String sqlQuery, JTable table) - second part");
                System.out.println(e.getMessage());
            }
        }
    }
    static boolean loginChecker (String providedLoggin){
        boolean wynik = true;
        try {
            DatabaseConnector databaseConnector = new DatabaseConnector();
            String sqlQuery = String.format("select * from loggintable where login = '%s'", providedLoggin);
            databaseConnector.resultSet = databaseConnector.statement.executeQuery(sqlQuery);
            if(databaseConnector.resultSet.next()) wynik = false;
        } catch (SQLException e) {
            System.out.println("error in LoginChecker");
            System.out.println(e.getMessage());
        }
        return wynik;
    }
    static boolean logginService(String providedLoggin,String providedPassword){
        boolean isValidPassword = false;
        DatabaseConnector databaseConnector = new DatabaseConnector();
        try {
            String sqlQuery = String.format("select * from loggintable where login = '%s' AND password = '%s'", providedLoggin, providedPassword);
            databaseConnector.resultSet = databaseConnector.statement.executeQuery(sqlQuery);
            if(databaseConnector.resultSet.next()) {
                isValidPassword = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Zły login lub hasło");
                isValidPassword = false;
            }
        } catch (SQLException e) {
            System.out.println("error in LoginService");
            System.out.println(e.getMessage());
        }finally {
            try {
                if (databaseConnector.connection != null) {
                   databaseConnector.resultSet.close();
                    databaseConnector.connection.close();
                    databaseConnector.statement.close();
                }
            } catch (SQLException e) {
                System.out.println("error in DatabaseConnector(String sqlQuery, JTable table) - second part");
                System.out.println(e.getMessage());
            }
        }
        return isValidPassword;
    }


}
