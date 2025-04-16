package org;

import java.util.ArrayList;
import java.util.TreeSet;

public class Course {


    private final TreeSet <Student> students = new TreeSet<>();
    private final ArrayList <Teacher> teachers = new ArrayList<>();


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
    public TreeSet<Student> getStudents() {
        return students;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

}
