import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class studentLogin {
    public studentLogin() {
    }

    ;

   public void logPage() {



            Student stud = new Student();
            JFrame frame = new JFrame("Student Login Page"); // Create a new JFrame with the title "Login Page"
            frame.setSize(300, 200); // Set the size of the JFrame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the program

            // Create a JPanel to hold the components of the login page
            JPanel panel = new JPanel(new GridLayout(5, 2)); // Use a GridLayout with 3 rows and 2 columns

            // Create the components for the login page
            JLabel usernameLabel = new JLabel("Username(user id):");
            JTextField usernameField = new JTextField();
            JLabel passwordLabel = new JLabel("Password:");
            JPasswordField passwordField = new JPasswordField();
            JButton loginButton = new JButton("Login");

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    stud.login(username, password);
                    usernameField.setText("");
                    passwordField.setText("");
                    if (stud.flag == 1){
                        stud.dashboard();
                    }

                }
            });

            // Add the components to the panel
            panel.add(usernameLabel);
            panel.add(usernameField);
            panel.add(passwordLabel);
            panel.add(passwordField);
            panel.add(loginButton);

            // Add the panel to the frame
            frame.add(panel);

            // Make the frame visible
            frame.setVisible(true);
        }
    }

