import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User{
private String phone;
public String studentId;
public String phoneNo;
public String itemsB;
int flag = 0;

public Student(String name, String userID, String password, String phone){
    super(name, userID, password);
    this.phone = phone;
}
public Student(){}

    public String getPhone() {
        return phone;
    }
public String hello(){
    return "hello";
}
public String addStudent(String username, String userid, String pass, String phone){
    try {
        Connection con = test.getConnection();
        String query = "Insert into Student values ('"+username+"','"+userid+"','"+pass+"','"+phone+"','compass');";
        PreparedStatement pp = con.prepareStatement(query);
        pp.execute();
    } catch (Exception e){
        e.printStackTrace();

    }
    return "Student added";
}
public void reg(String username, String name, String password, String phone){
    try {
        Connection con = test.getConnection();
        try {
            String reqQuery = "Insert into Student values ('"+username+"','"+name+"','"+password+"','"+phone+"','no item')";
            PreparedStatement pp = con.prepareStatement(reqQuery);
            pp.execute();

        } catch (Exception exception){
            exception.printStackTrace();
        }
    } catch (Exception exc){
        exc.printStackTrace();
    }
}
    public void login(String username, String password){
        try{
            Connection con=test.getConnection();

            try {
                String loginQuery="Select * from Student where student_name='"+username+"' AND pass='"+password+"';";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(loginQuery);
                if(rs.next()){
                    System.out.println("Login Successfull\n");
                    studentId=rs.getString("username");
                    phoneNo = rs.getString("phone");
                    itemsB = rs.getString("itemBorrowed");
                    System.out.println(studentId + "\t" + phoneNo + "\t" + itemsB);
                    flag = 1;

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
    static int quantityf;

    public void dashboard() {
        Scanner in = new Scanner(System.in);
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Item> cart = new ArrayList<>();
        JList itemList;
        JButton addButton;
        JButton removeButton;
        JLabel nameLabel;
        JLabel modelLabel;
        JLabel estimatedLabel;
        JTextArea cartArea;
        JFrame frame = new JFrame("Student dashboard"); // Create a new JFrame with the title "Login Page"
        frame.setSize(300, 200); // Set the size of the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the program
        frame.setLayout(new BorderLayout());
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(5, 1));
        nameLabel = new JLabel();
        modelLabel = new JLabel();
        estimatedLabel = new JLabel();
        JTextField quantity = new JTextField();
        detailsPanel.add(nameLabel);
        detailsPanel.add(modelLabel);
        detailsPanel.add(estimatedLabel);
        detailsPanel.add(new JLabel("Quantity:"));
        detailsPanel.add(quantity);

        DefaultListModel<Item> model = new DefaultListModel<>();
        try {
            Connection con = test.getConnection();
            String query = "Select * from Inventory";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            System.out.println("item_name\tmodel\tquantity\testimatedValue\tconsumable");
            while (rs.next()) {
                Item item = new Item(rs.getString("item_name"), rs.getString("model"), rs.getInt("quantity"),rs.getDouble("estimatedValue"),rs.getBoolean("consumable") );
                items.add(item);
                model.addElement(item);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        itemList = new JList(model);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Item item = (Item) itemList.getSelectedValue();
                if (item != null) {
                    nameLabel.setText(item.getName());
                    modelLabel.setText(item.getModel());
                    estimatedLabel.setText("$"+ item.getEstimatedValue());
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(itemList);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BorderLayout());
        cartArea = new JTextArea();
        JScrollPane cartScrollPane = new JScrollPane(cartArea);
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add to Cart");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantityf = Integer.parseInt(quantity.getText());
                Item item = (Item) itemList.getSelectedValue();
                if (item != null) {
                    cart.add(item);
                    cartArea.append(item.getName() + "\t$" + item.getEstimatedValue() + "\tquantity="+quantityf+"\n");
                    try {
                        Connection con = test.getConnection();
                        try {
                            String reqQuery = "Insert into borrow values ('"+studentId+"','"+item.getName()+"',"+quantityf+");";
                            PreparedStatement pp = con.prepareStatement(reqQuery);
                            pp.execute();

                        } catch (Exception exception){
                            exception.printStackTrace();
                        }

                    }
                    catch (Exception exc){
                        exc.printStackTrace();
                    }
                }
            }
        });
        removeButton = new JButton("Remove from Cart");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = cartArea.getSelectionStart();
                if (index >= 0) {
                    String cartText = cartArea.getText();
                    int start = cartText.lastIndexOf("\n", index) + 1;
                    int end = cartText.indexOf("\n", index);
                    String selectedItemText = cartText.substring(start, end);
                    String[] parts = selectedItemText.split("\t");
                    String itemName = parts[0];
                    double itemPrice = Double.parseDouble(parts[1].substring(1));
                    int itemQuantity = Integer.parseInt(parts[2].substring(parts[2].indexOf("=")+1));
                    Item selectedItem = null;
                    for (Item item : cart) {
                        if (item.getName().equals(itemName) && item.getEstimatedValue() == itemPrice) {
                            selectedItem = item;
                            break;
                        }
                    }
                    if (selectedItem != null) {
                        cart.remove(selectedItem);
                        cartArea.setText(cartText.substring(0, start));

                    }
                }
            }
        });
        JButton finishButton = new JButton("Finish");
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Item> selectedItems = new ArrayList<>();
                String item = "";
                for (int i = 0; i < itemList.getModel().getSize(); i++) {
                    item =  cartArea.getText();
                }
                for (Item it:selectedItems){
                    System.out.println(it);
                }

                String studentName = studentId;
                String phoneNumber = phoneNo;
                RequestForm requestForm = new RequestForm(item, studentName, phoneNumber);
                requestForm.generateRequestForm("request-form.txt");
                JOptionPane.showMessageDialog(null, "Request form saved to request-form.txt");
                frame.dispose();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(finishButton);
        frame.add(listPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(detailsPanel, BorderLayout.EAST);
        frame.add(cartPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

    }
}
