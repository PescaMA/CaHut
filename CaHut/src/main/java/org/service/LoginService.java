package org.service;

import org.classes.*;
import org.models.StudentDB;
import org.models.TeacherDB;
import org.models.UserDB;

import java.util.ArrayList;

public class LoginService {
    protected static AppInit appInit = AppInit.getInstance();

    protected static void addDefault(){

        if(!appInit.getUsers().isEmpty())
            return;

        ArrayList<UserDB> allUsers = new ArrayList<>();

        StudentDB s = new StudentDB("s", "s", "Student", "Stud@info.ro");
        TeacherDB t = new TeacherDB("t", "t", "Prof", "Prof@info.ro");

        s.save();
        t.save();

        allUsers.add(s.getUser());
        allUsers.add(t.getUser());

        for(UserDB user : allUsers){
            appInit.addUser(user);
        }

//        t.makeCourse("course1");
//        t.addStudent("course1",a);
//        Question q = new Question("what contains c?",20, Arrays.asList("c1","c2"), Arrays.asList("g1","g2"));
//        Question q2 = new Question("what is 10 + 9?",8, Arrays.asList("21","19"), Arrays.asList("11","0"));
//
//        Quiz quiz = new Quiz(t, "quiz", Arrays.asList(q,q2));
//        t.addQuiz("course1", quiz);
    }
    protected static void addStudent(String username, String password, String name, String email){
        UserDB newUser = new StudentDB(username,password,name,email).save().getUser();
        appInit.getUsers().put(username,newUser);
    }
    protected static void addTeacher(String username, String password, String name, String email){
        UserDB newUser = new TeacherDB(username,password,name,email).save().getUser();
        appInit.getUsers().put(username,newUser);
    }
    public static void start(){
        addDefault();

        System.out.println("Welcome to CaHut!");
        signIn();
        appInit.getScanner().close();
    }
    protected static void logIn(){
        while(true){
            System.out.println("Username (0 to exit): ");

            String username = appInit.getScanner().next();

            if(username.trim().equals("0")) return;
            if(!appInit.getUsers().containsKey(username)){
                System.out.println("Invalid username! Try again: ");
                continue;
            }

            System.out.println("Password: ");
            String password = appInit.getScanner().next();
            if(!appInit.getUsers().get(username).canLogin(password)){
                System.out.println("Invalid password! Try again: ");
                continue;
            }
            System.out.println("Success!");
            UserService.run( appInit.getUsers().get(username) );
            break;
        }


    }
    protected static void signUp(){
        String username;
        while(true){
            System.out.println("Username (0 to exit):");
            username = appInit.getScanner().next();

            if(username.trim().equals("0")) return;

            if(appInit.getUsers().containsKey(username)){
                System.out.println("Username already exists! ");
                continue;
            }
            break;
        }

        String name;

        System.out.println("Name (0 to exit):");
        name = appInit.getScanner().next();

        if(name.trim().equals("0")) return;

        String email;
        while(true){
            System.out.println("Email (0 to exit):");
            email = appInit.getScanner().next();

            if(email.trim().equals("0")) return;

            if(!UserData.isEmailValid(email)){
                System.out.println("Invalid email! Should have a . after @");
                continue;
            }
            break;
        }


        System.out.println("Password: ");
        String password = appInit.getScanner().next();

        while(true){
            System.out.println("Teacher or student?");
            System.out.println("0 = Student");
            System.out.println("1 = Teacher");

            if (appInit.getScanner().hasNextInt()) {
                int number = appInit.getScanner().nextInt();
                if (number < 0 || number > 2) {
                    System.out.println("Invalid input. The number must be between 0 and 1.");
                    continue;
                }
                if(number == 0) addStudent(username,password, name, email);
                if(number == 1) addTeacher(username,password, name, email);
                break;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                appInit.getScanner().next();
            }
        }

        System.out.println("Successfully added!");
    }

    protected static void signIn(){
        System.out.println("Login or sign up?");

        while(true){
            System.out.println("Please enter a number between 0 and 2 ");
            System.out.println("0 = exit");
            System.out.println("1 = log in");
            System.out.println("2 = sign up");

            if (appInit.getScanner().hasNextInt()) {
                int number = appInit.getScanner().nextInt();
                if (number < 0 || number > 2) {
                    System.out.println("Invalid input. The number must be between 0 and 2.");
                    continue;
                }
                if(number == 0) break;
                if(number == 1) logIn();
                if(number == 2) signUp();
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                appInit.getScanner().next();
            }
        }

    }
}
