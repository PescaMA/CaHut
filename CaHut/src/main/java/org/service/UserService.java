package org.service;

import org.classes.*;

import org.models.CourseDB;
import org.models.CourseStudentDB;
import org.models.UserDB;

import java.util.Optional;


public class UserService {
    protected static AppInit appInit = AppInit.getInstance();

    public static void run(UserDB user){
        if(user instanceof TeacherData)
            runTeacher((TeacherData) user);
        else if(user instanceof StudentData)
            runStudent((StudentData) user);
        else
            System.out.println("abstract user. ");
    }
    public static void addQuiz(TeacherData teacher){
        QuizData quiz = QuizService.makeQuiz(appInit.getScanner(), teacher);

        System.out.println("Now Add quiz to a course! ");
        while(true){
            System.out.println("0 = back");
            System.out.println("1 = print courses");
            System.out.println("2 = make course");
            System.out.println("[Course name]");

            if (appInit.getScanner().hasNextInt()) {
                int number = appInit.getScanner().nextInt();
                if (number < 0 || number > 2) {
                    System.out.println("Invalid input. The number must be between 0 and .");
                    continue;
                }
                if(number == 0) return;
                if(number == 1) System.out.println(teacher.getCoursesNames());
                if(number == 2) makeCourse(teacher);
            } else {
                String course = appInit.getScanner().next();
                if(teacher.addQuiz(course,quiz)){
                    System.out.println("added quiz!");
                    appInit.linkQuiz(course, quiz.getName());

                    AuditService.save("7. adaugarea unui quiz la curs. ");
                }
                else{
                    System.out.println("course not found!");
                }
            }
        }
    }
    protected static void viewCourse(UserData user){

        String courseName = appInit.getScanner().next();
        Optional<CourseData> course = user.getCourse(courseName);
        if(course.isEmpty()){
            System.out.println("Course not found!");
        }
        else
            CourseService.viewCourse(course.get(), user);
    }
public static void makeCourse(TeacherData teacher){
        while(true){
            System.out.println("enter course name: ");
            String courseName = appInit.getScanner().next();

            if(teacher.getCoursesNames().contains(courseName)){
                System.out.println("Course already exists!");
                continue;
            }
            teacher.makeCourse(courseName);
            CourseDB course = new CourseDB (courseName, teacher).save();
            appInit.addCourse(course);

            AuditService.save("2. Crearea unui nou curs.");
            return;
        }
    }
    public static void runTeacher(TeacherData teacher){
        System.out.println("Welcome teacher! What do you want to do?");

        while(true){
            System.out.println("0 = Log out");
            System.out.println("1 = Make&add quiz");
            System.out.println("2 = Make course");
            System.out.println("3 = View courses");
            System.out.println("4 = View all students");
            System.out.println("[course name]");

            if (appInit.getScanner().hasNextInt()) {
                int number = appInit.getScanner().nextInt();
                if (number < 0 || number > 4) {
                    System.out.println("Invalid input. The number must be between 0 and 4.");
                    continue;
                }
                if(number == 0) return;
                if(number == 1) UserService.addQuiz(teacher);
                if(number == 2) makeCourse(teacher);
                if(number == 3) {System.out.println(teacher.getCoursesNames());
                    AuditService.save("10. afisare toate cursurile");}
                if(number == 4) System.out.println(appInit.getUsers());
            } else {
                viewCourse(teacher);
            }
        }
    }
    public static void runStudent(StudentData student){
        while(true){
            System.out.println("Welcome student " + student.getUsername() + "! What do you want to do?");
            System.out.println("0 = Log out");
            System.out.println("1 = View courses");
            System.out.println("2 = update username");
            System.out.println("3 = delete self");

            System.out.println("[course name]");

            if (appInit.getScanner().hasNextInt()) {
                int number = appInit.getScanner().nextInt();
                if (number < 0 || number > 3) {
                    System.out.println("Invalid input. The number must be between 0 and 2.");
                    continue;
                }
                if(number == 0) return;
                if(number == 1) System.out.println( student.getCourses() ) ;
                if(number == 2){
                    String newUsername = LoginService.readUsername();
                    if(newUsername == null){
                        System.out.println("canceled changing username");
                        continue;
                    }
                    student.setUsername(newUsername);
                    student.update();
                }
                if(number == 3){
                    student.delete();
                    appInit.getUsers().remove(student.getUsername());
                    return;
                }
            }
            else viewCourse(student);
        }
    }
}
