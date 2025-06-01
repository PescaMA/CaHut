package org.classes;

import org.models.UserDB;

import java.util.*;

public class Teacher extends UserDB {
    public Teacher(){}
    public Teacher(String username, String password,String name, String email){
        super(username,password, name , email);
    }
    public Set<String> getCoursesNames(){
        return courses.keySet();
    }

    public void makeCourse(String courseName) {
        courses.put(courseName,  new Course(courseName));
    }
    public boolean addQuiz(String courseName, Quiz quiz){
        if(!courses.containsKey(courseName))
            return false;

        courses.get(courseName).addQuiz(quiz);
        return true;
    }
    public boolean addStudent( String courseName, Student student){
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
    public Teacher makeNew(){ return new Teacher(); }
}
