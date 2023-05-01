import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class studentMenu {
    public studentMenu(){};
    studentRegistration stdreg = new studentRegistration();
    studentLogin stdlog = new studentLogin();

    public void loginstud() {
        studentRegistration stdreg = new studentRegistration();
        JFrame frame = new JFrame("Student Page"); // Create a new JFrame with the title "Login Page"
        frame.setSize(300, 200); // Set the size of the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the program

        // Create a JPanel to hold the components of the login page
        JPanel panel = new JPanel(new GridLayout(3, 1)); // Use a GridLayout with 3 rows and 2 columns

        // Create the components for the login page
        JLabel regLabel = new JLabel("Register or Login");
        JButton regButton = new JButton("Registration");
        JButton loginButton = new JButton("Login");
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stdreg.regPage();
                frame.dispose();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stdlog.logPage();
                frame.dispose();
            }
        });

        // Add the components to the panel
        panel.add(regLabel);
        panel.add(regButton);
        panel.add(loginButton);
        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }
}
