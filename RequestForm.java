import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RequestForm {

    private String items;
    private Date currentDate;
    private String studentName;
    private String phoneNumber;

    public RequestForm(String items, String studentName, String phoneNumber) {
        this.items = items;
        this.currentDate = new Date();
        this.studentName = studentName;
        this.phoneNumber = phoneNumber;
    }

    public void generateRequestForm(String filename) {
        StringBuilder sb = new StringBuilder();
        sb.append("Request Form\n");
        sb.append("-------------\n");
        sb.append("Date: ").append(currentDate).append("\n");
        sb.append("Student Name: ").append(studentName).append("\n");
        sb.append("Phone Number: ").append(phoneNumber).append("\n");
        sb.append("\nList of Borrowed Items:\n");
        sb.append("-----------------------\n");
            sb.append(items.toString()).append("\n");
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            out.println(sb.toString());
            System.out.println("Request form saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving request form to file: " + e.getMessage());
        }
    }
}
