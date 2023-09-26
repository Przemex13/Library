package LibraryFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LibraryDesktop extends JFrame implements ActionListener {
    String loggedUser;

    JMenu staffJMenu, libraryJMenu, inventoryJMenu, bookDatabaseJMenu, settingsJMenu;
    JMenuItem editJMenuItem, addWorkerJMenuItem,loggsAndBorrowingsJMenuItem,
            borrowBookJMenuItem, giveBackBookJMenuItem,
            addBookJMenuItem, withdrawBookJMenuItem,
            bookDatabaseJMenuItem,
            yourProfileJMenuItem, maxRentJMenuItem, feeForOverKeepingJMenuItem;


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
        loggsAndBorrowingsJMenuItem = new JMenuItem("Logins and Borrowings");
        staffJMenu.add(addWorkerJMenuItem);
        staffJMenu.add(editJMenuItem);
        staffJMenu.add(loggsAndBorrowingsJMenuItem);
        addWorkerJMenuItem.addActionListener(this);
        editJMenuItem.addActionListener(this);
        loggsAndBorrowingsJMenuItem.addActionListener(this);
        libraryJMenu = new JMenu("Library");
        borrowBookJMenuItem = new JMenuItem("Borrow book");
        giveBackBookJMenuItem = new JMenuItem("Return book");
        libraryJMenu.add(borrowBookJMenuItem);
        libraryJMenu.add(giveBackBookJMenuItem);
        borrowBookJMenuItem.addActionListener(this);
        giveBackBookJMenuItem.addActionListener(this);
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
        settingsJMenu = new JMenu("Settings");
        yourProfileJMenuItem = new JMenuItem("Your Profile");
        maxRentJMenuItem = new JMenuItem("Maximal renting time");
        feeForOverKeepingJMenuItem = new JMenuItem("Overkeeping fee");
        settingsJMenu.add(yourProfileJMenuItem);
        settingsJMenu.add(maxRentJMenuItem);
        settingsJMenu.add(feeForOverKeepingJMenuItem);
        yourProfileJMenuItem.addActionListener(this);
        maxRentJMenuItem.addActionListener(this);
        feeForOverKeepingJMenuItem.addActionListener(this);
        JMenuBar menu = new JMenuBar();
        menu.add(staffJMenu);
        menu.add(libraryJMenu);
        menu.add(inventoryJMenu);
        menu.add(bookDatabaseJMenu);
        menu.add(settingsJMenu);
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
        else if (e.getSource() == loggsAndBorrowingsJMenuItem) {}
        else if (e.getSource() == borrowBookJMenuItem) {}
        else if (e.getSource() == giveBackBookJMenuItem) {}
        else if (e.getSource() == addBookJMenuItem) {
            System.out.println("dodano książke");
        }
        else if (e.getSource() == withdrawBookJMenuItem) {}
        else if (e.getSource() == bookDatabaseJMenuItem) {}
        else if (e.getSource() == yourProfileJMenuItem) {}
        else if (e.getSource() == maxRentJMenuItem) {}
        else if (e.getSource() == feeForOverKeepingJMenuItem) {}
            else {JOptionPane.showMessageDialog(null, "coś poszło nie tak");}
        }
    public static void main(String[] args) {
        new LibraryDesktop(null).setVisible(true);
    }


}
