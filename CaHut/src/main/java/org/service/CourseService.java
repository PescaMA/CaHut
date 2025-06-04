package org.service;

import org.classes.*;
import org.models.CourseStudentDB;

public class CourseService {
    protected static AppInit appInit = AppInit.getInstance();

    protected static void addStudent(CourseData course, TeacherData teacher){
        while(true) {
            System.out.println("enter student name (or 0 to cancel): ");
            String student = appInit.getScanner().next();

            if (student.equals("0"))
                return;

            AuditService.save("8. Cautare dupa nume a student.");

            if (course.getStudents().contains(student)) {
                System.out.println("Student already exists!");
                continue;
            }
            if (!appInit.getUsers().containsKey(student) || !(appInit.getUsers().get(student) instanceof StudentData)) {
                System.out.println("Student not found! ");
                continue;
            }
            if (!teacher.addStudent(course.getName(), (StudentData) appInit.getUsers().get(student))) {
                System.out.println("Error adding student.");
            } else {
                appInit.linkStudent(course.getName(), student);
                System.out.println("Successfully added student.");
                AuditService.save("5. inscrierea unui student la un curs.");
            }
            return;
        }
    }
    protected static void startQuiz(CourseData course, StudentData student){
        while(true){
            System.out.println("enter quiz name (or 0 to cancel): ");
            String quizName = appInit.getScanner().next();

            if(quizName.equals("0"))
                return;

            if(course.getQuiz(quizName).isEmpty()){
                System.out.println("Quiz not found!");
                continue;
            }

            QuizData quiz = course.getQuiz(quizName).get();

            ScoreData score = quiz.startQuiz();

            student.updateScore(quiz.getName(), score);

            return;
        }
    }

    public static void viewCourse(CourseData course, UserData user){
        System.out.println("Course " + course.getName() + ": ");

        while (true){
            System.out.println("0 = exit");
            System.out.println("1 = view students");
            System.out.println("2 = view quizzes");
            if(user instanceof TeacherData)
                System.out.println("3 = add student");
            if(user instanceof StudentData)
                System.out.println("3 = start quiz");

            if (appInit.getScanner().hasNextInt()) {
                int number = appInit.getScanner().nextInt();
                if (number < 0 || number > 4) {
                    System.out.println("Invalid input. The number must be between 0 and 4.");
                    continue;
                }
                if(number == 0) return;
                if(number == 1) {System.out.println(course.getStudentsByScore());
                    AuditService.save("11. Afișare scoruri obținute de un cursant la quizurile susținute.");

                }
                if(number == 2) System.out.println(course.getQuizzes());
                if(number == 3){
                    if(user instanceof TeacherData)
                        addStudent(course, (TeacherData) user);
                    if(user instanceof StudentData) {
                        AuditService.save("8. Incepere de quiz");
                        startQuiz(course, (StudentData) user);
                    }
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                appInit.getScanner().next();
            }
        }

    }
}
