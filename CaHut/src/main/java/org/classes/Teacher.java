package org.classes;

import java.util.*;

public class Teacher extends User{
    public Teacher(String username, String password){
        super(username,password);
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

    public void startQuiz(String courseName, Quiz quiz){
        courses.get(courseName).runQuiz(quiz);
    }
    public void endQuiz(String courseName, Quiz quiz){
        courses.get(courseName).endQuiz(quiz);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "username='" + username + '\'' +
                '}';
    }
}
