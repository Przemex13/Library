package LibraryFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LibraryDesktop extends JFrame implements ActionListener {
    String loggedUser;

    JMenu staffJMenu, readersJMenu, libraryJMenu, inventoryJMenu, bookDatabaseJMenu;
    JMenuItem editJMenuItem, addWorkerJMenuItem,
            borrowBookJMenuItem, giveBackBookJMenuItem,
            addReadersJMenuItem, showAndEditReadersJMenuItem,
            addBookJMenuItem, withdrawBookJMenuItem,
            bookDatabaseJMenuItem;


    public LibraryDesktop(String loggedUser) {
        super("Logged User: " + loggedUser);
        this.loggedUser = loggedUser;
        Dimension rozdzielnosc = Toolkit.getDefaultToolkit().getScreenSize();
        int widthScreen = (int) rozdzielnosc.getWidth();
        int highScreen = (int) rozdzielnosc.getHeight();
        int windowWidth = 1000;
        int windowHigh = 600;
        int locationWindowWidth = widthScreen / 2 - windowWidth / 2;
        int locationWindowHeight = highScreen / 2 - windowHigh / 2;
        this.setBounds(locationWindowWidth, locationWindowHeight, windowWidth, windowHigh);
        this.setVisible(true);
        ImageIcon ic = new ImageIcon (ClassLoader.getSystemResource("Images/wallpaper.png"));
        Image icToSize = ic.getImage().getScaledInstance(widthScreen, highScreen, Image.SCALE_FAST);
        ImageIcon ic2 = new ImageIcon(icToSize);
        JLabel backgroundLabel = new JLabel(ic2);
        this.add(backgroundLabel);

//        Menu and listeners

        staffJMenu = new JMenu("Staff");
        editJMenuItem = new JMenuItem("Staff edition");
        addWorkerJMenuItem = new JMenuItem("Add worker");
        staffJMenu.add(addWorkerJMenuItem);
        staffJMenu.add(editJMenuItem);
        addWorkerJMenuItem.addActionListener(this);
        editJMenuItem.addActionListener(this);
        libraryJMenu = new JMenu("Library");
        borrowBookJMenuItem = new JMenuItem("Borrow book");
        giveBackBookJMenuItem = new JMenuItem("Return book");
        readersJMenu = new JMenu("Readers");
        libraryJMenu.add(borrowBookJMenuItem);
        libraryJMenu.add(giveBackBookJMenuItem);
        borrowBookJMenuItem.addActionListener(this);
        giveBackBookJMenuItem.addActionListener(this);
        readersJMenu = new JMenu("Readers");
        addReadersJMenuItem = new JMenuItem("Add Readers");
        showAndEditReadersJMenuItem = new JMenuItem("Show and edit Readers");
        readersJMenu.add(addReadersJMenuItem);
        readersJMenu.add(showAndEditReadersJMenuItem);
        addReadersJMenuItem.addActionListener(this);
        showAndEditReadersJMenuItem.addActionListener(this);
        inventoryJMenu = new JMenu("Inventory");
        addBookJMenuItem = new JMenuItem("Add Book");
        withdrawBookJMenuItem = new JMenuItem("Withdraw Book");
        inventoryJMenu.add(addBookJMenuItem);
        inventoryJMenu.add(withdrawBookJMenuItem);
        addBookJMenuItem.addActionListener(this);
        withdrawBookJMenuItem.addActionListener(this);
        bookDatabaseJMenu = new JMenu("Book database");
        bookDatabaseJMenuItem = new JMenuItem("Book Database");
        bookDatabaseJMenu.add(bookDatabaseJMenuItem);
        bookDatabaseJMenu.addActionListener(this);
        bookDatabaseJMenuItem.addActionListener(this);
        JMenuBar menu = new JMenuBar();
        menu.add(staffJMenu);
        menu.add(readersJMenu);
        menu.add(libraryJMenu);
        menu.add(inventoryJMenu);
        menu.add(bookDatabaseJMenu);

//        menu.add(settingsJMenu);
        this.setJMenuBar(menu);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editJMenuItem) {
            try {
                new StaffLibraryTable();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource() == addWorkerJMenuItem) {
            new AddStaffWindow().setVisible(true);
        }
        else if (e.getSource() == addReadersJMenuItem){
            System.out.println("addReadersJMenuItem");
            new NewReader();
        }
        else if (e.getSource() == showAndEditReadersJMenuItem){
            System.out.println("showAndEditReadersJMenuItem");
            try {
                new  ReadersEdition();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource() == borrowBookJMenuItem) {
            new BorrowBook();
        }
        else if (e.getSource() == giveBackBookJMenuItem) {
            new ReturnBook();
        }
        else if (e.getSource() == addBookJMenuItem) {
            new NewCopy();
        }
        else if (e.getSource() == withdrawBookJMenuItem) {
            new WithdrawBook();
        }
        else if (e.getSource() == bookDatabaseJMenuItem) {
            new BookDatabase();

        }
            else {JOptionPane.showMessageDialog(null, "something went wrong, my friend :) ");}
        }
    public static void main(String[] args) {
        new LibraryDesktop(null).setVisible(true);
    }


}
