package org.service;

import org.classes.*;

import java.util.Scanner;

public class CourseService {
    static Scanner scanner;
    protected static void addStudent(Course course, Teacher teacher){
        while(true){
            System.out.println("enter student name (or 0 to cancel): ");
            String student = scanner.next();

            if(student.equals("0"))
                return;

            if(course.getStudents().contains(student)){
                System.out.println("Student already exists!");
                continue;
            }
            if(!LoginService.users.containsKey(student) || !(LoginService.users.get(student) instanceof Student)) {
                System.out.println("Student not found! ");
                continue;
            }
            if(!teacher.addStudent(course.getName(), (Student) LoginService.users.get(student)))
                System.out.println("Error adding student.");
            else
                System.out.println("Successfully added student.");
            return;
        }
    }
    protected static void startQuiz(Course course, Student student){
        while(true){
            System.out.println("enter quiz name (or 0 to cancel): ");
            String quizName = scanner.next();

            if(quizName.equals("0"))
                return;

            if(course.getQuiz(quizName).isEmpty()){
                System.out.println("Quiz not found!");
                continue;
            }

            Quiz quiz = course.getQuiz(quizName).get();

            Score score = quiz.startQuiz();

            student.updateScore(quiz.getName(), score);

            return;
        }
    }

    public static void viewCourse(Scanner newScanner, Course course, User user){
        scanner = newScanner;
        System.out.println("Course " + course.getName() + ": ");

        while (true){
            System.out.println("0 = exit");
            System.out.println("1 = view students");
            System.out.println("2 = view quizzes");
            if(user instanceof Teacher)
                System.out.println("3 = add student");
            if(user instanceof Student)
                System.out.println("3 = start quiz");

            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number < 0 || number > 4) {
                    System.out.println("Invalid input. The number must be between 0 and 4.");
                    continue;
                }
                if(number == 0) return;
                if(number == 1) System.out.println(course.getStudentsByScore());
                if(number == 2) System.out.println(course.getQuizzes());
                if(number == 3){
                    if(user instanceof Teacher)
                        addStudent(course, (Teacher) user);
                    if(user instanceof  Student)
                        startQuiz(course, (Student) user);
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }

    }
}
