import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        studentMenu stdmenu = new studentMenu();
        JFrame frame = new JFrame("Start page"); // Create a new JFrame with the title "Login Page"
        frame.setSize(300, 200); // Set the size of the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the program
        // Create a JPanel to hold the components of the login page
        JPanel panel = new JPanel(new GridLayout(3, 1)); // Use a GridLayout with 3 rows and 2 columns
        JLabel Option = new JLabel("What are you?");
        Option.setHorizontalAlignment(JLabel.CENTER);
        Option.setVerticalAlignment(JLabel.CENTER);
        JButton studentButton = new JButton("Student");
        JButton techButton = new JButton("Technician");
        techButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.loginTech();
                frame.dispose();
            }
        });
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stdmenu.loginstud();
                frame.dispose();

            }
        });
        panel.add(Option);
        panel.add(studentButton);
        panel.add(techButton);
        frame.add(panel);
        frame.setVisible(true);
    }


}
