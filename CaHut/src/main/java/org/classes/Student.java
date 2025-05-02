package org.classes;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("unused")
public class Student extends User {

    public Student(String username, String password){
        super(username,password);
    }

    public void addCourse(Course course){
        courses.put(course.getName(), course);
    }
    public Set<String> getCourses(){
        return courses.keySet();
    }

}
