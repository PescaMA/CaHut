package org.service;

import org.classes.*;

import java.util.Optional;
import java.util.Scanner;

public class UserService {
    static Scanner scanner;

    public static void run(User user, Scanner newScanner){
        scanner = newScanner;
        if(user instanceof Teacher)
            runTeacher((Teacher) user);
        if(user instanceof Student)
            runStudent((Student) user);
    }
    public static void addQuiz(Teacher teacher){
        Quiz quiz = QuizService.makeQuiz(scanner, teacher);

        while(true){
            System.out.println("0 = back");
            System.out.println("1 = print courses");
            System.out.println("2 = make course");
            System.out.println("[Course name]");

            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number < 0 || number > 2) {
                    System.out.println("Invalid input. The number must be between 0 and .");
                    continue;
                }
                if(number == 0) return;
                if(number == 1) System.out.println(teacher.getCoursesNames());
                if(number == 2) makeCourse(teacher);
            } else {
                String course = scanner.next();
                if(teacher.addQuiz(course,quiz)){
                    System.out.println("added quiz!");
                }
                else{
                    System.out.println("course not found!");
                }
            }
        }
    }
    protected static void viewCourse(User user){

        String courseName = scanner.next();
        Optional<Course> course = user.getCourse(courseName);
        if(course.isEmpty()){
            System.out.println("Course not found!");
        }
        else
            CourseService.viewCourse(scanner, course.get(), user);
    }
public static void makeCourse(Teacher teacher){
        while(true){
            System.out.println("enter course name: ");
            String courseName = scanner.next();

            if(teacher.getCoursesNames().contains(courseName)){
                System.out.println("Course already exists!");
                continue;
            }
            teacher.makeCourse(courseName);
            return;
        }
    }
    public static void runTeacher(Teacher teacher){
        System.out.println("Welcome teacher! What do you want to do?");

        while(true){
            System.out.println("0 = Log out");
            System.out.println("1 = Make&add quiz");
            System.out.println("2 = Make course");
            System.out.println("3 = View courses");
            System.out.println("4 = View all students");


            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number < 0 || number > 4) {
                    System.out.println("Invalid input. The number must be between 0 and 4.");
                    continue;
                }
                if(number == 0) return;
                if(number == 1) UserService.addQuiz(teacher);
                if(number == 2) makeCourse(teacher);
                if(number == 3) System.out.println(teacher.getCoursesNames());
                if(number == 4) System.out.println(LoginService.users);
            } else {
                viewCourse(teacher);
            }
        }
    }
    public static void runStudent(Student student){
        System.out.println("Welcome student! What do you want to do?");


        while(true){
            System.out.println("0 = Log out");
            System.out.println("1 = View courses");
            System.out.println("[course name]");

            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number < 0 || number > 1) {
                    System.out.println("Invalid input. The number must be between 0 and 2.");
                    continue;
                }
                if(number == 0) return;
                System.out.println( student.getCourses() ) ;
            }
            else viewCourse(student);
        }
    }
}
