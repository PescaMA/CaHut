package org.service;

import org.classes.*;

import java.util.HashMap;
import java.util.Scanner;

public class LoginService {
    protected static HashMap<String, User> users = new HashMap<>();

    protected static void loadUsers(){
        ///  TO DO when database implemented.
    }
    protected static void addStudent(String username, String password){
        User newUser = new Student(username,password);
        users.put(username,newUser);
        ///  TO DO add to database
    }
    protected static void addTeacher(String username, String password){
        User newUser = new Teacher(username,password);
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
                if(number == 0) addStudent(username,password);
                if(number == 1) addTeacher(username,password);
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
