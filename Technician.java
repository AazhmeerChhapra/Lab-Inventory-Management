import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.List;

public class Technician extends User{
    public Technician(){};

    Scanner in = new Scanner(System.in);
    String technician_id = "";
    int flag = 0;
    public void login(String userid, String password){
        try{
            Connection con=test.getConnection();

            try {
                String loginQuery="Select * from Technician where userid='"+userid+"' AND pass='"+password+"';";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(loginQuery);
                if(rs.next()){
                    System.out.println("Login Successfull\n");
                    technician_id=rs.getString("userid");
                    System.out.println(technician_id);
                    flag=1;
                    dashboard();

                }else{
                    System.out.println("login Failed");
                }

            } catch (Exception e) {
                System.out.println("No account found!! CHECK username or password\n");
                throw new RuntimeException(e);
            }

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static String status = "";

    public void dashboard(){
        JButton addButton;
        JButton deleteButton;
        JButton editButton;
        JButton requestsButton;
        List<String> items = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        JFrame frame = new JFrame("Technician dashboard");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        JPanel searchPanel = new JPanel(new FlowLayout());
        JTextField searchField = new JTextField(10);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = test.getConnection();
                    String search = searchField.getText();
                    String searchQuery = "Select * from Student where username like '"+search+"';";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(searchQuery);
                    if (rs.next()){
                        String id = rs.getString("student_name");
                        String name = rs.getString("username");
                        String phone = rs.getString("phone");
                        String itemBorrowed = rs.getString("itemBorrowed");
                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        editButton = new JButton("Edit");
        requestsButton = new JButton("See Requests");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame frame = new JFrame("Add Inventory");
                frame.setSize(300, 200); // Set the size of the JFrame
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the program

                // Create a JPanel to hold the components of the login page
                JPanel panel = new JPanel(new GridLayout(6, 2)); // Use a GridLayout with 6 rows and 2 columns
                JTextField itemNameField, modelField, quantityField, estimatedValueField;
                JCheckBox consumableCheckBox;
                JLabel itemNameLabel = new JLabel("Item Name:");
                itemNameField = new JTextField(20);

                JLabel modelLabel = new JLabel("Model:");
                modelField = new JTextField(20);

                JLabel quantityLabel = new JLabel("Quantity:");
                quantityField = new JTextField(10);

                JLabel estimatedValueLabel = new JLabel("Estimated Value:");
                estimatedValueField = new JTextField(10);

                JLabel consumableLabel = new JLabel("Consumable:");
                consumableCheckBox = new JCheckBox();

                // Create submit button
                JButton submitButton = new JButton("Submit");
                panel.add(itemNameLabel);
                panel.add(itemNameField);
                panel.add(modelLabel);
                panel.add(modelField);
                panel.add(quantityLabel);
                panel.add(quantityField);
                panel.add(estimatedValueLabel);
                panel.add(estimatedValueField);
                panel.add(consumableLabel);
                panel.add(consumableCheckBox);
                panel.add(submitButton);
                frame.add(panel);
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String itemName = itemNameField.getText();
                        String model = modelField.getText();
                        int quantity = Integer.parseInt(quantityField.getText());
                        double estimate = Double.parseDouble(estimatedValueField.getText());
                        boolean cons = consumableCheckBox.isSelected();
                        try {
                            Connection con = test.getConnection();
                            String query = "Insert into Inventory values ('" + itemName + "','" + model + "'," + quantity + ","+estimate+","+cons+");";
                            PreparedStatement pp = con.prepareStatement(query);
                            pp.execute();
                        } catch (Exception exception){
                            exception.printStackTrace();
                        }
                        itemNameField.setText("");
                        modelField.setText("");
                        quantityField.setText("");
                        estimatedValueField.setText("");
                        consumableCheckBox.setAction(null);
                    }
                });
frame.setVisible(true);


            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame frame = new JFrame("Delete Inventory");
                frame.setSize(300, 200); // Set the size of the JFrame
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the program

                JPanel panel = new JPanel(new GridLayout(2, 2));
                JLabel delete = new JLabel("Item name");
                JTextField deletefield = new JTextField("");
                JButton deleteBut = new JButton("Delete");
                deleteBut.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String itemName = deletefield.getText();
                        try {
                            Connection con = test.getConnection();
                            String query = "delete from Inventory where item_name = '"+itemName+"';";
                            PreparedStatement pp = con.prepareStatement(query);
                            pp.execute();
                            con.close();
                        }
                        catch (Exception exc){
                            exc.printStackTrace();
                        }
                        deletefield.setText("");
                    }
                });
                panel.add(delete);
                panel.add(deletefield);
                panel.add(deleteBut);
                frame.add(panel);
                frame.setVisible(true);

            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame frame = new JFrame("Add Inventory");
                frame.setSize(300, 200); // Set the size of the JFrame
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the program

                JPanel panel = new JPanel(new GridLayout(3, 2));
                JLabel editItem = new JLabel("Item name");
                JTextField editItemField = new JTextField();
                JLabel quantityLabel = new JLabel("Quantity:");
                JTextField quantityField = new JTextField(10);
                JButton editBut = new JButton("Update");
                editBut.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String itemName = editItemField.getText();
                            int quantity = Integer.parseInt(quantityField.getText());
                            Connection con = test.getConnection();
                            String query = "update Inventory set quantity = "+quantity+" where item_name like '"+itemName+"';";
                            PreparedStatement pp=con.prepareStatement(query);
                            pp.execute();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                panel.add(editItem);
                panel.add(editItemField);
                panel.add(quantityLabel);
                panel.add(quantityField);
                panel.add(editBut);
                frame.add(panel);
                frame.setVisible(true);
            }
        });

        requestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                StringBuilder sb = new StringBuilder();
                JFrame frame2 = new JFrame("Technician View");
                JTextArea requestFormArea = new JTextArea(30, 60);
                requestFormArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(requestFormArea);
                frame2.add(scrollPane);

                frame2.pack();
                frame2.setVisible(true);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                try (BufferedReader br = new BufferedReader(new FileReader("request-form.txt"))) {

                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    requestFormArea.setText(sb.toString());
                } catch (IOException ex) {
                    System.err.println("Error reading request form file: " + ex.getMessage());
                }
                String[] lines = sb.toString().split("\n");
                for (String line : lines) {
                    int index = line.indexOf("\t");
                    if (index >= 0) {
                        String itemName = line.substring(0, index).trim();
                        System.out.println(itemName);
                        items.add(itemName);
                    }

                }
                try {
                    Connection conn = test.getConnection();
                    Statement stmt = conn.createStatement();
                    for (String item : items) {
//                            System.out.println(item);
                            String sql = "SELECT * FROM borrow WHERE item_name = '" + item + "'";
                            ResultSet rs = stmt.executeQuery(sql);
                            int requestedQuantity = 0;
                            if (rs.next()) {
                                requestedQuantity = rs.getInt("quantity");
//                                System.out.println(requestedQuantity);
                            } else {
                                status ="not granted";
                            }
                            String query = "SELECT * FROM Inventory WHERE item_name = '" + item + "'";
                            ResultSet rs2 = stmt.executeQuery(query);
                            int availableQuantity = 0;
                            if (rs2.next()){
                                availableQuantity = rs2.getInt("quantity");
//                                System.out.println(availableQuantity);
                                if (availableQuantity > requestedQuantity){
                                    status = "granted";
                                    availableQuantity = availableQuantity - requestedQuantity;
                                }
                                else{
                                    status = "not granted";
                                }
                            }
                            try {
                                String Update = "Update Inventory set quantity = "+availableQuantity+" where item_name = '"+item+"';" ;
                                PreparedStatement ps = conn.prepareStatement(Update);
                                ps.executeUpdate();
                            } catch (Exception exception){
                                exception.printStackTrace();
                            }


                    }
                    System.out.println(status);
                    conn.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        String selectStatement = "SELECT * FROM Inventory";

        // Execute the SELECT statement and retrieve the results
        ResultSet resultSet = null;
        try {
            Connection con = test.getConnection();
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(selectStatement);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Format the results as a string
        StringBuilder sb = new StringBuilder();
        try {
            while (resultSet.next()) {
                String itemName = resultSet.getString("item_name");
                String model = resultSet.getString("model");
                int quantity = resultSet.getInt("quantity");
                double estimatedValue = resultSet.getDouble("estimatedValue");
                boolean consumable = resultSet.getBoolean("consumable");

                sb.append("Item name: ").append(itemName).append("\t");
                sb.append("Model: ").append(model).append("\t");
                sb.append("Quantity: ").append(quantity).append("\t");
                sb.append("Estimated value: $").append(estimatedValue).append("\t");
                sb.append("Consumable: ").append(consumable).append("\t\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Set the formatted results as the text of the JTextArea
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);

        String studentQuery = "Select * from borrow;";
        ResultSet resultSet2 = null;
        try {
            Connection con = test.getConnection();
            Statement statement = con.createStatement();
            resultSet2 = statement.executeQuery(studentQuery);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Format the results as a string
        StringBuilder sb2 = new StringBuilder();
        try {
            while (resultSet2.next()) {
                String studName = resultSet2.getString("student_name");
                String itemName = resultSet2.getString("item_name");
                int quantity = resultSet2.getInt("quantity");
                sb2.append("Student name: ").append(studName).append("\t");
                sb2.append("Item name: ").append(itemName).append("\t");
                sb2.append("Quantity: ").append(quantity).append("\t\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Set the formatted results as the text of the JTextArea
        JTextArea textArea2 = new JTextArea(sb2.toString());
        JScrollPane scrollPane2 = new JScrollPane(textArea2);

        panel.add(searchPanel);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(editButton);
        panel.add(requestsButton);
        panel.add(scrollPane);
        panel.add(scrollPane2);
        frame.add(panel);

        frame.setVisible(true);
    }
}
