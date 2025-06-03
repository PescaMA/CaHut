package org.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;


public final class Database <T>{

    private static  Database <?> instance = null;
    private  Connection connection = null;

    private Database(){}

    static  public <T> Database<T> getInstance() {
        if(instance == null)
            instance = new Database <T>();
        return (Database<T>) instance;
    }

    public Connection getConnection(){
        if (connection != null) return connection;
        try {
            final String jdbcUrl = "jdbc:postgresql://localhost/javacahut";
            final String username = "javacahut";
            final String password = "test";
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e){
            System.out.println("error loading database");
            System.exit(1);
        }
        return connection;
    }

    public void executeStatement(String sql){
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace(System.out);
        }
    }

    public void closeConnection(){
        try{
            getConnection().close();
        } catch(SQLException e){
            e.printStackTrace(System.out);
        }catch (NullPointerException e){
            return;
        }
    }
    public Optional<ArrayList<Long>> getPKs(String table){
        table = table.toLowerCase();
        String sql = String.format(
                "SELECT %s_id FROM %s",
                table,
                table
        );

        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);

            ResultSet results = stmt.executeQuery();
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
