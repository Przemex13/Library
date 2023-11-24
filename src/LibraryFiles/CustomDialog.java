package LibraryFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomDialog extends JFrame {
    JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField authorTextField;
    private JTextField titleTextField;
    private JPanel panel;
    private JLabel authorLabel;
    private JLabel titleLabel;

    public CustomDialog() {}

    public CustomDialog(Frame frame) {
        JDialog dialog = new JDialog(frame);
        dialog.setTitle("Tytuł");
        dialog.setContentPane(contentPane);
        dialog.setVisible(true);
        dialog.setSize(400, 200);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.pack();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

//         call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

//         call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public String getAuthor() {
        return this.authorTextField.getText();
    }

    public String getTitleText() {
        return titleTextField.getText();
    }
}
