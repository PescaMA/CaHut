package org.service;

import org.classes.QuestionData;
import org.classes.QuizData;
import org.classes.TeacherData;
import org.models.QuestionDB;
import org.models.QuizDB;

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


        ArrayList<QuestionDB> questions = new ArrayList<>();

        while(true){
            System.out.println("Question is (0 to finish quiz):");
            String body = appInit.getScanner().nextLine();

            if(body.trim().isEmpty()) continue;
            if(body.trim().equals("0")) break;
            questions.add(makeQuestion(body));
            AuditService.save("6. adaugare intrebare si raspunsuri la quiz.");
        }

        QuizDB q = new QuizDB(teacher, quizName, new ArrayList<>(questions)).save();
        appInit.addQuiz(q);

        for(QuestionDB question : questions){
            question.setQuiz_pk(q.pk());
            question.save();
        }

        AuditService.save("3. Creare nou quiz. ");

        return q;
    }
    public static QuestionDB makeQuestion(String body){

        ArrayList<String> correctAnswers = new  ArrayList<>();
        while (true){
            System.out.println("Correct answer (0 to go to next step): ");
            String answer = appInit.getScanner().nextLine();

            if(answer.trim().isEmpty()) continue;
            if(answer.trim().equals("0")) break;

            correctAnswers.add(answer);
        }

        ArrayList<String> wrongAnswers = new  ArrayList<>();
        while (true){
            System.out.println("Wrong answer (0 to go to next step): ");
            String answer = appInit.getScanner().nextLine();

            if(answer.trim().isEmpty()) continue;
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
        return new QuestionDB(body,seconds,correctAnswers,wrongAnswers);
    }
}
