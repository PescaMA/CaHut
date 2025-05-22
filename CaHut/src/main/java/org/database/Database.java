package org.database;
import java.sql.*;


public final class Database {
    private final static String jdbcUrl = "jdbc:postgresql://localhost/javacahut";
    private final static String username = "javacahut";
    private final static String password = "test";

    private static Connection connection = null;

    public static Connection getConnection(){
        if (connection != null) return connection;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e){
            System.out.println("error loading database");
            System.exit(1);
        }
        return connection;
    }

    public static void closeConnection(){
        try{
            connection.close();
        } catch(SQLException e){
            e.printStackTrace(System.out);
        }
    }
}
