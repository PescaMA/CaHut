package org.service;

import org.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AppInit {
    private final HashMap<String, UserDB> users = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    StudentDB studentManager;
    TeacherDB teacherManager;
    CourseDB courseManager;

    static AppInit getInstance() { return new AppInit();}
    private AppInit(){
        createManagers();
        loadUsers();
    }
    public HashMap<String, UserDB> getUsers() {
        return users;
    }
    public Scanner getScanner() {
        return scanner;
    }


    protected void createManagers(){
        studentManager = new StudentDB(true);
        teacherManager = new TeacherDB(true);
        courseManager = new CourseDB(true);
    }
    protected void loadUsers(){
        ArrayList<UserDB> allUsers = studentManager.loadAllUsers();
        allUsers.addAll(teacherManager.loadAllUsers());

        for(UserDB user : allUsers){
            users.put(user.getUsername(), user);
        }
    }
}
