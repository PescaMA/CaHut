package org.service;

import org.classes.Question;
import org.classes.Quiz;
import org.classes.Teacher;

import java.util.ArrayList;
import java.util.Scanner;

public class QuizService {
    protected static Scanner scanner;


    public static Quiz makeQuiz(Scanner newScanner, Teacher teacher){
        String quizName;
        scanner = newScanner;

        System.out.println("Enter quiz name: ");
        do {
            quizName = scanner.nextLine();
        } while (quizName.trim().isEmpty());


        ArrayList<Question> questions = new ArrayList<>();

        while(true){
            System.out.println("Question is (0 to finish quiz):");
            String body = scanner.nextLine();

            if(body.trim().isEmpty()) continue;
            if(body.trim().equals("0")) break;

            questions.add(makeQuestion(body));
        }


        return new Quiz(teacher, quizName, questions);
    }
    public static Question makeQuestion(String body){

        ArrayList<String> correctAnswers = new  ArrayList<>();
        while (true){
            System.out.println("Correct answer (0 to go to next step): ");
            String answer = scanner.nextLine();

            if(body.trim().isEmpty()) continue;
            if(answer.trim().equals("0")) break;

            correctAnswers.add(answer);
        }

        ArrayList<String> wrongAnswers = new  ArrayList<>();
        while (true){
            System.out.println("Wrong answer (0 to go to next step): ");
            String answer = scanner.nextLine();

            if(body.trim().isEmpty()) continue;
            if(answer.trim().equals("0")) break;

            wrongAnswers.add(answer);
        }

        int seconds ;
        while (true){
            System.out.println("Seconds to finish the question: ");
            if(scanner.hasNextInt()){
                seconds = scanner.nextInt();
                if(seconds <= 5){
                    System.out.println("must have at least 5 seconds! ");
                    continue;
                }
                break;
            }
            System.out.println("Enter a number! ");
            scanner.next();
        }
        return new Question(body,seconds,correctAnswers,wrongAnswers);
    }
}
