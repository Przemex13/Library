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

        int locationWindowWidth = (int) rozdzielnosc.getWidth() / 2 - windowWidth / 2;
        int locationWindowHeight = (int) rozdzielnosc.getHeight() / 2 - windowHigh / 2;

        this.setBounds(locationWindowWidth, locationWindowHeight, windowWidth, windowHigh);
        this.setVisible(true);

        ImageIcon ic = new ImageIcon (ClassLoader.getSystemResource("Images/wallpaper.png"));
        Image icToSize = ic.getImage().getScaledInstance((int)rozdzielnosc.getWidth(), (int)rozdzielnosc.getHeight(), Image.SCALE_FAST);
        ImageIcon ic2 = new ImageIcon(icToSize);
        JLabel backgroundLabel = new JLabel(ic2);
        this.add(backgroundLabel);


    }

    public static void main(String[] args) {
        new LibraryDesktop(null).setVisible(true);
    }
}
