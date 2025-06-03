package org.classes;

import org.models.UserDB;

import java.util.*;

public class TeacherData extends UserDB {
    public TeacherData(){}
    public TeacherData(String username, String password, String name, String email){
        super(username,password, name , email);
    }
    public Set<String> getCoursesNames(){
        return courses.keySet();
    }

    public void makeCourse(String courseName) {
        courses.put(courseName,  new CourseData(courseName));
    }
    public boolean addQuiz(String courseName, QuizData quiz){
        if(!courses.containsKey(courseName))
            return false;

        courses.get(courseName).addQuiz(quiz);
        return true;
    }
    public boolean addStudent( String courseName, StudentData student){
        if(!courses.containsKey(courseName))
            return false;

        courses.get(courseName).addStudent(this, student);
        return true;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "username='" + username + '\'' +
                '}';
    }


    @Override
    public TeacherData makeNew(){ return new TeacherData(); }
}
