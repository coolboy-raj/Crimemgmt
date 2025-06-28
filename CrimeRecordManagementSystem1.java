import java.sql.*;

public class CrimeRecordManagementSystem1 {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/crime_records";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void main(String[] args) throws SQLException {

        // Connect to the database
        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        statement = connection.createStatement();

        // Create the admin user
        createUser("admin", "password", "Director");

        // Create the record manager user
        createUser("record_manager", "password", "Record Manager");

        // Create the constable user
        createUser("constable", "password", "Constable");

        // Login as the admin user
        int adminId = login("admin", "password");

        // Add a new crime record
        addCrimeRecord(adminId, "Robbery", "123 Main Street", "John Doe");

        // View the crime records
        listCrimeRecords();

        // Close the connection
        connection.close();
    }

    private static void createUser(String username, String password, String role) throws SQLException {

        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, role);
        preparedStatement.executeUpdate();
    }

    private static int login(String username, String password) throws SQLException {

        String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("id");
        } else {
            return -1;
        }
    }

    private static void addCrimeRecord(int userId, String crimeType, String location, String suspect) throws SQLException {

        String sql = "INSERT INTO crime_records (user_id, crime_type, location, suspect) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        preparedStatement.setString(2, crimeType);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, suspect);
        preparedStatement.executeUpdate();
    }

    private static void listCrimeRecords() throws SQLException {

        String sql = "SELECT * FROM crime_records";
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            System.out.println("Crime ID: " + resultSet.getInt("id"));
            System.out.println("Crime Type: " + resultSet.getString("crime_type"));
            System.out.println("Location: " + resultSet.getString("location"));
            System.out.println("Suspect: " + resultSet.getString("suspect"));
            System.out.println();
        }
    }
}
