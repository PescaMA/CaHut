package org;

import java.util.ArrayList;
import java.util.TreeSet;

public class Course {
    private TreeSet <Student> students = new TreeSet<>();
    private ArrayList <Teacher> teachers = new ArrayList<>();


    Course(Teacher teacher){
        assert teacher != null;
        teachers.add(teacher);
    }
    public void addStudent(Student student){
        students.add(student);
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

}
