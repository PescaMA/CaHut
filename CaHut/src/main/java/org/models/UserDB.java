package org.models;

import org.classes.UserData;
import org.database.DatabaseClass;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public abstract class UserDB extends UserData implements DatabaseClass<UserDB> {
    protected long pk = -1;
    public UserDB(){}
    public UserDB(String username, String password,String name, String email) {
        super(username,password,name,email);

        createTable();
        /// pk = insert();
    }

    @Override
    public String tableName() {
        return "userCahut";
    }

    @Override
    public ArrayList<Map.Entry<String, String>> tableColumns() {
        ArrayList<Map.Entry<String, String>> columns = new ArrayList<>();

        columns.add(new AbstractMap.SimpleEntry<>("name", "varchar(100)"));
        columns.add(new AbstractMap.SimpleEntry<>("email", "varchar(100)"));
        columns.add(new AbstractMap.SimpleEntry<>("password", "varchar(66)"));
        columns.add(new AbstractMap.SimpleEntry<>("username", "varchar(64)"));


        return columns;
    }

    @Override
    public ArrayList<String> tableValues() {
        ArrayList<String> values = new ArrayList<>();

        values.add(String.valueOf(name));
        values.add(String.valueOf(email));
        values.add(String.valueOf(passwordHash));
        values.add(String.valueOf(username));

        return values;
    }

    @Override
    public long pk() {
        return pk;
    }

    @Override
    public void load(long queryPk) {
        pk = queryPk;
        Optional<ArrayList<String>> values = queryByPk(queryPk);
        if (values.isEmpty() || values.get().isEmpty()) return;

        name = values.get().get(0);
        email = values.get().get(1);
        passwordHash = values.get().get(2);
        username = values.get().get(3);
    }

    @Override
    public UserDB save() {
        pk = insert();
        return this;
    }

}
