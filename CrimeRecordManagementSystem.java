import java.sql.*;
import java.util.*;

public class CrimeRecordManagementSystem {

    private static Connection connection;

    public static void main(String[] args) throws SQLException {

        // Create a connection to the database
        Scanner scanner=new Scanner(System.in);
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crime_records", "root", "");

        // Create a user object
        User user = new User();

        // Get the user input
        System.out.println("Enter your username: ");
        user.username = scanner.nextLine();

        System.out.println("Enter your password: ");
        user.password = scanner.nextLine();

        // Check if the user is an admin
        if (isAdmin(user)) {

            // Admins can view, edit, and delete crime records
            System.out.println("You are an admin.");
            System.out.println("You can view, edit, and delete crime records.");

        } else if (isRecordManager(user)) {

            // Record managers can only view and add new crime records
            System.out.println("You are a record manager.");
            System.out.println("You can view and add new crime records.");

        } else if (isConstable(user)) {

            // Constables can only view crime records
            System.out.println("You are a constable.");
            System.out.println("You can only view crime records.");

        } else {

            // User is not authorized
            System.out.println("You are not authorized to access this system.");

        }

        // Close the connection to the database
        connection.close();
    }

    private static boolean isAdmin(User user) throws SQLException {

        // Get the admin user from the database
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.username);
        preparedStatement.setString(2, user.password);
        ResultSet resultSet = preparedStatement.executeQuery();

        // If the user exists, they are an admin
        if (resultSet.next()) {
            return true;
        }

        return false;
    }

    private static boolean isRecordManager(User user) throws SQLException {

        // Get the record manager user from the database
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.username);
        preparedStatement.setString(2, user.password);
        ResultSet resultSet = preparedStatement.executeQuery();

        // If the user exists, they are a record manager
        if (resultSet.next()) {
            return true;
        }

        return false;
    }

    private static boolean isConstable(User user) throws SQLException {

        // Get the constable user from the database
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.username);
        preparedStatement.setString(2, user.password);
        ResultSet resultSet = preparedStatement.executeQuery();

        // If the user exists, they are a constable
        if (resultSet.next()) {
            return true;
        }

        return false;
    }
}

class User {

    public String username;
    public String password;
}
