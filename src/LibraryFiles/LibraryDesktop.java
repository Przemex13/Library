package LibraryFiles;

import javax.swing.*;


public class LibraryDesktop extends JFrame {

    String loggedUser;

    public LibraryDesktop(String loggedUser) {
        super("Logged User: " + loggedUser);
        this.loggedUser = loggedUser;

        this.setBounds(300, 300, 300, 300);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LibraryDesktop(null).setVisible(true);
    }
}
