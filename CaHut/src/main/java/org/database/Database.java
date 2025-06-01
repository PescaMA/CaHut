package org.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;


public final class Database {
    private final static String jdbcUrl = "jdbc:postgresql://localhost/javacahut";
    private final static String username = "javacahut";
    private final static String password = "test";

    private static Connection connection = null;

    private Database(){}

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
        }catch (NullPointerException e){
            return;
        }
    }
    public static Optional<ArrayList<Long>> getPKs(String table){
        table = table.toLowerCase();
        String sql = String.format(
                "SELECT %s_id FROM %s",
                table,
                table
        );

        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet results = stmt.executeQuery(); // result set is basically a cursor
            ArrayList<Long> values = new ArrayList<>();

            while (results.next()) {
                values.add(results.getLong(1));
            }
            stmt.close();
            return Optional.of(values);
        }
        catch(SQLException e){
            e.printStackTrace(System.out);
        }
        return Optional.empty();
    }
}
