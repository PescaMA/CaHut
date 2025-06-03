package org.service;

import org.classes.*;
import org.models.StudentDB;
import org.models.UserDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LoginService {
    protected static HashMap<String, User> users = new HashMap<>();

    protected static void addDefault(){

        StudentDB userManager = new StudentDB();
        ArrayList<UserDB> allUsers = userManager.loadAllUsers();
        System.out.println(allUsers);

        if(allUsers.isEmpty()) {
            StudentDB s = new StudentDB("s", "s", "Student", "Stud@info.ro");
            Teacher t = new Teacher("t", "t", "Prof", "Prof@info.ro");

            s.save();

            allUsers.add(s.getUser());
            allUsers.add(t);
        }

        for(UserDB user : allUsers){
            users.put(user.getUsername(), user);
        }
//
//        t.makeCourse("course1");
//        t.addStudent("course1",a);
//        Question q = new Question("what contains c?",20, Arrays.asList("c1","c2"), Arrays.asList("g1","g2"));
//        Question q2 = new Question("what is 10 + 9?",8, Arrays.asList("21","19"), Arrays.asList("11","0"));
//
//        Quiz quiz = new Quiz(t, "quiz", Arrays.asList(q,q2));
//        t.addQuiz("course1", quiz);
    }

    protected static void loadUsers(){
        addDefault();

        ///  TO DO when database implemented.
    }
    protected static void addStudent(String username, String password, String name, String email){
        User newUser = new Student(username,password,name,email);
        users.put(username,newUser);
        ///  TO DO add to database
    }
    protected static void addTeacher(String username, String password, String name, String email){
        User newUser = new Teacher(username,password,name,email);
        users.put(username,newUser);
        ///  TO DO add to database
    }
    public static void start(){
        loadUsers();

        System.out.println("Welcome to CaHut!");
        final Scanner scanner = new Scanner(System.in);
        signIn(scanner);
        scanner.close();
    }
    protected static void logIn(Scanner scanner){
        while(true){
            System.out.println("Username (0 to exit): ");

            String username = scanner.next();

            if(username.trim().equals("0")) return;
            if(!users.containsKey(username)){
                System.out.println("Invalid username! Try again: ");
                continue;
            }

            System.out.println("Password: ");
            String password = scanner.next();
            if(!users.get(username).canLogin(password)){
                System.out.println("Invalid password! Try again: ");
                continue;
            }
            System.out.println("Success!");
            UserService.run( users.get(username), scanner );
            break;
        }


    }
    protected static void signUp(Scanner scanner){
        String username;
        while(true){
            System.out.println("Username (0 to exit):");
            username = scanner.next();

            if(username.trim().equals("0")) return;

            if(users.containsKey(username)){
                System.out.println("Username already exists! ");
                continue;
            }
            break;
        }

        String name;

        System.out.println("Name (0 to exit):");
        name = scanner.next();

        if(name.trim().equals("0")) return;

        String email;
        while(true){
            System.out.println("Email (0 to exit):");
            email = scanner.next();

            if(email.trim().equals("0")) return;

            if(!User.isEmailValid(email)){
                System.out.println("Invalid email! Should have a . after @");
                continue;
            }
            break;
        }


        System.out.println("Password: ");
        String password = scanner.next();

        while(true){
            System.out.println("Teacher or student?");
            System.out.println("0 = Student");
            System.out.println("1 = Teacher");

            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number < 0 || number > 2) {
                    System.out.println("Invalid input. The number must be between 0 and 1.");
                    continue;
                }
                if(number == 0) addStudent(username,password, name, email);
                if(number == 1) addTeacher(username,password, name, email);
                break;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }

        System.out.println("Successfully added!");
    }

    protected static void signIn(Scanner scanner){
        System.out.println("Login or sign up?");

        while(true){
            System.out.println("Please enter a number between 0 and 2 ");
            System.out.println("0 = exit");
            System.out.println("1 = log in");
            System.out.println("2 = sign up");

            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number < 0 || number > 2) {
                    System.out.println("Invalid input. The number must be between 0 and 2.");
                    continue;
                }
                if(number == 0) break;
                if(number == 1) logIn(scanner);
                if(number == 2) signUp(scanner);
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }

    }
}
