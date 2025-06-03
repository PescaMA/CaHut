package org.models;

import org.classes.StudentData;
import org.database.DatabaseClass;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentDB implements DatabaseClass<StudentDB> {
    protected StudentData s = new StudentData();
    protected long student_pk;
        public StudentDB(boolean create){
            if(create){
                createTable();
                s.createTable();
            }
        }

        public StudentDB(String username, String password,String name, String email) {
            s= new StudentData(username,password,name,email);
        }
    public StudentData getUser() {
        return s;
    }
    public ArrayList<UserDB> loadAllUsers(){
        return loadAll().stream().map(StudentDB::getUser)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String tableName() {
        return "student";
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

        values.add(String.valueOf(s.pk));

        return values;
    }

    @Override
    public StudentDB makeNew(){ return new StudentDB(false); }

    @Override
    public long pk() {
        return student_pk;
    }

    @Override
    public void load(long queryPk) {
        this.student_pk = queryPk;
        Optional<ArrayList<String>> values = queryByPk(queryPk);

        if (values.isEmpty() || values.get().isEmpty()) return;

        s.load(Long.parseLong(values.get().getFirst()));
    }

    @Override
    public StudentDB save() {
        s.save();
        student_pk = insert();
        return this;
    }

    }