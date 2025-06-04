package org.models;

import org.classes.CourseData;
import org.classes.TeacherData;
import org.database.DatabaseClass;
import org.service.AppInit;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class CourseDB extends CourseData implements DatabaseClass<CourseDB> {
    protected long pk = -1;
    protected long teacher_pk = -1;

    public CourseDB(boolean create){
        if(create) createTable();
    }
    public CourseDB(String name, TeacherData creator) {
        super(name, creator);
        teacher_pk = creator.pk();
    }
    public long getTeacher_pk() {
        return teacher_pk;
    }

    @Override
    public CourseDB makeNew(){return new CourseDB(false);}

    @Override
    public String tableName() {
        return "course";
    }

    @Override
    public ArrayList<Map.Entry<String, String>> tableColumns() {
        ArrayList<Map.Entry<String, String>> columns = new ArrayList<>();

        columns.add(new AbstractMap.SimpleEntry<>("name", "varchar(64)"));
        columns.add(new AbstractMap.SimpleEntry<>("teacher_pk", "int references usercahut(usercahut_id)"));

        return columns;
    }

    @Override
    public ArrayList<String> tableValues() {
        ArrayList<String> values = new ArrayList<>();

        values.add(String.valueOf(name));
        values.add(String.valueOf(teacher_pk));

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
        teacher_pk = Long.parseLong(values.get().get(1));
        System.out.println("teacher pk " + teacher_pk);

    }

    @Override
    public CourseDB save() {
        pk = insert();
        return this;
    }

}
