package org.classes;

import java.util.HashMap;
import java.util.List;

public class Teacher extends User{
    HashMap<String, Course> courses;

    public Teacher(String username, String password){
        super(username,password);
    }

    public void makeCourse(String courseName) {
        courses.put(courseName,  new Course(courseName));
    }
    public void addStudents( String courseName, List<Student> students){
        courses.get(courseName).addStudents(this,students);
    }

    public void startQuiz(String courseName, Quiz quiz){
        courses.get(courseName).runQuiz(quiz);
    }
    public void endQuiz(String courseName, Quiz quiz){
        courses.get(courseName).endQuiz(quiz);
    }
}
