package org.service;

import org.classes.QuestionData;
import org.classes.QuizData;
import org.classes.TeacherData;

import java.util.ArrayList;
import java.util.Scanner;

public class QuizService {
    protected static AppInit appInit = AppInit.getInstance();


    public static QuizData makeQuiz(Scanner newScanner, TeacherData teacher){
        String quizName;

        System.out.println("Enter quiz name: ");
        do {
            quizName = appInit.getScanner().nextLine();
        } while (quizName.trim().isEmpty());


        ArrayList<QuestionData> questions = new ArrayList<>();

        while(true){
            System.out.println("Question is (0 to finish quiz):");
            String body = appInit.getScanner().nextLine();

            if(body.trim().isEmpty()) continue;
            if(body.trim().equals("0")) break;

            questions.add(makeQuestion(body));
        }


        return new QuizData(teacher, quizName, questions);
    }
    public static QuestionData makeQuestion(String body){

        ArrayList<String> correctAnswers = new  ArrayList<>();
        while (true){
            System.out.println("Correct answer (0 to go to next step): ");
            String answer = appInit.getScanner().nextLine();

            if(body.trim().isEmpty()) continue;
            if(answer.trim().equals("0")) break;

            correctAnswers.add(answer);
        }

        ArrayList<String> wrongAnswers = new  ArrayList<>();
        while (true){
            System.out.println("Wrong answer (0 to go to next step): ");
            String answer = appInit.getScanner().nextLine();

            if(body.trim().isEmpty()) continue;
            if(answer.trim().equals("0")) break;

            wrongAnswers.add(answer);
        }

        int seconds ;
        while (true){
            System.out.println("Seconds to finish the question: ");
            if(appInit.getScanner().hasNextInt()){
                seconds = appInit.getScanner().nextInt();
                if(seconds <= 5){
                    System.out.println("must have at least 5 seconds! ");
                    continue;
                }
                break;
            }
            System.out.println("Enter a number! ");
            appInit.getScanner().next();
        }
        return new QuestionData(body,seconds,correctAnswers,wrongAnswers);
    }
}
