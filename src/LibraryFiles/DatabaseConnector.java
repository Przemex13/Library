package LibraryFiles;


import net.proteanit.sql.DbUtils;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            e.getMessage();
        }
    }
    public DatabaseConnector(String sqlQuery) {
        this();
        try {
            this.preparedStatement = connection.prepareStatement(sqlQuery);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            System.out.println(e.getCause());
            System.out.println(e.getLocalizedMessage());
        } finally {
            try {
                if (this.connection != null) {
                    this.connection.close();
                    this.statement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public DatabaseConnector(String sqlQuery, JTable table) {
        this();
        try{
            resultSet = statement.executeQuery(sqlQuery);
            tableModel = DbUtils.resultSetToTableModel(resultSet);
            table.setModel(tableModel);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    resultSet.close();
                    connection.close();
                    statement.close();
                }
            } catch (SQLException e) {
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
                JOptionPane.showMessageDialog(null, "invalid credentials");
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
                System.out.println(e.getMessage());
            }
        }
        return isValidPassword;
    }
    public static TableModel resultOfSelectRequest (String sqlQuery){
            TableModel tableModel1 = null;
        try {
            DatabaseConnector databaseConnector = new DatabaseConnector();
            ResultSet resultSet1 = databaseConnector.statement.executeQuery(sqlQuery);
            tableModel1 = DbUtils.resultSetToTableModel(resultSet1);
            resultSet1.close();
            databaseConnector.connection.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return tableModel1;
    }

    public static String CopyNumberGenerator(String book){
        String date;
        String CopyNumber = "";
        String sqlQuery;
        String idBook = "";
        try {
//            dodanie numeru książki
            sqlQuery = String.format("select * from booktable where title ='%s';",book);
            DatabaseConnector databaseConnector = new DatabaseConnector();
            databaseConnector.connection.createStatement();
            ResultSet resultSet = databaseConnector.statement.executeQuery(sqlQuery);
            if(resultSet.next()){
                idBook  = resultSet.getString("id_book");
                resultSet.close();
                databaseConnector.connection.close();
            }else{
                System.out.println("something went wrong");
            }
            CopyNumber += numerFiller(idBook,4);
            CopyNumber +="_";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Date nowDate = new Date();

//       adding data to book number
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        CopyNumber += dateFormat.format(nowDate);
        CopyNumber +="_";

//      adding control number to copy number
        SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy");
        String rok  = dateFormatYear.format(nowDate);
        String SQLQuery = String.format("SELECT * FROM inventory WHERE inventory.enter_data > '%s-01-01' AND inventory.enter_data < '%s-12-31' AND inventory.id_book = %s;", rok, rok, idBook);
        TableModel tableModel1 = resultOfSelectRequest(SQLQuery);
        System.out.println("book account in this year " + tableModel1.getRowCount());
        CopyNumber += tableModel1.getRowCount() + 1;
        return CopyNumber;
    }

    public static String numerFiller(String value, int iloscZnakow){
        String figure = "";
        int zerosAmount = iloscZnakow - value.length();
        for (int i = 0 ; i < zerosAmount; i ++){
            figure += "0";
        }
        figure += value;
        return figure;
    }
    public static void fetchBookTable(String id_book, JTable table) {
        String sqlQuery = String.format("SELECT inventory.id_copy, inventory.enter_data FROM inventory WHERE inventory.id_book = %s;",id_book);
        DatabaseConnector databaseConnector = new DatabaseConnector();

        ResultSet resultSet = null;
        TableModel tableModel;
        try{
            Statement statement = databaseConnector.connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            tableModel = DbUtils.resultSetToTableModel(resultSet);
            table.setModel(tableModel);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (databaseConnector != null) {
                try {
                    resultSet.close();
                    databaseConnector.connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
