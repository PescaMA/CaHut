package org.models;

import org.classes.TeacherData;
import org.database.DatabaseClass;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeacherDB implements DatabaseClass<TeacherDB> {

    ///  note: the courses is saved as a foreign key in the course table.

    protected TeacherData t = new TeacherData();
    protected long teacher_pk;
    public TeacherDB(boolean create){
        if(create){
            createTable();
            t.createTable();
        }
    }
    public TeacherDB(String username, String password,String name, String email) {
        t = new TeacherData(username,password,name,email);
    }
    public TeacherData getUser() {
        return t;
    }
    public ArrayList<UserDB> loadAllUsers(){
        return loadAll().stream().map(TeacherDB::getUser)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String tableName() {
        return "teacher";
    }
    @Override
    public ArrayList<Map.Entry<String, String>> tableColumns() {
        ArrayList<Map.Entry<String, String>> columns = new ArrayList<>();

        columns.add(new AbstractMap.SimpleEntry<>("user_pk", "int references usercahut(usercahut_id)"));

        return columns;
    }
    @Override
    public ArrayList<String> tableValues() {
        ArrayList<String> values = new ArrayList<>();

        values.add(String.valueOf(t.pk));

        return values;
    }

    @Override
    public TeacherDB makeNew(){ return new TeacherDB(false); }

    @Override
    public long pk() {
        return teacher_pk;
    }

    @Override
    public void load(long queryPk) {
        this.teacher_pk = queryPk;
        Optional<ArrayList<String>> values = queryByPk(queryPk);

        if (values.isEmpty() || values.get().isEmpty()) return;

        t.load(Long.parseLong(values.get().getFirst()));
    }

    @Override
    public TeacherDB save() {
        t.save();
        teacher_pk = insert();
        return this;
    }

}