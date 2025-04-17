package org;

import java.util.HashMap;
import java.util.List;

public class Teacher extends User{
    HashMap<String, Course> courses;

    public void makeCourse(String courseName) {
        courses.put(courseName,  new Course(courseName));
    }

    public void addStudents( String courseName, List<Student> students){
        courses.get(courseName).addStudents(this,students);
    }
}
