package projectsem2;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import javax.swing.*;
import java.sql.DriverManager;

public class FitnessRegistration extends JFrame {
    private final JLabel text;
    private final JLabel nameLabel;
    private JTextField nameField;
    private final JLabel emailLabel;
    private JTextField emailField;
    private final JLabel ageLabel;
    private JTextField ageField;
    private final JLabel userIDLabel;
    private JTextField userIDField;
    private final JLabel phoneLabel;
    private JTextField phoneField;
    private final JButton submitButton;
    private JButton clearButton;
    private JLabel logoLabel;
    private ObjectOutputStream oos;
    private final String url = "jdbc:mysql://localhost:3306/Asg";
    private final String user = "root";
    private final String password = "Angah.123";
    private Connection con ;
    
    public FitnessRegistration() {

        // Set up the frame
        setTitle("Fitness Center Registration System ");
        
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Create and add the labels
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\firda\\Downloads\\A1.jpg");
        logoLabel = new JLabel(logoIcon);
        add(logoLabel);
        logoLabel.setBounds(210, 0, 300, 150);
        
        text= new JLabel ("== REGISTRATION FORM ==");
        text.setBounds(300, 150, 200,40);
        add(text);
        
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(130, 200, 80, 25);
        add(nameLabel);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(130, 250, 80, 25);
        add(emailLabel);

        ageLabel = new JLabel("Age:");
        ageLabel.setBounds(130, 300, 80, 25);
        add(ageLabel);

        userIDLabel = new JLabel("User ID:");
        userIDLabel.setBounds(130, 350, 80, 25);
        add(userIDLabel);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(130, 400, 80, 25);
        add(phoneLabel);

        // Create and add the text fields
        
      
        nameField = new JTextField();
        nameField.setBounds(200, 200, 300, 25);
        add(nameField);

        emailField = new JTextField();
        emailField.setBounds(200, 250, 300, 25);
        add(emailField);

        ageField = new JTextField();
        ageField.setBounds(200, 300, 50, 25);
        add(ageField);

        userIDField = new JTextField();
        userIDField.setBounds(200, 350, 100, 25);
        add(userIDField);

        phoneField = new JTextField();
        phoneField.setBounds(200, 400, 100, 25);
        add(phoneField);
        
         // Create and add the submit button
        submitButton = new JButton("Submit");
        submitButton.setBounds(400, 450, 100, 25);
        add(submitButton);
        
          clearButton = new JButton("Clear");
          clearButton.setBounds(200, 450, 100, 25);
          add(clearButton);

        // Add an action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Connect to the server
                    Socket socket = new Socket("10.19.3.219", 8000);
                    oos = new ObjectOutputStream(socket.getOutputStream());

                    // Send the registration information to the server
                    User user = new User(nameField.getText(), emailField.getText(), 
                                         Integer.parseInt(ageField.getText()), 
                                         userIDField.getText(), phoneField.getText());
                    oos.writeObject(user);
                    oos.flush();
                    System.out.println("Sent registration information to the server.");
                         JOptionPane.showMessageDialog(null, "Sent registration information to the server.", "Success", JOptionPane.INFORMATION_MESSAGE);

                    //Inserting the registration data in the DB
                    String sql = "INSERT INTO registration (name, email, age, userID, phone) values (?,?,?,?,?)";
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, user.getName());
                    statement.setString(2, user.getEmail());
                    statement.setInt(3, user.getAge());
                    statement.setString(4, user.getUserID());
                    statement.setString(5, user.getPhone());
                    statement.execute();
                    JOptionPane.showMessageDialog(null, "Data has been successfully inserted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                   

                    // Close the connection
                    oos.close();
                    statement.close();
                    socket.close();

                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (SQLException ex) {
                }
            }
        });
         clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the text fields
                nameField.setText("");
                emailField.setText("");
                phoneField.setText("");
                ageField.setText("");
                userIDField.setText("");
            }
         });
    }

    public static void main(String[] args) {
        FitnessRegistration fr = new FitnessRegistration();
        fr.setVisible(true);
    }
}