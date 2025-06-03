package org.models;

import org.classes.CourseData;
import org.database.DatabaseClass;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public abstract class CourseDB extends CourseData implements DatabaseClass<CourseDB> {
    protected long pk = -1;
    public CourseDB(boolean create){
        if(create) createTable();
    }
    public CourseDB(String name) {
        super(name);

        createTable();
    }

    @Override
    public String tableName() {
        return "course";
    }

    @Override
    public ArrayList<Map.Entry<String, String>> tableColumns() {
        ArrayList<Map.Entry<String, String>> columns = new ArrayList<>();

        columns.add(new AbstractMap.SimpleEntry<>("name", "varchar(64)"));

        return columns;
    }

    @Override
    public ArrayList<String> tableValues() {
        ArrayList<String> values = new ArrayList<>();

        values.add(String.valueOf(name));

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
        if (values.isEmpty()) return;

        name = values.get().getFirst();
    }

    @Override
    public CourseDB save() {
        pk = insert();
        return this;
    }

}
