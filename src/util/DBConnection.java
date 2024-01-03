package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    private DBConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Get connection details from property file
                String connectionString = PropertyUtil.getPropertyString();
                
                // Establish the connection
                connection = DriverManager.getConnection(connectionString);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace(); // Handle the exception according to your needs
            }
        }
        return connection;
    }
}
