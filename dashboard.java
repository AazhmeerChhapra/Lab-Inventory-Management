import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class dashboard {

    public static void main(String[] args) {
        Student std = new Student();
        JButton addButton, editButton, deleteButton;
        JTextField searchField;
        JFrame f = new JFrame("Dashboard");
        f.setTitle("Dashboard");
        f.setSize(400, 200);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        searchField = new JTextField(20);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                std.addStudent("okas","bokas","bokies","334455");
            }
        });
//        editButton.addActionListener(this);
//        deleteButton.addActionListener(this);
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(searchField);
        f.add(panel);
        f.setVisible(true);


    }
}
