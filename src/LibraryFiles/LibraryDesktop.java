package LibraryFiles;

import javax.swing.*;
import java.awt.*;


public class LibraryDesktop extends JFrame {

    String loggedUser;

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
        Image icToSize = ic.getImage().getScaledInstance((int)rozdzielnosc.getWidth(), (int)rozdzielnosc.getHeight(), Image.SCALE_FAST);
        ImageIcon ic2 = new ImageIcon(icToSize);
        JLabel backgroundLabel = new JLabel(ic2);
        this.add(backgroundLabel);

//        Menu

        JMenu staffJMenu = createJMenu("Staff", "Staff edition, Add  staff, Logging and book borrowing");
        JMenu libraryJMenu = createJMenu("Library","Borrow book, return book");
        JMenu inventoryJMenu = createJMenu("Inventory","Add book to inventory, Remove book from inventory");
        JMenu bookDataBaseJMenu = createJMenu("Book Database","Book Database");
        JMenu settingsJMenu = createJMenu("Settings", "Your Profile, maximum borrowing, book overkeeping fee");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(staffJMenu);
        menuBar.add(libraryJMenu);
        menuBar.add(inventoryJMenu);
        menuBar.add(bookDataBaseJMenu);
        menuBar.add(settingsJMenu);
        this.setJMenuBar(menuBar);

    }

    public static JMenu createJMenu (String JMenuName, String menuIngredients){
        JMenu menu = new JMenu(JMenuName);
        String [] tablicaMenuItemow = menuIngredients.split(", ");
        for (String s : tablicaMenuItemow) {
            menu.add(s);
        }
        return menu;
    }

    public static void main(String[] args) {
        new LibraryDesktop(null).setVisible(true);
    }
}
