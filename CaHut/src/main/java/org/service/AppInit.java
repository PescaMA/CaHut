package org.service;

import org.classes.StudentData;
import org.classes.TeacherData;
import org.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AppInit {
    private final HashMap<String, UserDB> users = new HashMap<>();
    private final HashMap<Long, UserDB> usersByPk = new HashMap<>();
    private final HashMap<String, CourseDB> courses = new HashMap<>();
    private final HashMap<Long, CourseDB> coursesByPk = new HashMap<>();

    private final Scanner scanner = new Scanner(System.in);

    StudentDB studentManager;
    TeacherDB teacherManager;
    CourseDB courseManager;
    CourseStudentDB courseStudentManager;

    static public AppInit getInstance() { return new AppInit();}
    private AppInit(){
        createManagers();
        loadUsers();
        loadCourses();
    }
    public HashMap<String, UserDB> getUsers() {
        return users;
    }
    public Scanner getScanner() {
        return scanner;
    }
    public void addUser(UserDB user){
        users.put(user.getUsername(), user);
        usersByPk.put(user.pk(), user);
    }
    public void addCourse(CourseDB course){
        coursesByPk.put(course.pk(), course);
        courses.put(course.getName(), course);
    }

    public void linkStudent(String courseName, String student){
        new CourseStudentDB(courses.get(courseName), (StudentData) users.get(student) ).save();
    }


    protected void createManagers(){
        studentManager = new StudentDB(true);
        teacherManager = new TeacherDB(true);
        courseManager = new CourseDB(true);
        courseStudentManager = new CourseStudentDB(true);
    }
    protected void loadUsers(){
        ArrayList<UserDB> allUsers = studentManager.loadAllUsers();
        allUsers.addAll(teacherManager.loadAllUsers());

        for(UserDB user : allUsers){
            addUser(user);
        }
    }
    protected void loadCourses(){

        ArrayList<CourseDB> allCourses = courseManager.loadAll();
        for(CourseDB course:allCourses){
            addCourse(course);
            TeacherData t = (TeacherData) usersByPk.get(course.getTeacher_pk());
            t.addCourse(course);

        }

        ArrayList<CourseStudentDB> allCourseStudentRelations = courseStudentManager.loadAll();
        for(CourseStudentDB courseStudent:allCourseStudentRelations){
            StudentData s = (StudentData) usersByPk.get(courseStudent.getStudent_pk());
            CourseDB c = coursesByPk.get(courseStudent.getCourse_pk());
            TeacherData t = (TeacherData) usersByPk.get(c.getTeacher_pk());
            t.addStudent(c.getName(), s);
        }
    }
}
