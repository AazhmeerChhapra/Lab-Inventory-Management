import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class studentRegistration {
    public studentRegistration() {
    }

    ;
studentLogin stdlog = new studentLogin();

public void regPage(){

        Student stud = new Student();

        JFrame frame = new JFrame("Registration Page"); // Create a new JFrame with the title "Login Page"
        frame.setSize(300, 200); // Set the size of the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the program
        JLabel page = new JLabel("Registration Page");
        // Create a JPanel to hold the components of the login page
        JPanel panel = new JPanel(new GridLayout(5, 2)); // Use a GridLayout with 3 rows and 2 columns
        JLabel username = new JLabel("username(Registration id): ");
        JTextField usernameField = new JTextField();
        JLabel name = new JLabel("name: ");
        JTextField nameField = new JTextField();
        JLabel password = new JLabel("password: ");
        JPasswordField passwordField = new JPasswordField();
        JLabel phone = new JLabel("Phone: ");
        JTextField phoneNumber = new JTextField();
        JButton regBut = new JButton("Register");
        regBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String name = nameField.getText();
                String password = new String(passwordField.getPassword());
                String phone = phoneNumber.getText();
                stud.reg(username, name, password, phone);
                stdlog.logPage();
            }
        });

        panel.add(username);
        panel.add(usernameField);
        panel.add(name);
        panel.add(nameField);
        panel.add(password);
        panel.add(passwordField);
        panel.add(phone);
        panel.add(phoneNumber);
        panel.add(regBut);

        frame.add(panel);

        frame.setVisible(true);

    }
}
