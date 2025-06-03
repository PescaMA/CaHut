package org.models;

import org.classes.StudentData;
import org.database.DatabaseClass;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class CourseStudentDB implements DatabaseClass<CourseStudentDB> {
    protected long pk = -1;
    long student_pk = -1;
    long course_pk = -1;

    public CourseStudentDB(boolean create){
        if(create) createTable();
    }
    public CourseStudentDB(CourseDB course, StudentData student) {
        student_pk = student.pk();
        course_pk = course.pk();
    }

    public long getStudent_pk() {
        return student_pk;
    }

    public long getCourse_pk() {
        return course_pk;
    }

    @Override
    public CourseStudentDB makeNew(){return new CourseStudentDB(false);}

    @Override
    public String tableName() {
        return "course_student";
    }

    @Override
    public ArrayList<Map.Entry<String, String>> tableColumns() {
        ArrayList<Map.Entry<String, String>> columns = new ArrayList<>();

        columns.add(new AbstractMap.SimpleEntry<>("course_pk", "int references course(course_id)"));
        columns.add(new AbstractMap.SimpleEntry<>("student_pk", "int references usercahut(usercahut_id)"));

        return columns;
    }

    @Override
    public ArrayList<String> tableValues() {
        ArrayList<String> values = new ArrayList<>();

        values.add(String.valueOf(course_pk));
        values.add(String.valueOf(student_pk));

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

        course_pk = Long.parseLong(values.get().get(0));
        student_pk = Long.parseLong(values.get().get(1));

    }

    @Override
    public CourseStudentDB save() {
        pk = insert();
        return this;
    }

}
