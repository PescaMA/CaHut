package org.classes;

import java.util.HashMap;

@SuppressWarnings("unused")
public class Student extends User {
    final HashMap <String,Course> enrolledCourses = new HashMap<>();

    public Student(String username, String password){
        super(username,password);
    }

    void addCourse(Course course){
        enrolledCourses.put(course.getName(), course);
    }


    //Map<Integer, Score> scores;

}
