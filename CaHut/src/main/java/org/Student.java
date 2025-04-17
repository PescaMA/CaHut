package org;

import java.util.HashMap;

@SuppressWarnings("unused")
public class Student extends User {
    final HashMap <String,Course> enrolledCourses = new HashMap<>();

    void addCourse(Course course){
        enrolledCourses.put(course.getName(), course);
    }


    //Map<Integer, Score> scores;

}
