
package projectsem2;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FitnessServer {

    private final String url = "jdbc:mysql://localhost:3306/Asg";
    private final String user = "root";
    private final String password = "Angah.123";
    private Connection con;

    public static void main(String[] args) {
        new FitnessServer().start();
    }

    public void start() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);

            ServerSocket serverSocket = new ServerSocket(8000);
            while (true) {
                Socket socket = serverSocket.accept();

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                User user = (User) inputStream.readObject();

                //insert the user information into the database
                //insertUser(user);

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeUTF("Registration Successful!");

                inputStream.close();
                outputStream.close();
                socket.close();
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

   /**private void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO registration (name, email, age, userID, phone) values (?,?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setInt(3, user.getAge());
        statement.setString(4, user.getUserID());
        statement.setString(5, user.getPhone());
        statement.execute();
    } **/

}
