package org.database;
import org.service.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public interface DatabaseClass <T extends DatabaseClass<T>> {
    String tableName();
    ArrayList<Map.Entry<String, String>> tableColumns(); //
    ArrayList<String> tableValues();
    long pk();
    void load(long pk);
    T save();
    T makeNew();

    default ArrayList<String> tableValuesInQuotes(){
        return (ArrayList<String>)(tableValues().stream()
                .map(value -> "'" + value.replace("'", "''") + "'") // Escape single quotes
                .collect(Collectors.toList()));
    }


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
                    append(" = '").append(tableValues().get(i)).append("'");
        }
        columns.deleteCharAt(0); // delete fist comma
        return columns;
    }



    default void createTable(){
        ///  note: serial is like auto_increment for postgres
        String sql = String.format(
                """
                        CREATE TABLE IF NOT EXISTS %s (
                            %s_id serial PRIMARY KEY  %s
                        )
                        """,
                tableName(),
                tableName(),
                getColumnsAsSQL()
        );

        AuditService.save("__1.try to create " + tableName() + "__");

        Database.getInstance().executeStatement(sql);;

    }
    default long insert(){
        String sql = String.format(
                "INSERT INTO %s VALUES(DEFAULT,%s)",
                tableName(),
                String.join(", ", tableValuesInQuotes()) //     ArrayList<String> tableValues();
        );

        AuditService.save("__2.insert into " + tableName() + "__");
        try {
            String[] key = {tableName().toLowerCase() + "_id"}; // name of pk
            PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(sql, key);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e){
            e.printStackTrace(System.out);
        }

        System.out.println(sql);


        return -1;
    }
    default void update(){
        String sql = String.format(
                """
                        UPDATE %s SET
                        %s
                        WHERE %s_id=%s
                        """,
                tableName(),
                getUpdateColumnsAsSQL(),
                tableName(),
                pk()
        );

        Database.getInstance().executeStatement(sql);

    }
    default void delete(){
        String sql = String.format(
                "DELETE FROM %s WHERE %s_id = %d",
                tableName(),
                tableName(),
                pk()
        );
        Database.getInstance().executeStatement(sql);;
    }
    default void drop(){
        String sql = String.format(
                "DROP TABLE %s",
                tableName()
        );
        Database.getInstance().executeStatement(sql);;

    }
    default Optional<ArrayList<String>> queryByPk(long pk) {
        return query(String.format(" %s_id=%d", tableName(), pk));
    }
    default Optional<ArrayList<String>> query(String where){
        String sql = String.format(
                """
                    SELECT * FROM %s
                    WHERE %s
                    """,
                tableName(),
                where
        );

        try {
            PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);

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

    default ArrayList<T> loadAll (){
        ArrayList<T> result = new ArrayList<>();
        Optional<ArrayList<Long>> optPks = Database.getInstance().getPKs(tableName()); // presumed primary keys
        if(optPks.isEmpty()) return result;
        ArrayList<Long> pks = optPks.get();

        for(Long id: pks){
            T newEl = makeNew();
            newEl.load(id);
            result.add(newEl);
        }
        return result;
    }

}