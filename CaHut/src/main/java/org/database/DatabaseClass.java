package org.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface DatabaseClass {
    String tableName();
    ArrayList<Map.Entry<String, String>> tableColumns(); //
    ArrayList<String> tableValues();
    int pk();

    default StringBuffer getColumnsAsSQL(){
        StringBuffer columns = new StringBuffer();
        for (Map.Entry<String, String> column : tableColumns()) {
            columns.append(",\n").append(column.getKey()).append(" ").append(column.getValue());
        }
        return columns;
    }

    default void executeStatement(String sql){
        Connection connection = Database.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace(System.out);
        }
    }

    default void createTable(){
        String sql = String.format(
                """
                        CREATE TABLE IF NOT EXISTS %s (
                            %s_ID INT PRIMARY KEY%s
                        )
                        """,
                tableName(),
                tableName(),
                getColumnsAsSQL()
        );

        executeStatement(sql);
    }
    default void insert(){
        String sql = String.format(
                """
                        INSERT INTO %s VALUES(%s%s)
                        """,
                tableName(),
                pk(),
                String.join(", ", tableValues()) //     ArrayList<String> tableValues();
        );
        executeStatement(sql);
    }
}

