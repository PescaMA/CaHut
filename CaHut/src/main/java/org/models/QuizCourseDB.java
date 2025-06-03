package org.models;

import org.classes.StudentData;
import org.database.DatabaseClass;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class QuizCourseDB implements DatabaseClass<QuizCourseDB> {
    protected long pk = -1;
    long quiz_pk = -1;
    long course_pk = -1;

    public QuizCourseDB(boolean create){
        if(create) createTable();
    }
    public QuizCourseDB(CourseDB course, QuizDB quiz) {
        quiz_pk = quiz.pk();
        course_pk = course.pk();
    }

    public long getQuiz_pk() {
        return quiz_pk;
    }

    public long getCourse_pk() {
        return course_pk;
    }

    @Override
    public QuizCourseDB makeNew(){return new QuizCourseDB(false);}

    @Override
    public String tableName() {
        return "quiz_course";
    }

    @Override
    public ArrayList<Map.Entry<String, String>> tableColumns() {
        ArrayList<Map.Entry<String, String>> columns = new ArrayList<>();

        columns.add(new AbstractMap.SimpleEntry<>("course_pk", "int references course(course_id)"));
        columns.add(new AbstractMap.SimpleEntry<>("quiz_pk", "int references quiz(quiz_id)"));

        return columns;
    }

    @Override
    public ArrayList<String> tableValues() {
        ArrayList<String> values = new ArrayList<>();

        values.add(String.valueOf(course_pk));
        values.add(String.valueOf(quiz_pk));

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
        quiz_pk = Long.parseLong(values.get().get(1));

    }

    @Override
    public QuizCourseDB save() {
        pk = insert();
        return this;
    }

}
