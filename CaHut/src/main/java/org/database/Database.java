package org.database;
import java.sql.*;


public class Database {


        public static void start() {

            String jdbcUrl = "jdbc:postgresql://localhost:5432/javacahut";
            String username = "javacahut";
            String password = "test1";
            // Register the Postgres driver
            ///Class.forName("org.postgresql.Driver");

            // Connect to the database
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

                // Perform desired database operations

                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

                while (resultSet.next()) {
                    String columnValue = resultSet.getString("column_name");
                    System.out.println("Column Value: " + columnValue);
                }
            } catch (SQLException e){
                System.out.println("error loading database");
                System.exit(1);
            }

            // Close the connection
//            connection.close();
        }
}
