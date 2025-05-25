package org.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

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
    default StringBuffer getUpdateColumnsAsSQL(){
        StringBuffer columns = new StringBuffer();
        for (int i =0; i < tableColumns().size(); i++ ) {
            columns.append(",\n").append(tableColumns().get(i).getKey()).
                    append(" = ").append(tableValues().get(i));
        }
        columns.deleteCharAt(0); // delete fist comma
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
    default void update(){
        String sql = String.format(
                """
                        UPDATE %s SET
                        %s
                        WHERE %s_ID=%s
                        """,
                tableName(),
                getUpdateColumnsAsSQL(),
                tableName(),
                pk()
        );

        executeStatement(sql);
    }
    default void drop(){
        String sql = String.format(
                "DROP TABLE %s",
                tableName()
        );
        executeStatement(sql);
    }
    default Optional<ArrayList<String>> query() {
        return query("");
    }
    default Optional<ArrayList<String>> query(String where){
        String sql = String.format(
                """
                    SELECT * FROM %s
                    WHERE %s_ID=%s%s
                    """,
                tableName(),
                tableName(),
                pk(),
                where
        );

        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet results = stmt.executeQuery(); // result set is basically a cursor
            ArrayList<String> values = new ArrayList<>();

            if (results.next()) {
                for (Map.Entry<String, String> column : tableColumns()) {
                    values.add(results.getString(column.getKey()));
                }
            }
            stmt.close();
            return Optional.of(values);
        }
        catch(SQLException e){
            e.printStackTrace(System.out);
        }
        return Optional.empty();
    }
    void load(int pk);
}


